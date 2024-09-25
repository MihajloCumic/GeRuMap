package raf.dsw.gerumap.gui.swing.controller;

import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.repository.composite.MapNode;
import raf.dsw.gerumap.repository.composite.MapNodeComposite;
import raf.dsw.gerumap.repository.factory.utils.FactoryUtils;
import raf.dsw.gerumap.repository.implementation.MindMap;
import raf.dsw.gerumap.repository.implementation.Project;
import raf.dsw.gerumap.repository.implementation.ProjectExplorer;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class NewAction extends AbstractGerumapAction{

    public NewAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/resursi/new.svg"));
        putValue(NAME, "New");
        putValue(SHORT_DESCRIPTION, "New");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
       MapNode node =  MainFrame.getInstance().getMapTree().getSelectedNode().getMapNode();
       if(!(node instanceof MapNodeComposite)) {
           AppCore.getInstance().getMessageGenerator().generateMessage("CANNOT_ADD_CHILD");
           return;
       }
        MapNode child = null;
       if(node instanceof ProjectExplorer) child = FactoryUtils.getFactory("Project").createNode("Project", node).getNode();
       if(node instanceof Project) child = FactoryUtils.getFactory("MindMap").createNode("MindMap", node).getNode();

         if(node instanceof MindMap) return;
       ((MapNodeComposite) node).addChild(child);

    }
}
