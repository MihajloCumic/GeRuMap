package raf.dsw.gerumap.gui.swing.controller;

import raf.dsw.gerumap.gui.swing.view.InfoDialog;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.gui.swing.view.ProjectPane;
import raf.dsw.gerumap.gui.swing.view.StylePickerDialog;
import raf.dsw.gerumap.observer.NotificatoinType;
import raf.dsw.gerumap.state.StateManager;


import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class InfoAction extends AbstractGerumapAction{

    public InfoAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/resursi/info.svg"));
        putValue(NAME, "Info");
        putValue(SHORT_DESCRIPTION, "Info");
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        InfoDialog dialog = new InfoDialog();
    }
}
