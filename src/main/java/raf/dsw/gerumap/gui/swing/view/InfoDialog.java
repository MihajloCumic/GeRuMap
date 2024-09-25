package raf.dsw.gerumap.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class InfoDialog extends JDialog {

    public InfoDialog(){
        super(MainFrame.getInstance(), "Info", true);
        setLayout(new FlowLayout());
        add(new JLabel("Mihajlo Cumic"));
        setSize(new Dimension(100,100));
        setLocationRelativeTo(MainFrame.getInstance());
        setVisible(true);
    }
}
