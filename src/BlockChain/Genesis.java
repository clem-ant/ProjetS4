package BlockChain;

import HashUtil.HashUtil;

import java.util.ArrayList;
import java.util.Date;

public class Genesis extends Block{
    private Date timeStamp = new Date();
    private String hashBlockCourant;
    private String hashRootMerkle;

    public Genesis(BlockChain blockChain) {
        super(blockChain);
        hashing("Genesis bonobo");
    }

    private String hashing(String message){
        ArrayList<String> hash = new ArrayList<String>();
        hash.add(message);
        hashBlockCourant = HashUtil.applySha256(message+timeStamp);
        hashRootMerkle = hashBlockCourant;
        return hashBlockCourant;
    }
}
