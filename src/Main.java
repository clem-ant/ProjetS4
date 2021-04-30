import BlockChain.BlockChain;
import HashUtil.HashUtil;
import Tools.BCJsonUtils;
import Utilisateurs.Creator;
import Utilisateurs.Mineur;
import Utilisateurs.User;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.Arrays;
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
        int nbBlock;
        Scanner clavier = new Scanner(System.in);
        System.out.print("Combien de blocs voulez vous pour votre blockchain : ");
        nbBlock = clavier.nextInt();
        System.out.print("Quelle difficulté voulez vous : ");
        int difficulte = clavier.nextInt();
        System.out.print("Nombre maximum de transactions par block : ");
        int nbMaxTransac = clavier.nextInt();


        Creator simrun = new Creator("Simran", "Jesaispasencore", 20);
        BlockChain blockChain = new BlockChain(difficulte, nbBlock, simrun, nbMaxTransac);
        User[] users = blockChain.createNUsers(100);
        blockChain.remplirBC(users);
        blockChain.printBC();

        //TODO Marche pas
        //BCJsonUtils.BCJsonWriter(blockChain, "test");
        System.out.println(blockChain.checkIntegriteBC());
    }
}