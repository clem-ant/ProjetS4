package Utilisateurs;

import BlockChain.Block;
import HashUtil.HashUtil;

/**
 * @author Clément PAYET
 * The type Mineur.
 * @see User
 */
public class Mineur extends User{
    /**
     * Instantiates a new Mineur.
     *
     * @param nom      the nom
     * @param hashUser the hash user
     * @param Bnb      the bnb
     */
    public Mineur(String nom, String hashUser, int Bnb, int salt) {
        super(nom, hashUser, Bnb, salt);
    }

    /**
     * Mine les blocs afin d'avoir un hash avec {difficulte}[0] (regex).
     *
     * @param difficulte the difficulte
     * @param nonce      the nonce
     * @param block      the block
     * @param recompense the recompense
     * @return the string
     */
    public String mining(int difficulte, int nonce, Block block, int recompense){
        String hashBlockCourant;
        do {
            hashBlockCourant = HashUtil.applySha256(String.valueOf(nonce++) + block.getHashPrecedent() + block.getHashMerkleRoot() + block.getTimeStamp());
        }while(!hashBlockCourant.matches("[0]{"+difficulte+"}(.*)")); //Regex : On cherche uniquement [0]{difficulte} et ça fini par ce qu'on veut
        block.setNonce(nonce);
        this.recevoirBnb(recompense); //On récompense le mineur pour son minage avec 50 Bnb
        //block.transaction("Coinbase envoie " + recompense + " à " + this.getNom() + " qui a miné le bloc");
        return hashBlockCourant;
    }

    /**
     * Check integrity of a block.
     *
     * @param block the block
     * @return the string
     */
    public String checkIntegrity(Block block){
        return HashUtil.applySha256(String.valueOf(block.getNonce()-1 + block.getHashPrecedent() + block.getHashMerkleRoot() + block.getTimeStamp()));
    }
}
