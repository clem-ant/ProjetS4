package Utilisateurs;

import BlockChain.Block;
import HashUtil.HashUtil;

public class Mineur extends User{
    public Mineur(String nom, String hashUser, int Bnb) {
        super(nom, hashUser, Bnb);
    }

    public String mining(int difficulte, int nonce, Block block){
        String hashBlockCourant;
        do {
            hashBlockCourant = HashUtil.applySha256(String.valueOf(nonce++) + block.getHashPrecedent() + block.getHashMerkleRoot() + block.getTimeStamp());
        }while(!hashBlockCourant.matches("[0]{"+difficulte+"}(.*)")); //Regex : On cherche uniquement [0]{difficulte} et ça fini par ce qu'on veut
        block.setNonce(nonce);
        this.recevoirBnb(50); //On récompense le mineur pour son minage avec 50 Bnb
        return hashBlockCourant;
    }

    public String checkIntegrity(Block block){
        return HashUtil.applySha256(String.valueOf(block.getNonce()-1 + block.getHashPrecedent() + block.getHashMerkleRoot() + block.getTimeStamp()));
    }
}
