package BlockChain;

import Utilisateurs.Creator;
import Tools.RandomNumber;
import Utilisateurs.Mineur;
import Utilisateurs.User;

import java.util.ArrayList;


/**
 * @author Clément PAYET
 * The type Blockchain.
 */
public class BlockChain {
    private final int difficulte; //Difficultée de la blockChain
    private final int nbBlock; //Nombre de blocs de la blockChain ? Genesis ?
    private final int  NB_TRANSACTION_MAX; //Nombre de transaction max par blocs
    private transient int nbTransactionMax;
    private transient int nbTransaction = 1;
    private transient int indexBlock = 1;
    private int recompense = 50;
    private final Block[] blocks; //Tableau de blocs
    private ArrayList<ArrayList<Object>> utxo = new ArrayList<>();


    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

    /**
     * Instantiates a new Block chain.
     *
     * @param difficulte         the difficulte
     * @param nbBlock            the nb block
     * @param createur           the createur
     * @param NB_TRANSACTION_MAX the nb transaction max
     */
    public BlockChain(int difficulte, int nbBlock, Creator createur, int NB_TRANSACTION_MAX) { //Constructeur de BlockChain.BlockChain
        this.NB_TRANSACTION_MAX = NB_TRANSACTION_MAX;
        this.nbTransactionMax = NB_TRANSACTION_MAX;
        this.difficulte = difficulte;
        this.nbBlock = nbBlock;
        blocks = new Block[nbBlock];
        blocks[0] = createur.createFirstBlock(this);
        for(int i = 1; i < nbBlock; i++){
            blocks[i] = new Block(this);
        }
    }

    /**
     * Gets nb transaction max.
     *
     * @return the nb transaction max
     */
    public int getNbTransactionMax() {
        return nbTransactionMax;
    }

    /**
     * Sets index block.
     *
     * @param indexBlock the index block
     */
    public void setIndexBlock(int indexBlock) {
        this.indexBlock = indexBlock;
    }

    /**
     * Gets nb block.
     *
     * @return the nb block
     */
    public int getNbBlock() {
        return nbBlock;
    }

    /**
     * Gets previous blocks.
     *
     * @return the previous blocks
     */
    public Block getPreviousBlocks() {
        return blocks[indexBlock-1];
    }

    /**
     * Gets current blocks.
     *
     * @return the current blocks
     */
    public Block getCurrentBlocks() {
        return blocks[indexBlock];
    }

    /**
     * Gets blocks.
     *
     * @param index the index
     * @return the blocks
     */
    public Block getBlocks(int index) {
        return blocks[index];
    }

    /**
     * Gets difficulte.
     *
     * @return the difficulte
     */
    public int getDifficulte() {
        return difficulte;
    }

    /**
     * Trouver un mineur dans une liste.
     *
     * @param users the User list
     * @return the first mineur that we fund in the User list
     */
    public Mineur trouverMineur(User[] users){
        Mineur mineur = null;
        int rand3 = (int) (Math.random()*users.length);
        while(true){ //Tant qu'on a pas trouvé de mineur dans les users
            if(users[rand3] instanceof Mineur){
                mineur = (Mineur) users[rand3];
                break;
            }else{
                rand3 = (int) (Math.random()*users.length);
            }
        }
        return mineur;
    }
    public int montantWallet(ArrayList<ArrayList<Object>> wallet){
        int somme = 0;
        for(int i = 0; i < wallet.size(); i++){
            somme += (int)wallet.get(i).get(2);
        }
        return somme;
    }


    public ArrayList<ArrayList<Object>> walletUser(User user){
        ArrayList<ArrayList<Object>> wallet = new ArrayList<>();
        for(int i = 0; i < utxo.size(); i++){
            if(user.getHashUserPublic().equals(utxo.get(i).get(1))){
                wallet.add(utxo.get(i));
            }
        }
        return wallet;
    }

    /**
     * Fait une transaction aléatoire entre 2 users et un montant prix aléatoirement.
     *
     * @param users list
     */
    public void transactionAleatoire(User[] users){
        int rand1, rand2, montant;
        montant = RandomNumber.getRandomNumberInRange(1,10);
        do{
            rand1 = (int) (Math.random()*users.length);
            rand2 = (int) (Math.random()*users.length);
        }while(rand2 == rand1);
        User un = users[rand1];
        if(!un.aAssezDArgent(montant)){ //Si le premier user qui doit donner n'a pas assez d'argent, alors on en cherche un autre en utilisant la même fonction
            transactionAleatoire(users);
            return;
        }
        User deux = users[rand2];
        transaction(un, deux, montant, trouverMineur(users));
    }

    private void inflation(){
        if(indexBlock % nbBlock/3 == 0){
            this.recompense /= 3;
        }
    }

    public void transaction(User u1, User u2, int montant, Mineur mineur){
        if(indexBlock >= nbBlock){
            return;
        }
        if(nbTransaction <= nbTransactionMax){ //Si le nb de transaction est <= aux nombre max de transaction donné avec un rand, on les ajoutes au bloc courant
            utxo.add(this.getCurrentBlocks().transaction(u1, u2, montant));
            nbTransaction++;
            //Rajouter condition : && this.montantWallet(this.walletUser(u1)) >= montant ?
        }else{
            this.getCurrentBlocks().setHashRootMerkle();
            this.getCurrentBlocks().calculateHashing(mineur, recompense);
            inflation();
            nbTransaction = 1;
            indexBlock++;
            nbTransactionMax = RandomNumber.getRandomNumberInRange(1, NB_TRANSACTION_MAX); //On regenère un nombre aléatoire de transaction pour le prochain block.
            transaction(u1, u2, montant, mineur);
        }
    }


    /**
     * Check integrite bc boolean.
     *
     * @param mineur the mineur
     * @return the boolean
     */
    public boolean checkIntegriteBC(Mineur mineur){
        int tmpIndex = this.indexBlock;
        for (int i = 1; i < nbBlock; i++) { //Le bloc 1 est le genesis : hash du bloc : 0
            setIndexBlock(i); //Changement d'index pour avoir le previous qui est bien de 0 à n au lieu de n-1 direct.
            if(!this.getBlocks(i).verifyHash(mineur)){
                setIndexBlock(tmpIndex);
                return false;
            }
        }
        setIndexBlock(tmpIndex);
        return true;
    }

    /**
     * Affiche la BC en couleur
     */
    public void printBC(){
        System.out.println("\u001B[33m[Contenu de la BlockChain]");
        for(int i = 0; i < nbBlock; i++){
            System.out.println(ANSI_YELLOW + "[Block n°"+i+"]" + ANSI_RESET);
            System.out.println("| Date de création : " + getBlocks(i).getTimeStamp());
            System.out.println("| Nonce : " + getBlocks(i).getNonce());
            System.out.println("| Liste de transaction : ");
            ArrayList<String> listTransaction = getBlocks(i).getListeTransaction();
            for(int j = 0; j < listTransaction.size(); j++){
                System.out.println(ANSI_BLUE + "    | " + j + " - " + listTransaction.get(j) + ANSI_RESET);
            }
            System.out.println("| Hash Merkle root        : " + getBlocks(i).getHashMerkleRoot());
            if(i != 0) {
                System.out.println("| Hash du block precedent : " + getBlocks(i - 1).getHashBlockCourant());
            }
            System.out.println("| Hash du block           : " + getBlocks(i).getHashBlockCourant() + "\n");
        }
    }

    /**
     * Remplir bc avec des transactions aléatoire.
     * @param users list
     */
    public void remplirBC(User[] users){
        for(int i = 1; i <= nbBlock; i++){
            int nbtransactTest = getNbTransactionMax();
            for(int j = 0; j <= nbtransactTest; j++){
                transactionAleatoire(users);
            }
        }
    }
}
