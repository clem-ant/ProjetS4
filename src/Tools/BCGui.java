package Tools;

import com.sun.tools.javac.Main;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class BCGui extends JFrame{
    private static boolean go;

    private JPanel BlockChain;
    private JTabbedPane tabbedPane1;
    private JButton RUNButton;
    private JTextField a100TextField;
    private JTextField a10TextField;
    private JTextField a4TextField;
    private int nbBlock;
    private int diff;
    private int difMax;

    public static boolean isGo() {
        return go;
    }


    public BCGui(){
        add(BlockChain);



        RUNButton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                go = true;
            }
        });
    }

    public int getNbBlock() {
        String intermediaire = a100TextField.getText();
        this.nbBlock = Integer.parseInt(intermediaire);
        return nbBlock;
    }

    public int getDiff() {
        String intermediaire = a4TextField.getText();
        this.difMax = Integer.parseInt(intermediaire);
        return difMax;
    }

    public int getDifMax() {
        String intermediaire = a10TextField.getText();
        this.diff = Integer.parseInt(intermediaire);
        return diff;
    }
}
