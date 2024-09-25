package raf.dsw.gerumap.gui.swing;

import raf.dsw.gerumap.core.Gui;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.messagegenerator.Message;
import raf.dsw.gerumap.observer.NotificatoinType;

import javax.swing.*;

public class SwingGui implements Gui {

    private MainFrame mainFrame;

    @Override
    public void start() {
        mainFrame = MainFrame.getInstance();
        mainFrame.setVisible(true);

    }

    @Override
    public void update(Object notification, NotificatoinType type) {
        if(type == NotificatoinType.LOGMESSAGE){
            JOptionPane.showMessageDialog(MainFrame.getInstance(), ((Message)notification).getText(),((Message)notification).getType().toString(), JOptionPane.ERROR_MESSAGE);
        }
    }
}
