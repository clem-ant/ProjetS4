import BlockChain.BlockChain;
import Tools.BCGui;
import Utilisateurs.Creator;
import Utilisateurs.User;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
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
    public static void main(String[] args) throws Exception{
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        BCGui guiBC = new BCGui();
        guiBC.setTitle("BlockChain");
        guiBC.pack();
        guiBC.setLocale(null);
        guiBC.setSize(700,225);
        guiBC.setVisible(true);
        guiBC.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        while(!guiBC.isGo()){
            System.out.print("");
        }
        int difficulte = guiBC.getDiff();
        int nbBlock = guiBC.getNbBlock();
        int nbMaxTransac = guiBC.getDifMax();
        System.out.println("teststestststes");
        System.out.println(difficulte);
        /*Scanner clavier = new Scanner(System.in);
        System.out.print("Combien de blocs voulez vous pour votre blockchain : ");
        int nbBlock = clavier.nextInt();
        System.out.print("Quelle difficulté voulez vous : ");
        int difficulte = clavier.nextInt();
        System.out.print("Nombre maximum de transactions par block : ");
        int nbMaxTransac = clavier.nextInt();*/


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