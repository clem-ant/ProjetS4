import BlockChain.BlockChain;
import HashUtil.HashUtil;
import Utilisateurs.User;

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
        BlockChain blockChain = new BlockChain(4, nbBlock);
        User tony = new User("Tony", HashUtil.applySha256("Mon Mot de passe je sais pas"), 1);
        System.out.println(tony.getHashUser());
        //blockChain.calculOfAllTheBlocks();
        //blockChain.calculFrom0ToI(5);
    }
}