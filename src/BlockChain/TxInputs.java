package BlockChain;
import java.util.ArrayList;

public class TxInputs {
    private String hash; //hash UTXO source
    private int hauteur; // num du bloc contenant la Tx pour payer, tout à 0 si coinbase input
    private long  amount; // montant UTXO source
    private int unlockSize;
    private ArrayList<String> unlockingScript = new ArrayList<>();
    private String comment; // Dans quelle phase on est : marché, helicopter money ou genesis




    public TxInputs( long amount) {
        this.amount=amount;
    }

    public long getAmount() {
        return amount;
    }
}
