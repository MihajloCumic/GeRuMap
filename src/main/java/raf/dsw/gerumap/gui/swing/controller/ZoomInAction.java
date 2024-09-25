package raf.dsw.gerumap.gui.swing.controller;

import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.gui.swing.view.MindMapPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class ZoomInAction extends AbstractGerumapAction{
    public ZoomInAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_PLUS, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/resursi/new.svg"));
        putValue(NAME, "Zoom in");
        putValue(SHORT_DESCRIPTION, "Zoom in");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        ((MindMapPanel)MainFrame.getInstance().getWorkspacePanel().getOpenProjectPane().getSelectedComponent()).zoomIn();
    }
}
