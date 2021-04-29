package BlockChain;

import Utilisateurs.Creator;
import Tools.RandomNumber;

import java.util.ArrayList;

public class BlockChain {
    private int difficulte; //Difficultée de la blockChain
    private int nbBlock; //Nombre de blocs de la blockChain ? Genesis ?
    private Genesis genesis;
    private Block[] blocks; //Tableau de blocs
    private int NB_TRANSACTION_MAX; //Nombre de transaction max par blocs
    private int nbTransactionMax;
    private int nbTransaction = 1;
    private int indexBlock = 1;

    public BlockChain(int difficulte, int nbBlock, Creator createur, int NB_TRANSACTION_MAX) { //Constructeur de BlockChain.BlockChain
        this.NB_TRANSACTION_MAX = NB_TRANSACTION_MAX;
        this.nbTransactionMax = NB_TRANSACTION_MAX;
        this.difficulte = difficulte;
        this.nbBlock = nbBlock;
        blocks = new Block[nbBlock];
        blocks[0] = new Genesis(this);
        for(int i = 1; i < nbBlock; i++){
            blocks[i] = new Block(this);
        }
    }

    public int getNbTransactionMax() {
        return nbTransactionMax;
    }

    public void transaction(String message){
        if(indexBlock >= nbBlock){
            return;
        }
        //System.out.println("NbTransaction = " + nbTransaction + " NB_Transaction_max = " + nbTransactionMax + " On est au block : " + indexBlock);
        if(nbTransaction <= nbTransactionMax){
            this.getCurrentBlocks().transaction(message);
            nbTransaction++;
        }else{
            nbTransactionMax = RandomNumber.getRandomNumberInRange(1, NB_TRANSACTION_MAX); //On regenère un nombre aléatoire de transaction pour le prochain block.
            this.getCurrentBlocks().calculateHashing();
            this.getCurrentBlocks().setHashRootMerkle();
            nbTransaction = 1;
            indexBlock++;
            transaction(message);

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

    public boolean checkIntegriteBC(){ //TODO je vois pas trop comment faire
        for (int i = 1; i < nbBlock; i++) {
            ArrayList<String> transactions = blocks[i].getListeTransaction();
            if(!blocks[i].verifyHash()){
                return false;
            }
        }
        return true;
    }


}
