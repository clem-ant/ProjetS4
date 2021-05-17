package BlockChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Clément PAYET
 * The type Genesis.
 */
public class Genesis extends Block{
    private final ArrayList<Transaction> listeTransactionGenesis = new ArrayList<>(Arrays.asList(new Transaction("Genesis")));
    private final String hashRootMerkleGenesis;

    /**
     * Instantiates a new Genesis.
     *
     * @param blockChain the block chain
     */
    public Genesis(BlockChain blockChain) {
        super(blockChain);
        Date time = new Date();
        this.hashRootMerkleGenesis = calculateMerkleRoot(listeTransactionGenesis).toString();
    }

    public String getHashMerkleRoot() {
        return hashRootMerkleGenesis;
    }

    public String getHashBlockCourant() {
        String hashBlockCourantGenesis = "0";
        return hashBlockCourantGenesis;
    }

    public ArrayList<Transaction> getListeTransaction() {
        return listeTransactionGenesis;
    }
}
