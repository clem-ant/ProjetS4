package BlockChain;

import java.util.Date;

public class BlockChain {
    private int difficulte; //Difficultée de la blockChain
    private int nbBlock; //Nombre de blocs de la blockChain ? Genesis ?
    private Blocks[] blocks; //Tableau de blocs
    //private char[] UTXO;

    public BlockChain(int difficulte, int nbBlock) { //Constructeur de BlockChain.BlockChain
        this.difficulte = difficulte;
        this.nbBlock = nbBlock;
        blocks = new Blocks[nbBlock];
        for(int i = 0; i < nbBlock; i++){
            blocks[i] = new Blocks(i, new Date());
        }
    }

    public Blocks getBlocks(int index) {
        return blocks[index];
    }
}
