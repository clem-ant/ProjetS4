package Tools;

import BlockChain.BlockChain;
import Utilisateurs.User;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * @author Clément PAYET
 * The type Bc gui, la fenetre en elle meme.
 */
public class BCGui extends JFrame{
    private static boolean go = false;

    private JPanel BlockChain;
    private JTabbedPane tabbedPane1;
    private JButton RUNButton;
    private JTextField nbBlockGui;
    private JTextField nbMaxBlock;
    private JTextField difficulte;
    private JScrollPane jTabScrollPane;
    private JTable table1;
    private JCheckBox jsonCheckBox;
    private JScrollPane Userpanel;
    private JTable table2;
    private int nbBlock;
    private int diff;
    private int difMax;

    /**
     * Instantiates a new Bc gui.
     * @see CreateGui
     */
    public BCGui(){
        add(BlockChain);
        RUNButton.addActionListener(new ActionListener() { //Listener sur le bouton RUN qui permet de mettre go a true et donc d'arreter la boucle dans CreateGUI
            @Override
            public void actionPerformed(ActionEvent e) {
                go = true;
            }
        });
    }



    public JCheckBox getJsonCheckBox() {
        return jsonCheckBox;
    }


    /**
     * Is go boolean.
     *
     * @return the boolean
     */
    public static boolean isGo() {
        return go;
    }


    /**
     * Gets nb block.
     *
     * @return the nb block
     */
    public int getNbBlock() {
        String intermediaire = nbBlockGui.getText();
        this.nbBlock = Integer.parseInt(intermediaire);
        return nbBlock;
    }

    /**
     * Gets diff.
     *
     * @return the diff
     */
    public int getDiff() {
        String intermediaire = difficulte.getText();
        this.difMax = Integer.parseInt(intermediaire);
        return difMax;
    }

    /**
     * Gets dif max.
     *
     * @return the dif max
     */
    public int getDifMax() {
        String intermediaire = nbMaxBlock.getText();
        this.diff = Integer.parseInt(intermediaire);
        return diff;
    }

    /**
     * Sets bc in tab.
     *
     * @param BC the bc
     */
    public void setBCInTab(BlockChain BC) {
        addBCInTab(BC);
    }

    public void setUsersInTab(User[] users){
        addUsersInTab(users);
    }

    /**
     * Ajoute dans la tableau du 2nd onglet toutes les valeurs des blocs
     * @param BC
     */
    private void addBCInTab(BlockChain BC){
        TableModel model = table1.getModel();
        for (int i = 0; i < BC.getNbBlock(); i++) {
            model.setValueAt(i, i, 0);
            model.setValueAt(BC.getBlocks(i).getNonce(), i, 1);
            model.setValueAt(BC.getBlocks(i).getHashMerkleRoot(), i, 2);
            model.setValueAt(BC.getBlocks(i).getHashBlockCourant(), i, 3);
        }
    }

    private void addUsersInTab(User[] users){
        TableModel modelUser = table2.getModel();
        for(int i = 0; i < users.length; i++){
            modelUser.setValueAt(i,i, 0);
            modelUser.setValueAt(users[i].getClass().getSimpleName(), i, 1);
            modelUser.setValueAt(users[i].getNom(), i, 2);
            modelUser.setValueAt(users[i].getHashUserPublic(), i, 3);
            modelUser.setValueAt((users[i].getBnb() + " Bnb" ), i, 4);
        }
    }

    /**
     * Creer le tableau de 10 000 par 4 elements et le tableau des users
     */
    private void createUIComponents() {
        String[] entetesBC = {"Index", "Nonce", "Merkle Root", "Hash du Bloc"};
        String[] entetesUsers = {"Numero", "Type", "Nom", "Hash public", "Argent"};
        Object[][] emptyBC = new Object[10000][4]; //Nombre max de bloc = 10000
        Object[][] emptyUsers = new Object[1000][5];
        table1 = new JTable(emptyBC, entetesBC);
        table2 = new JTable(emptyUsers, entetesUsers);
    }

}
