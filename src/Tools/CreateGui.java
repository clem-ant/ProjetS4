package Tools;

import BlockChain.BlockChain;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;
import java.awt.*;

public class CreateGui {
    private int difficulte;
    private int nbBlock;
    private int nbMaxTransac;
    private BCGui guiBC;
    private static BlockChain BC;


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
            System.out.print("");
        }
        difficulte = guiBC.getDiff();
        nbBlock = guiBC.getNbBlock();
        nbMaxTransac = guiBC.getDifMax();

    }

    public void setBC(BlockChain BC) {
        guiBC.setBCInTab(BC);
    }



    public int getDifficulte() {
        return difficulte;
    }

    public int getNbBlock() {
        return nbBlock;
    }

    public int getNbMaxTransac() {
        return nbMaxTransac;
    }
}
