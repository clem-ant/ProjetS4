import java.util.Date;

public class Blocks {
    private int index; //Genesis = 0
    private Date timeStamp; //La date au moment de la création
    private String hashPrecedent; //Hash du block précédent de la chaine
    private int nbTranstaction;
    private String hashRootMerkle;
    private String hashBlockCourant;
    private int nonce; //En cryptographie, un nonce est un nombre arbitraire destiné à être utilisé une seule fois. Il s'agit souvent d'un nombre aléatoire ou pseudo-aléatoire émis dans un protocole d'authentification pour garantir que les anciennes communications ne peuvent pas être réutilisées dans des attaques par rejeu

    public Blocks(int index, Date timeStamp){
        this.index = index;
        this.timeStamp = timeStamp;
    }

    public Date getTimeStamp() {
        return timeStamp;
    }

    public int getIndex() {
        return index;
    }
}
