package Utilisateurs;

import BlockChain.BlockChain;
import BlockChain.Block;
import BlockChain.Genesis;

public class Creator extends User {
    public Creator(String nom, String hashUser, int Bnb) {
        super(nom, hashUser, Bnb);
    }

    public Block createFirstBlock(BlockChain bChain){
        Genesis block = new Genesis(bChain);
        return block;
    }
}
