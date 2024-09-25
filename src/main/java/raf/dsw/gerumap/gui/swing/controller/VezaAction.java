package raf.dsw.gerumap.gui.swing.controller;

import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.gui.swing.view.ProjectPane;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class VezaAction extends AbstractGerumapAction{

    public VezaAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/resursi/new.svg"));
        putValue(NAME, "Veza");
        putValue(SHORT_DESCRIPTION, "Veza");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        ProjectPane projectPane = (ProjectPane) MainFrame.getInstance().getWorkspacePanel().getOpenProjectPane();
        if(projectPane == null) return;
        projectPane.startVezaState();
    }
}
