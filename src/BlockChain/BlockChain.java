package BlockChain;

import Utilisateurs.Creator;
import Tools.RandomNumber;
import Utilisateurs.Mineur;
import Utilisateurs.User;

import java.util.ArrayList;
import java.util.Arrays;


public class BlockChain {
    private final int difficulte; //Difficultée de la blockChain
    private final int nbBlock; //Nombre de blocs de la blockChain ? Genesis ?
    private final int  NB_TRANSACTION_MAX; //Nombre de transaction max par blocs
    private transient int nbTransactionMax;
    private transient int nbTransaction = 1;
    private transient int indexBlock = 1;
    private final Block[] blocks; //Tableau de blocs

    private int recompense = 50;

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";

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

    public int getNbTransactionMax() {
        return nbTransactionMax;
    }

    public void transaction(String message, Mineur mineur){
        if(indexBlock >= nbBlock){
            return;
        }
        if(nbTransaction <= nbTransactionMax){
            this.getCurrentBlocks().transaction(message);
            nbTransaction++;
        }else{//TODO pb quand le NB_TRANSACTION_MAX == 1 pour le dernier block : Hashbock = null & Merkleroot ==
            nbTransactionMax = RandomNumber.getRandomNumberInRange(1, NB_TRANSACTION_MAX); //On regenère un nombre aléatoire de transaction pour le prochain block.
            this.getCurrentBlocks().setHashRootMerkle();
            this.getCurrentBlocks().calculateHashing(mineur);
            nbTransaction = 1;
            indexBlock++;
            transaction(message, mineur);

        }
    }

    public void setIndexBlock(int indexBlock) {
        this.indexBlock = indexBlock;
    }

    public int getNbBlock() {
        return nbBlock;
    }

    public Block getPreviousBlocks() {
        return blocks[indexBlock-1];
    }
    public Block getCurrentBlocks() {
        return blocks[indexBlock];
    }
    public Block getBlocks(int index) {
        return blocks[index];
    }

    public int getDifficulte() {
        return difficulte;
    }

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

    public void printBC(){
        System.out.println("\u001B[33m[Contenu de la BlockChain]");
        for(int i = 0; i < nbBlock; i++){
            System.out.println(ANSI_YELLOW + "[Block n°"+i+"]" + ANSI_RESET);
            System.out.println("| Date de création : " + getBlocks(i).getTimeStamp());
            System.out.println("| Nonce : " + getBlocks(i).getNonce());
            System.out.println("| Liste de transaction : ");
            ArrayList<String> listTransaction = getBlocks(i).getListeTransactionGenesis();
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

    public void transactionAleatoire(User[] users){
        int rand1, rand2, montant;
        do{
            rand1 = (int) (Math.random()*users.length);
            rand2 = (int) (Math.random()*users.length);
        }while(rand2 == rand1);
        montant = RandomNumber.getRandomNumberInRange(1,10);
        User un = users[rand1];
        User deux = users[rand2];
        Mineur mineur = trouverMineur(users);
        transaction(un.getNom() + " donne " + montant + " BNB à " + deux.getNom(), mineur);
        //TODO faire perdre et gagner de l'argent a la personne qui donne et celle qui reçoit
    }

    public User[] createNUsers(int nbUsers){
        User[] users = new User[nbUsers];
        int nom, type;
        ArrayList<String> listePrenoms = new ArrayList<>(Arrays.asList("joyce","lola","brian","louis", "an", "charles","diane","ravi","achille","nathan","javier","chance","lois","dominique","pedro","karl","rose","james","constance","roland","christine","archibald","renard","angel","mary","sam","stepan","eugenio","eden","justice","bart","alix","guy","mo","alfred","francine","brigitte","iris","eddie","otto","fabia","roger","maurice","louise","camille","luis","sabine","chantal","val","paulo","lin","giselle","caroline","sandra","trace","michel","andré","pauline","jung","colombe","lou","miguel","francisco","gladys","edgar","rodrigo","oscar","young","ion","carl","li","edward","alex","fernando","avril","albert","temple","catherine","lourdes","sage","marin","douglas","gordon","jacqueline","colin","paco","julius","josé","scott","roy","felipe","ariane","marek","denise","london","taylor","hermine","philip","indigo","malvina"));
        for(int i = 0; i < nbUsers; i++){
            type = RandomNumber.getRandomNumberInRange(0,1);
            nom = RandomNumber.getRandomNumberInRange(0,nbUsers-1);
            if(type == 0){
                users[i] = new User(listePrenoms.get(nom), listePrenoms.get(nom), 0);
            }else{
                users[i] = new Mineur(listePrenoms.get(nom), listePrenoms.get(nom), 0);
            }
        }
        return users;
    }

    public void remplirBC(User[] users){
        for(int i = 1; i < nbBlock; i++){
            int nbtransactTest = getNbTransactionMax();
            int j = 0;
            while(j < nbtransactTest){
                transactionAleatoire(users);
                j++;
            }
        }
    }
}
