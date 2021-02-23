package BlockChain;

import java.util.Date;

public class BlockChain {
    private int difficulte; //Difficultée de la blockChain
    private int nbBlock; //Nombre de blocs de la blockChain ? Genesis ?
    private Blocks[] blocks; //Tableau de blocs
    private String hashPrecedent;
    //private char[] UTXO;

    public BlockChain(int difficulte, int nbBlock) { //Constructeur de BlockChain.BlockChain
        this.difficulte = difficulte;
        this.nbBlock = nbBlock;
        blocks = new Blocks[nbBlock];
        for(int i = 1; i < nbBlock; i++){
            blocks[i] = new Blocks(i);
        }
        hashPrecedent = new Genesis(0).initGenesis();
    }

    public Blocks getBlocks(int index) {
        return blocks[index];
    }

    public void CalculOfAllTheBlocks(){
        System.out.println("Hash Genesis  : " + hashPrecedent);
        for(int i = 1; i < nbBlock; i++){
            hashPrecedent = blocks[i].hashing("Un message random qui donne 1000 Bnb", hashPrecedent);
            System.out.println("Hash numero " + i + " : " + hashPrecedent);
        }
    }

    public void CalculFrom0ToI(int index){
        System.out.println("Hash Genesis  : " + hashPrecedent);
        for(int i = 1; i < index; i++){
            hashPrecedent = blocks[i].hashing("Un message random qui donne 1000 Bnb", hashPrecedent);
            System.out.println("Hash numero " + i + " : " + hashPrecedent);
        }
    }

}
