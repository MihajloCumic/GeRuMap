package raf.dsw.gerumap.gui.swing.controller;

import raf.dsw.gerumap.gui.swing.painters.PojamPainter;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.gui.swing.view.MindMapPanel;
import raf.dsw.gerumap.repository.implementation.Pojam;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class CentralniPojamAction extends AbstractGerumapAction{

    public CentralniPojamAction(){
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/resursi/new.svg"));
        putValue(NAME, "Centar");
        putValue(SHORT_DESCRIPTION, "Centralni pojam");
    }

    @Override
    public void actionPerformed(ActionEvent e) {



        MindMapPanel mapPanel = (MindMapPanel) MainFrame.getInstance().getWorkspacePanel().getOpenProjectPane().getSelectedComponent();
        MainFrame.getInstance().getProjectExplorer().setSelectionPath(new TreePath(mapPanel.getMindMapTreeItem()));

        if(mapPanel.isImaCentralniPojam()){
            if(mapPanel.getPainters().contains(mapPanel.getCentralniPainter())){
               return;
            }
            mapPanel.setImaCentralniPojam(false);
        }

        Pojam pojam = new Pojam("CentralniPojam", mapPanel.getMindMapModel());
        Point position = new Point(mapPanel.getWidth()/2 - 50, mapPanel.getHeight()/2 -50);
        pojam.setPosition(position);
        pojam.setColor(new Color(255, 0, 0));
        pojam.setStroke(8);
        PojamPainter painter = new PojamPainter(pojam);

        mapPanel.getPainters().add(painter);
        mapPanel.getMindMapModel().addChild(pojam);

        mapPanel.setImaCentralniPojam(true);
        mapPanel.setCentralniPainter(painter);






    }
}
