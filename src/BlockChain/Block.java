package BlockChain;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import HashUtil.HashUtil;
import Tools.RandomNumber;
import Utilisateurs.Mineur;
import Utilisateurs.User;

/**
 * @author Clément PAYET
 * The type Block.
 */
public class Block {
    private final Date timeStamp; //La date au moment de la création
    private String hashRootMerkle = "";
    private final transient BlockChain blockChain; //Transient pour pas que le json soit recursif et donc infini
    private String hashBlockCourant;
    private ArrayList<String> listeTransaction = new ArrayList<>();
    private int nonce; //En cryptographie, un nonce est un nombre arbitraire destiné à être utilisé une seule fois. Il s'agit souvent d'un nombre aléatoire ou pseudo-aléatoire émis dans un protocole d'authentification pour garantir que les anciennes communications ne peuvent pas être réutilisées dans des attaques par rejeu

    /**
     * Instantiates a new Block.
     *
     * @param blockChain the blockchain
     */
    public Block(BlockChain blockChain){
        this.blockChain = blockChain;
        this.timeStamp = new Date();
    }

    /**
     * Sets hash root merkle.
     */
    public void setHashRootMerkle() {
        ArrayList<String> tmp = new ArrayList<>(listeTransaction);
        hashRootMerkle = calculateMerkleRoot(tmp).toString();
    }

    /**
     * Gets hash precedent.
     *
     * @return the hash precedent
     */
    public String getHashPrecedent() {
        return blockChain.getPreviousBlocks().getHashBlockCourant();
    }

    /**
     * Gets time stamp.
     *
     * @return the time stamp
     */
    public Date getTimeStamp() {
        return timeStamp;
    }

    /**
     * Gets nonce.
     *
     * @return the nonce
     */
    public int getNonce() {
        return nonce;
    }

    /**
     * Sets nonce.
     *
     * @param nonce the nonce
     */
    public void setNonce(int nonce) {
        this.nonce = nonce;
    }

    /**
     * Calculate merkle root object.
     *
     * @param transacList the list transaction
     * @return the object
     */
    public Object calculateMerkleRoot(ArrayList<String> transacList){ //Last transact in a block
        if(transacList.size() == 1){
            return HashUtil.applySha256(transacList.toString());
        }
        ArrayList<String> parentHash = new ArrayList<>();

        if(transacList.size()%2 != 0){
            transacList.add(transacList.get(transacList.size()-1));
        }
        for(int i = 0; i < transacList.size(); i+=2){
            String hashed = HashUtil.applySha256(transacList.get(i) + transacList.get(i + 1));
            parentHash.add(hashed);
        }
        if(transacList.size() %2 == 1){
            String lastHash = transacList.get(transacList.size()-1); //On lui donne le dernier hash
            String lastHashHashed = HashUtil.applySha256(lastHash);
            parentHash.add(lastHashHashed);
        }
        return calculateMerkleRoot(parentHash);
    }

    /**
     * Gets hash merkle root.
     *
     * @return the hash merkle root
     */
    public String getHashMerkleRoot() {
        return hashRootMerkle;
    }

    /**
     * Gets hash block courant.
     *
     * @return the hash block courant
     */
    public String getHashBlockCourant() {
        return hashBlockCourant;
    }

    /**
     * Gets liste transaction genesis.
     *
     * @return the liste transaction genesis
     */
    public ArrayList<String> getListeTransaction() {
        return listeTransaction;
    }

    /**
     * Transaction.
     *  @param message the message
     * @param utxo
     */
    public void transaction(User u1, User u2, int montant, ArrayList<ArrayList<Object>> utxo){
        listeTransaction.add(u1.getHashUserPublic() + " donne "+ montant + " Bnb a " +u2.getHashUserPublic());

    }

    /**
     * Calculate hashing.
     *
     * @param mineur     the mineur qui va miner
     * @param recompense the recompense gagner par le mineur
     */
    public void calculateHashing(Mineur mineur, int recompense){
        hashBlockCourant = mineur.mining(blockChain.getDifficulte(), 0, this, recompense);
    }


    /**
     * Verify hash avec la nonce du bloc etc.
     *
     * @param mineur the mineur qui va check
     * @return the boolean
     */
    protected boolean verifyHash(Mineur mineur){
        return mineur.checkIntegrity(this).equals(this.getHashBlockCourant());
    }
}
