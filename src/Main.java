import BlockChain.BlockChain;
import Tools.CreateGui;
import Tools.RandomNumber;
import Utilisateurs.Creator;
import Utilisateurs.Mineur;
import Utilisateurs.User;
import static Tools.BCJsonUtils.*;
import javax.swing.*;
import java.util.ArrayList;
import java.util.Arrays;


/**
 * @author Clément PAYET
 * The type Main.
 */
public class Main {

    private static final String ANSI_RESET = "\u001B[0m";
    private static final String ANSI_YELLOW = "\u001B[33m";

    /**
     * Create n users user [ ].
     *
     * @param nbUsers the nb users
     * @return User list with 1 creators, n users and r mineurs
     */
    public static User[] createNUsers(int nbUsers){
        User[] users = new User[nbUsers];
        int nom, type;
        ArrayList<String> listePrenoms = new ArrayList<>(Arrays.asList("joyce","lola","brian","louis", "an", "charles","diane","ravi","achille","nathan","javier","chance","lois","dominique","pedro","karl","rose","james","constance","roland","christine","archibald","renard","angel","mary","sam","stepan","eugenio","eden","justice","bart","alix","guy","mo","alfred","francine","brigitte","iris","eddie","otto","fabia","roger","maurice","louise","camille","luis","sabine","chantal","val","paulo","lin","giselle","caroline","sandra","trace","michel","andré","pauline","jung","colombe","lou","miguel","francisco","gladys","edgar","rodrigo","oscar","young","ion","carl","li","edward","alex","fernando","avril","albert","temple","catherine","lourdes","sage","marin","douglas","gordon","jacqueline","colin","paco","julius","josé","scott","roy","felipe","ariane","marek","denise","london","taylor","hermine","philip","indigo","malvina"));
        users[0] = new Creator(listePrenoms.get(RandomNumber.getRandomNumberInRange(0,1)), listePrenoms.get(RandomNumber.getRandomNumberInRange(0,1)), 0);
        for(int i = 1; i < nbUsers; i++){
            type = RandomNumber.getRandomNumberInRange(0,1);
            nom = RandomNumber.getRandomNumberInRange(0,100-1);
            if(type == 0){
                users[i] = new User(listePrenoms.get(nom), listePrenoms.get(nom), 0, i);
            }else{
                users[i] = new Mineur(listePrenoms.get(nom), listePrenoms.get(nom), 0, i);
            }
        }
        return users;
    }

    /**
     * Print users.
     *
     * @param users the users
     */
    public static void printUsers(User[] users){
        System.out.println("\u001B[33m[Contenu de User]");
        for(int i = 0; i < users.length; i++){
            System.out.println(ANSI_YELLOW + "[User n°"+i+"]" + ANSI_RESET);
            System.out.println("| Type : " + users[i].getClass().getName());
            System.out.println("| Nom  : " + users[i].getNom() + "\n");
        }
    }

    /**
     * Main, Creer les users, la blockchain, appel les fonctions pour faire les transactions et les vérifications
     *
     * @param args the args
     * @throws Exception Si le style NimbusLookAndFeel n'est pas dispo
     * @see CreateGui
     */
    public static void main(String[] args) throws Exception {
        CreateGui guiBC = new CreateGui();
        int difficulte = guiBC.getDifficulte();
        int nbBlock = guiBC.getNbBlock();
        int nbMaxTransac = guiBC.getNbMaxTransac();
        JCheckBox jsonCheckBox = guiBC.getJsonCheckBox();
        User[] users = createNUsers(100);
        Creator simrun = (Creator) users[0];
        BlockChain blockChain = new BlockChain(difficulte, nbBlock, simrun, nbMaxTransac);

        blockChain.transaction("Coinbase donne 5000000000 satoBnb à Creator", simrun, 0);
        simrun.recevoirBnb(50); //Recompense pour avoir créer le genesis par la coinbase
        for(int i = 1; i < users.length; i++){ //On commence à 1 pour pas donner au Createur qui a déjà recu pour la création du genesis+
            blockChain.transaction("Coinbase donne 5000000000 satoBnb à " + users[i].getNom() + " : " + users[i].getHashUserPublic(), blockChain.trouverMineur(users), 0);
            users[i].recevoirBnb(50); //Helicopter money
        }

       blockChain.remplirBC(users); //On fait des transactions aléatoire entre x et y avec un montant aléatoire m

        blockChain.printBC(); //Print dans la console la blockchain
        guiBC.setBC(blockChain); //Rempli le tableau sur l'interface
        guiBC.setUsers(users); //Rempli le tableau des users

        System.out.println(blockChain.checkIntegriteBC(blockChain.trouverMineur(users))); //On demande a un mineur de vérifier que la BC est integre. On choisit au hasard entre les 100 users
        if(jsonCheckBox.isSelected()){
            //printUsers(users);
            BCJsonWriter(blockChain, "BC.json");
        }
        blockChain.initExchangeList(users);

    }
}