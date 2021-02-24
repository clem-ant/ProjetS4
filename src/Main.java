import BlockChain.BlockChain;
import HashUtil.HashUtil;
import Utilisateurs.Mineurs;
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
        User clement = new Mineurs("Clement", HashUtil.applySha256("Mon Mot de passe je sais pas"), 1);
        tony.donnerArgent(clement, 20);
        blockChain.transaction(tony,2, "On sait pas", clement);
    }
}