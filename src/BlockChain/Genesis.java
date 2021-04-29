package BlockChain;

import HashUtil.HashUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

public class Genesis extends Block{
    private Date timeStamp;
    private ArrayList<String> listeTransaction = new ArrayList<>(Arrays.asList("Genesis"));
    private String hashRootMerkle;
    private String hashBlockCourant = "0";

    public Genesis(BlockChain blockChain) {
        super(blockChain);
        this.timeStamp = new Date();
        this.hashRootMerkle = calculateMerkleRoot(listeTransaction).toString();
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
}
