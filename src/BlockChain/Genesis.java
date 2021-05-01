package BlockChain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Genesis extends Block{
    private Date time;
    private ArrayList<String> listeTransactionGenesis = new ArrayList<>(Arrays.asList("Genesis"));
    private String hashRootMerkleGenesis;
    private String hashBlockCourantGenesis = "0";

    public Genesis(BlockChain blockChain) {
        super(blockChain);
        this.time = new Date();
        this.hashRootMerkleGenesis = calculateMerkleRoot(listeTransactionGenesis).toString();
    }

    public String getHashRootMerkleGenesis() {
        return hashRootMerkleGenesis;
    }

    public String getHashBlockCourantGenesis() {
        return hashBlockCourantGenesis;
    }

    public ArrayList<String> getListeTransactionGenesis() {
        return listeTransactionGenesis;
    }
}
