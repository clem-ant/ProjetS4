package BlockChain;

import Utilisateurs.User;

import java.util.Date;

public class BlockChain {
    private int difficulte; //Difficultée de la blockChain
    private int nbBlock; //Nombre de blocs de la blockChain ? Genesis ?
    private Blocks[] blocks; //Tableau de blocs
    private int NB_TRANSACTION_MAX = 2; //Nombre de transaction max par blocs de la
    private int nbTransaction = 1;
    private int indexBlock = 0;
    //private char[] UTXO;

    public BlockChain(int difficulte, int nbBlock) { //Constructeur de BlockChain.BlockChain
        this.difficulte = difficulte;
        this.nbBlock = nbBlock;
        blocks = new Blocks[nbBlock];
        blocks[0] = new Genesis(this);
        for(int i = 1; i < nbBlock; i++){
            blocks[i] = new Blocks(this);
        }
    }

    public String transaction(String message){
        if(nbTransaction < NB_TRANSACTION_MAX){

            nbTransaction++;
            return this.getBlocks(indexBlock).transaction(message);
        }else{
            nbTransaction = 0;
            indexBlock++;
            return transaction(message);
        }
    }
    public Blocks getBlocks(int index) {
        return blocks[index];
    }

    public int getDifficulte() {
        return difficulte;
    }
}
