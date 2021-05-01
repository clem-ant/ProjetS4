package Tools;

import BlockChain.BlockChain;

import javax.swing.*;
import javax.swing.table.TableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BCGui extends JFrame{
    private static boolean go = false;
    private BlockChain BC;

    private JPanel BlockChain;
    private JTabbedPane tabbedPane1;
    private JButton RUNButton;
    private JTextField nbBlockGui;
    private JTextField nbMaxBlock;
    private JTextField difficulte;
    private JScrollPane jTabScrollPane;
    private JTable table1;
    private int nbBlock;
    private int diff;
    private int difMax;

    public BCGui(){
        add(BlockChain);
        RUNButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                go = true;
            }
        });
    }

    public static boolean isGo() {
        return go;
    }



    public int getNbBlock() {
        String intermediaire = nbBlockGui.getText();
        this.nbBlock = Integer.parseInt(intermediaire);
        return nbBlock;
    }

    public int getDiff() {
        String intermediaire = difficulte.getText();
        this.difMax = Integer.parseInt(intermediaire);
        return difMax;
    }

    public int getDifMax() {
        String intermediaire = nbMaxBlock.getText();
        this.diff = Integer.parseInt(intermediaire);
        return diff;
    }

    public void setBCInTab(BlockChain BC) {
        addInTab(BC);

    }
    private void addInTab(BlockChain BC){
        TableModel model = table1.getModel();
        for (int i = 0; i < BC.getNbBlock(); i++) {
            model.setValueAt(i, i, 0);
            model.setValueAt(BC.getBlocks(i).getNonce(), i, 1);
            model.setValueAt(BC.getBlocks(i).getHashRootMerkle(), i, 2);
            model.setValueAt(BC.getBlocks(i).getHashBlockCourant(), i, 3);
        }
    }

    private void createUIComponents() {
        String[] entetes = {"Index", "Nonce", "Merkle Root", "Hash du Bloc"};
        Object[][] empty = new Object[1000][4]; //Nombre max de bloc = 1000
        table1 = new JTable(empty, entetes);
    }

}
