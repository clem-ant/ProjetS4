package BlockChain;
import java.util.ArrayList;

public class TxOutputs {
    private ArrayList<String> lockingScript = new ArrayList<>();
    private long amount; // montant de la tx en SatoBnb

    public TxOutputs(long amount) {
        this.amount = amount;
    }

    public long getAmount() {
        return amount;
    }
}
