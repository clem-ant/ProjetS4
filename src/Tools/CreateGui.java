package Tools;

import BlockChain.BlockChain;
import Utilisateurs.User;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

/**
 * @author Clément PAYET
 * The type Create gui.
 */
public class CreateGui {
    private int difficulte;
    private int nbBlock;
    private int nbMaxTransac;
    private JCheckBox jsonCheckBox;
    private JCheckBox fullCheckBox;
    private BCGui guiBC;
    private static BlockChain BC;




    /**
     * Instantiates a new gui.
     * Elle va attendre qu'on appuie sur RUN pour envoyer au main les valeurs.
     * @throws Exception Si le style NimbusLookAndFeel n'est pas dispo
     */
    public CreateGui() throws Exception{
        UIManager.setLookAndFeel(new NimbusLookAndFeel());
        this.guiBC = new BCGui();
        guiBC.setTitle("BlockChain");
        guiBC.pack();
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension screenSize = toolkit.getScreenSize();
        int x = (screenSize.width - guiBC.getWidth()) / 2;
        int y = (screenSize.height - guiBC.getHeight()) / 2;
        guiBC.setLocation(x, y); //Met la fenêtre au milieu de l'écran
        guiBC.setSize(700,225);
        guiBC.setVisible(true);
        guiBC.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        while(!BCGui.isGo()) {
            System.out.print(""); //Ne peu pas mettre juste ";" sinon elle ne marche pas
        }
        difficulte = guiBC.getDiff();
        nbBlock = guiBC.getNbBlock();
        nbMaxTransac = guiBC.getDifMax();
        jsonCheckBox = guiBC.getJsonCheckBox();
        fullCheckBox = guiBC.getFullCheckBox();

    }

    /**
     * Sets BC dans l'onglet tableau du GUI.
     *
     * @param BC the bc
     */
    public void setBC(BlockChain BC) {
        guiBC.setBCInTab(BC);
    }

    public void setUsers(User[] users){
        guiBC.setUsersInTab(users);
    }

    public JCheckBox getJsonCheckBox() {
        return jsonCheckBox;
    }

    public JCheckBox getFullCheckBox() {
        return fullCheckBox;
    }

    /**
     * Gets difficulte.
     *
     * @return the difficulte
     */
    public int getDifficulte() {
        return difficulte;
    }

    /**
     * Gets nb block.
     *
     * @return the nb block
     */
    public int getNbBlock() {
        return nbBlock;
    }

    /**
     * Gets nb max transac.
     *
     * @return the nb max transac
     */
    public int getNbMaxTransac() {
        return nbMaxTransac;
    }
}
