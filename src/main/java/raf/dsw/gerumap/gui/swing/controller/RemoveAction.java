package raf.dsw.gerumap.gui.swing.controller;

import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.gui.swing.view.MainFrame;

import raf.dsw.gerumap.repository.composite.MapNode;
import raf.dsw.gerumap.repository.composite.MapNodeComposite;
import raf.dsw.gerumap.repository.implementation.ProjectExplorer;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;


public class RemoveAction extends AbstractGerumapAction{


    public RemoveAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/resursi/new.svg"));
        putValue(NAME, "Remove");
        putValue(SHORT_DESCRIPTION, "Remove");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode() instanceof ProjectExplorer){
            AppCore.getInstance().getMessageGenerator().generateMessage("NODE_CANNOT_BE_DELETED");
            return;
        }
        MapNode node =  MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
        MapNodeComposite parent = (MapNodeComposite)node.getParent();
        if(parent == null) return;
        parent.deleteChild(node);
    }
}
