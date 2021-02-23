package BlockChain;
import java.util.Date;
import HashUtil.HashUtil;

public class Blocks {
    private int index; //Block Genesis = 0
    private Date timeStamp = new Date(); //La date au moment de la création
    private String hashPrecedent; //Hash du block précédent de la chaine
    private String hashRootMerkle;
    private String hashBlockCourant;
    private int nbTranstaction;
    private int nonce = 0; //En cryptographie, un nonce est un nombre arbitraire destiné à être utilisé une seule fois. Il s'agit souvent d'un nombre aléatoire ou pseudo-aléatoire émis dans un protocole d'authentification pour garantir que les anciennes communications ne peuvent pas être réutilisées dans des attaques par rejeu

    public Blocks(int index){
        this.index = index;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public int getIndex() {
        return index;
    }

    public String getHashBlockCourant() {
        return hashBlockCourant;
    }

    public String transaction(String input){ //TODO Minage
        return hashing(input, hashPrecedent);
    }


    public String hashing(String message, String ancienHash){ //TODO ajouter arbre de Merkle
        hashBlockCourant = HashUtil.applySha256(message + timeStamp + ancienHash);
        return nonce+hashBlockCourant;
    }

}
