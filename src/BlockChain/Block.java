package BlockChain;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import HashUtil.HashUtil;

public class Block {
    private Date timeStamp; //La date au moment de la création
    private String hashPrecedent; //Hash du block précédent de la chaine
    private String hashRootMerkle = "";
    private BlockChain blockChain;
    private String hashBlockCourant;
    private ArrayList<String> listeTransaction = new ArrayList<>();
    private int nonce; //En cryptographie, un nonce est un nombre arbitraire destiné à être utilisé une seule fois. Il s'agit souvent d'un nombre aléatoire ou pseudo-aléatoire émis dans un protocole d'authentification pour garantir que les anciennes communications ne peuvent pas être réutilisées dans des attaques par rejeu

    public Block(BlockChain blockChain){
        this.blockChain = blockChain;
        this.timeStamp = new Date();
    }

    public void setHashRootMerkle() {
        this.hashRootMerkle = calculateMerkleRoot(listeTransaction).toString();
    }

    public String getHashPrecedent() {
        hashPrecedent = blockChain.getPreviousBlocks().hashBlockCourant;
        return hashPrecedent;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public int getNonce() {
        return nonce;
    }


    public Object calculateMerkleRoot(ArrayList<String> listTransaction){ //Last transact in a block
        if(listTransaction.size() == 1){
            return HashUtil.applySha256(listTransaction.toString());
        }
        ArrayList<String> parentHash = new ArrayList<>();

        if(listTransaction.size()%2 != 0){
            listTransaction.add(listTransaction.get(listTransaction.size()-1));
        }
        for(int i = 0; i < listTransaction.size(); i+=2){
            String hashed = HashUtil.applySha256(listTransaction.get(i) + listTransaction.get(i + 1));
            parentHash.add(hashed);
        }
        if(listTransaction.size() %2 == 1){
            String lastHash = listTransaction.get(listTransaction.size()-1); //On lui donne le dernier hash
            String lastHashHashed = HashUtil.applySha256(lastHash);
            parentHash.add(lastHashHashed);
        }
        return calculateMerkleRoot(parentHash);
    }

    public String getHashRootMerkle() {
        return hashRootMerkle;
    }

    public String getHashBlockCourant() {
        return hashBlockCourant;
    }

    public ArrayList<String> getListeTransaction() {
        return listeTransaction;
    }

    public void transaction(String message){
        listeTransaction.add(message);
    }

    public void calculateHashing(){
        hashing(blockChain.getDifficulte(), 0);
    }

    protected String hashing(int difficulte, int nonce){
        do {
            hashBlockCourant = HashUtil.applySha256(String.valueOf(nonce) + getHashPrecedent() + hashRootMerkle + timeStamp);
            nonce++;
        }while(!hashBlockCourant.matches("[0]{"+difficulte+"}(.*)")); //Regex : On cherche uniquement [0]{difficulte} et ça fini par ce qu'on veut
        this.nonce = nonce;
        return hashBlockCourant;
    }

    protected boolean verifyHash(){
        return hashing(blockChain.getDifficulte(), this.nonce) == hashBlockCourant;
    }
}
