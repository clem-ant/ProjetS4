import BlockChain.BlockChain;
import HashUtil.HashUtil;
import Tools.BCJsonUtils;
import Utilisateurs.Creator;
import Utilisateurs.Mineur;
import Utilisateurs.User;
import com.google.gson.Gson;

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
        User tony = new User("Tony", "Mot de passe de Tony", 0);
        User clement = new Mineur("Clement", "Mot de passe de Clément", 0);
        //System.out.println("Hash public de Tony : " + tony.getHashUserPublic()); //TODO Clé privée ?
        //System.out.println("Hash public de Clément : " + clement.getHashUserPublic()); //TODO Clé privée ?
        for(int i = 1; i < nbBlock; i++){
            int nbtransactTest = blockChain.getNbTransactionMax();
            int j = 0;
            while(j < nbtransactTest){
                blockChain.transaction("Je donne 2 bonobos a Thomas" + i+j);
                j++;
            }
        }
        for(int i = 0; i < nbBlock; i++){
            System.out.println("===============================================================================");
            System.out.println("Hash Merkle root du block " + i +" : " +blockChain.getBlocks(i).getHashRootMerkle());
            System.out.println("Hash du block courant     " + i +" : " +blockChain.getBlocks(i).getHashBlockCourant());
            System.out.println("Listetransaction du block :  "+ i + " : " + blockChain.getBlocks(i).getListeTransaction());
        }
        BCJsonUtils bcJSon = new BCJsonUtils();
        System.out.println(blockChain.checkIntegriteBC());
        bcJSon.BCJsonWriter(blockChain, "test");
    }
}