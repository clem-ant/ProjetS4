import BlockChain.BlockChain;

import java.util.Scanner;

/**
 * The type Main.
 */
public class Main {
    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args) {
        int nbBlock, difficulte;
        Scanner clavier = new Scanner(System.in);
        System.out.println("Combien de blocs voulez vous pour votre blockchain : ");
        nbBlock = clavier.nextInt();
        BlockChain blockChain = new BlockChain(1, nbBlock);
        System.out.println(blockChain.getBlocks(2).getTimeStamp());
    }
}