package BlockChain;

import HashUtil.HashUtil;

import java.util.Date;

public class Genesis extends Blocks{
    private int index;
    private Date timeStamp = new Date();
    private String hashBlockCourant;

    public Genesis(int index) {
        super(index);
    }

    public String initGenesis(){
        return hashing("Genesis");
    }

    private String hashing(String message){ //TODO ajouter arbre de Merkle, Surcharge de la methode hashing qui prend un ancien hash
        hashBlockCourant = HashUtil.applySha256(message+timeStamp);
        return 0+hashBlockCourant; //0 = nonce
    }

}
