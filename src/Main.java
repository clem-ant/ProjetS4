import BlockChain.BlockChain;
import Tools.BCJsonUtils;
import Tools.CreateGui;
import Tools.RandomNumber;
import Utilisateurs.Creator;
import Utilisateurs.Mineur;
import Utilisateurs.User;

import java.util.ArrayList;
import java.util.Arrays;


/**
 * The type Main.
 */
public class Main {

    public static User[] createNUsers(int nbUsers){
        User[] users = new User[nbUsers];
        int nom, type;
        ArrayList<String> listePrenoms = new ArrayList<>(Arrays.asList("joyce","lola","brian","louis", "an", "charles","diane","ravi","achille","nathan","javier","chance","lois","dominique","pedro","karl","rose","james","constance","roland","christine","archibald","renard","angel","mary","sam","stepan","eugenio","eden","justice","bart","alix","guy","mo","alfred","francine","brigitte","iris","eddie","otto","fabia","roger","maurice","louise","camille","luis","sabine","chantal","val","paulo","lin","giselle","caroline","sandra","trace","michel","andré","pauline","jung","colombe","lou","miguel","francisco","gladys","edgar","rodrigo","oscar","young","ion","carl","li","edward","alex","fernando","avril","albert","temple","catherine","lourdes","sage","marin","douglas","gordon","jacqueline","colin","paco","julius","josé","scott","roy","felipe","ariane","marek","denise","london","taylor","hermine","philip","indigo","malvina"));
        users[0] = new Creator(listePrenoms.get(RandomNumber.getRandomNumberInRange(0,1)), listePrenoms.get(RandomNumber.getRandomNumberInRange(0,1)), 0);
        for(int i = 1; i < nbUsers; i++){
            type = RandomNumber.getRandomNumberInRange(0,1);
            nom = RandomNumber.getRandomNumberInRange(0,nbUsers-1);
            if(type == 0){
                users[i] = new User(listePrenoms.get(nom), listePrenoms.get(nom), 0);
            }else{
                users[i] = new Mineur(listePrenoms.get(nom), listePrenoms.get(nom), 0);
            }
        }
        return users;
    }
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

        User[] users = createNUsers(100);
        Creator simrun = (Creator) users[0];
        BlockChain blockChain = new BlockChain(difficulte, nbBlock, simrun, nbMaxTransac);
        simrun.recevoirBnb(50); //Recompense pour avoir créer le genesis par la coinbase

        for(int i = 0; i < 100; i++){
            users[i].recevoirBnb(50); //Helicopter money
        }

        blockChain.remplirBC(users); //On fait des transactions aléatoire entre x et y avec un montant aléatoire m

        blockChain.printBC(); //Print dans la console la blockchain
        guiBC.setBC(blockChain); //Rempli le tableau sur l'interface
        System.out.println(blockChain.checkIntegriteBC(blockChain.trouverMineur(users))); //On demande a un mineur de vérifier que la BC est integre. On choisit au hasard entre les 100 users

        BCJsonUtils.BCJsonWriter(blockChain, "out.json");
    }
}