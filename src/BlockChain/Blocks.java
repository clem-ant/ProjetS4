package BlockChain;
import java.util.Date;
import HashUtil.HashUtil;

public class Blocks {
    private int index = 1; //Block Genesis = 0
    private Date timeStamp = new Date(); //La date au moment de la création
    private String hashPrecedent; //Hash du block précédent de la chaine
    private String hashRootMerkle;
    private BlockChain blockChain;
    private String hashBlockCourant;
    private String listeTransaction = "";
    private int nbTranstaction;
    private int nonce = 0; //En cryptographie, un nonce est un nombre arbitraire destiné à être utilisé une seule fois. Il s'agit souvent d'un nombre aléatoire ou pseudo-aléatoire émis dans un protocole d'authentification pour garantir que les anciennes communications ne peuvent pas être réutilisées dans des attaques par rejeu

    public Blocks(BlockChain blockChain){
        this.index = index;
        this.blockChain = blockChain;
    }

    public String getHashPrecedent() {
        hashPrecedent = blockChain.getBlocks(index-1).hashBlockCourant;
        return hashPrecedent;
    }

    public String getHashBlockCourant() {
        return hashBlockCourant;
    }

    public String getListeTransaction() {
        return listeTransaction;
    }

    public String transaction(String message){
        listeTransaction += message;
        return hashing(message, blockChain.getDifficulte());
    }

    public String hashing(String message, int difficulte){ //TODO ajouter arbre de Merkle
        do {
            hashBlockCourant = HashUtil.applySha256(String.valueOf(nonce) + message + timeStamp + getHashPrecedent());
            nonce++;
        }while(!hashBlockCourant.matches("[0]{"+blockChain.getDifficulte()+"}(.*)"));
        return hashBlockCourant;
    }


}
