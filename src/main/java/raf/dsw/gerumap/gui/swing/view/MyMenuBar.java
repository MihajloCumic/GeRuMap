package raf.dsw.gerumap.gui.swing.view;

import javax.swing.*;
import java.awt.event.KeyEvent;

public class MyMenuBar extends JMenuBar {

    public MyMenuBar(){
        JMenu fileMenu = new JMenu("File");
        fileMenu.setMnemonic(KeyEvent.VK_F);
        fileMenu.add(MainFrame.getInstance().getActionManager().getNewAction());
        fileMenu.add(MainFrame.getInstance().getActionManager().getRemoveAction());

        JMenu helpMenu = new JMenu("Help");
        helpMenu.setMnemonic(KeyEvent.VK_H);
        helpMenu.add(MainFrame.getInstance().getActionManager().getInfoAction());

        this.add(fileMenu);
        this.add(helpMenu);

    }
}
