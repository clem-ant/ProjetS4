package BlockChain;

import Utilisateurs.Creator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

/**
 * @author Clément PAYET
 * The type Genesis.
 */
public class Genesis extends Block{
    private final ArrayList<String> listeTransactionGenesis = new ArrayList<>(Arrays.asList("Genesis"));
    private final String hashRootMerkleGenesis;
    private final String hashBlockCourantGenesis = "0";

    /**
     * Instantiates a new Genesis.
     *
     * @param blockChain the block chain
     */
    public Genesis(BlockChain blockChain, Creator createur) {
        super(blockChain);
        this.hashRootMerkleGenesis = calculateMerkleRoot(listeTransactionGenesis).toString();
    }

    public String getHashBlockCourant() {
        return hashBlockCourantGenesis;
    }

    @Override
    public ArrayList<String> getListeTransaction() {
        return listeTransactionGenesis;
    }

    public String getHashMerkleRoot() {
        return hashRootMerkleGenesis;
    }
}
