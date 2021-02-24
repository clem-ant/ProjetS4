package BlockChain;

import Utilisateurs.User;

import java.util.Date;

public class BlockChain {
    private int difficulte; //Difficultée de la blockChain
    private int nbBlock; //Nombre de blocs de la blockChain ? Genesis ?
    private Blocks[] blocks; //Tableau de blocs
    private int NB_TRANSACTION_MAX = 2; //Nombre de transaction max par blocs de la
    private int nbTransaction = 0;
    private int indexBlock = 0;
    //private char[] UTXO;

    public BlockChain(int difficulte, int nbBlock) { //Constructeur de BlockChain.BlockChain
        this.difficulte = difficulte;
        this.nbBlock = nbBlock;
        blocks = new Blocks[nbBlock];
        for(int i = 1; i < nbBlock; i++){
            blocks[i] = new Blocks(i, this);
        }
        String hashPrecedent = new Genesis(0).initGenesis();
    }

    public Blocks getBlocks(int index) {
        return blocks[index];
    }

    /*public void calculOfAllTheBlocks(){
        System.out.println("Hash Genesis  : " + hashPrecedent);
        for(int i = 1; i < nbBlock; i++){
            hashPrecedent = blocks[i].hashing("Un message random qui donne 1000 Bnb", hashPrecedent, difficulte);
            System.out.println("Hash numero " + i + " : " + hashPrecedent);
        }
    }

    public void calculFrom0ToI(int index){
        System.out.println("Hash Genesis  : " + hashPrecedent);
        for(int i = 1; i < index; i++){
            hashPrecedent = blocks[i].hashing("Un message random qui donne 1000 Bnb", hashPrecedent, difficulte);
            System.out.println("Hash numero " + i + " : " + hashPrecedent);
        }
    }*/

    public String transaction(User expediteur, int montant, String message, User destinataire){
        if(nbTransaction < NB_TRANSACTION_MAX){
            expediteur.donnerArgent(destinataire, montant);
            nbTransaction++;
        }else{
            indexBlock++;
        }
    }
}
