package Utilisateurs;

import BlockChain.BlockChain;
import BlockChain.Block;
import BlockChain.Genesis;

/**
 * @author Clément PAYET
 * The type Creator.
 */
public class Creator extends Mineur {
    /**
     * Instantiates a new Creator.
     *
     * @param nom      the nom
     * @param hashUser the hash user
     * @param Bnb      the bnb
     */
    public Creator(String nom, String hashUser, int Bnb) {
        super(nom, hashUser, Bnb, 0);
    }

    /**
     * Create first block of the BC.
     *
     * @param bChain the b chain
     * @return the block
     */
    public Block createFirstBlock(BlockChain bChain){
        return new Genesis(bChain, this);
    }
}
