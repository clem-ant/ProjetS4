package BlockChain;
import java.util.ArrayList;
import java.util.Date;
import HashUtil.HashUtil;

public class Block {
    private Date timeStamp = new Date(); //La date au moment de la création
    private String hashPrecedent; //Hash du block précédent de la chaine
    private String hashRootMerkle = "";
    private BlockChain blockChain;
    private String hashBlockCourant;
    private ArrayList<String> listeTransaction = new ArrayList<String>();
    private int nonce = 0; //En cryptographie, un nonce est un nombre arbitraire destiné à être utilisé une seule fois. Il s'agit souvent d'un nombre aléatoire ou pseudo-aléatoire émis dans un protocole d'authentification pour garantir que les anciennes communications ne peuvent pas être réutilisées dans des attaques par rejeu

    public Block(BlockChain blockChain){
        this.blockChain = blockChain;
    }

    public void setHashRootMerkle() {
        this.hashRootMerkle = calculateMerkleRoot(listeTransaction).toString();
    }

    public String getHashPrecedent() {
        hashPrecedent = blockChain.getPreviousBlocks().hashBlockCourant;
        return hashPrecedent;
    }

    public ArrayList<String> calculateMerkleRoot(ArrayList<String> listeTransaction){ //Last transact in a block
        if(listeTransaction.size() == 1){
            hashRootMerkle = listeTransaction.toString();
            return listeTransaction;
        }
        ArrayList<String> parentHash = new ArrayList<String>();
        for(int i = 0; i < listeTransaction.size(); i+=2){
            String hashed = hashing(listeTransaction.get(i)+listeTransaction.get(i+1), 0);
            parentHash.add(hashed);
        }
        if(listeTransaction.size() %2 == 1){
            String lastHash = listeTransaction.get(listeTransaction.size()-1); //On lui donne le dernier hash
            String lastHashHashed = hashing(lastHash, 0);
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

    public String transaction(String message){
        listeTransaction.add(message);
        return hashing(message, blockChain.getDifficulte());
    }

    protected String hashing(String message, int difficulte){
        do {
            hashBlockCourant = HashUtil.applySha256(String.valueOf(nonce) + message + timeStamp + getHashPrecedent() + hashRootMerkle);
            nonce++;
        }while(!hashBlockCourant.matches("[0]{"+difficulte+"}(.*)")); //Regex : On cherche uniquement [0]{difficulte} et ça fini par ce qu'on veut
        return hashBlockCourant;
    }


}
