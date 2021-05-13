import BlockChain.BlockChain;
import Tools.BCJsonUtils;
import Tools.CreateGui;
import Tools.RandomNumber;
import Utilisateurs.Creator;
import Utilisateurs.Mineur;
import Utilisateurs.User;


/**
 * The type Main.
 */
public class Main {

    /**
     * Main.
     *
     * @param args the args
     */
    public static void main(String[] args) throws Exception {
        CreateGui guiBC = new CreateGui();
        int difficulte = guiBC.getDifficulte();
        int nbBlock = guiBC.getNbBlock();
        int nbMaxTransac = guiBC.getNbMaxTransac();


        Creator simrun = new Creator("Simran", "Jesaispasencore", 50);
        BlockChain blockChain = new BlockChain(difficulte, nbBlock, simrun, nbMaxTransac);
        simrun.recevoirBnb(50); //Recompense pour avoir créer le genesis par la coinbase
        User[] users = blockChain.createNUsers(100);
        for(int i = 0; i < 100; i++){
            users[i].recevoirBnb(50); //Helicopter money
        }

        blockChain.remplirBC(users); //On fait des transactions aléatoire entre x et y avec un montant aléatoire m

        blockChain.printBC(); //Print dans la console la blockchain
        guiBC.setBC(blockChain); //Rempli le tableau sur l'interface
        System.out.println(blockChain.checkIntegriteBC((Mineur)users[RandomNumber.getRandomNumberInRange(0,99)])); //On demande a un mineur de vérifier que la BC est integre. On choisit au hasard entre les 100 users

        BCJsonUtils jsonUtils = new BCJsonUtils();
        BCJsonUtils.BCJsonWriter(blockChain, "out.json");
    }
}