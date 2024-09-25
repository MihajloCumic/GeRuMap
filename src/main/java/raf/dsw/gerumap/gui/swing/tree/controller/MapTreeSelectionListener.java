package raf.dsw.gerumap.gui.swing.tree.controller;

import raf.dsw.gerumap.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.gui.swing.tree.model.MindMapTreeItem;
import raf.dsw.gerumap.gui.swing.tree.model.ProjectMapTreeItem;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.gui.swing.view.WorkspacePanel;

import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.TreePath;

public class MapTreeSelectionListener implements TreeSelectionListener {

    public static MapTreeItem wasSelected = null;

    @Override
    public void valueChanged(TreeSelectionEvent e) {
        TreePath path = e.getPath();
        MapTreeItem treeItemSelected = (MapTreeItem)path.getLastPathComponent();
        wasSelected = treeItemSelected;
        if(treeItemSelected instanceof ProjectMapTreeItem){
            WorkspacePanel panel =  MainFrame.getInstance().getWorkspacePanel();
           panel.setOpenProject(treeItemSelected.getMapNode().getName());
           panel.removeTabbedPane(panel.getOpenProjectPane());
           panel.addTabbedPane(((ProjectMapTreeItem) treeItemSelected).getProjectPane());
        }
        if(treeItemSelected instanceof MindMapTreeItem){
            WorkspacePanel panel =  MainFrame.getInstance().getWorkspacePanel();
            ProjectMapTreeItem projectMapTreeItem = (ProjectMapTreeItem)treeItemSelected.getParent();
            panel.removeTabbedPane(panel.getOpenProjectPane());
            panel.addTabbedPane( projectMapTreeItem.getProjectPane());
            panel.setOpenProject(projectMapTreeItem.getMapNode().getName());
            projectMapTreeItem.getProjectPane().setSelectedComponent(((MindMapTreeItem)treeItemSelected).getMindMapPanel());

        }
        if(treeItemSelected.getParent() instanceof MindMapTreeItem){
            WorkspacePanel panel =  MainFrame.getInstance().getWorkspacePanel();
            MindMapTreeItem mindMapTreeItem = (MindMapTreeItem) treeItemSelected.getParent();
            ProjectMapTreeItem projectMapTreeItem = (ProjectMapTreeItem)mindMapTreeItem.getParent();
            panel.removeTabbedPane(panel.getOpenProjectPane());
            panel.addTabbedPane( projectMapTreeItem.getProjectPane());
            panel.setOpenProject(projectMapTreeItem.getMapNode().getName());
        }
//        System.out.println("Selektovan cvor:"+ treeItemSelected.getMapNode().getName());
//        System.out.println("getPath: "+e.getPath());
    }
}
