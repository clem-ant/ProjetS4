package BlockChain;

import Utilisateurs.Creator;
import Tools.RandomNumber;

public class BlockChain {
    private int difficulte; //Difficultée de la blockChain
    private int nbBlock; //Nombre de blocs de la blockChain ? Genesis ?
    private Block[] blocks; //Tableau de blocs
    private int NB_TRANSACTION_MAX; //Nombre de transaction max par blocs
    private int nbTransaction = 1;
    private int indexBlock = 1;

    public BlockChain(int difficulte, int nbBlock, Creator createur, int NB_TRANSACTION_MAX) { //Constructeur de BlockChain.BlockChain
        this.NB_TRANSACTION_MAX = NB_TRANSACTION_MAX;
        this.difficulte = difficulte;
        this.nbBlock = nbBlock;
        this.NB_TRANSACTION_MAX = RandomNumber.getRandomNumberInRange(1, NB_TRANSACTION_MAX);
        blocks = new Block[nbBlock];
        blocks[0] = createur.createFirstBlock(this);
        for(int i = 1; i < nbBlock; i++){
            blocks[i] = new Block(this);
        }
    }

    public String transaction(String message){
        if(nbTransaction <= NB_TRANSACTION_MAX){
            nbTransaction++;
            return this.getCurrentBlocks().transaction(message);
        }else{ //TODO Creation de bloc pas un mineur tiré aléatoirement
            NB_TRANSACTION_MAX = RandomNumber.getRandomNumberInRange(1, NB_TRANSACTION_MAX); //On regenère un nombre aléatoire de transaction pour le prochain block.
            getCurrentBlocks().setHashRootMerkle();
            nbTransaction = 0;
            indexBlock++;
            return transaction(message);
        }
    }

    public Block getPreviousBlocks() {
        return blocks[indexBlock-1];
    }
    public Block getCurrentBlocks() {
        return blocks[indexBlock];
    }

    public Block getBlocks(int index) {
        return blocks[index];
    }


    public int getDifficulte() {
        return difficulte;
    }
}
