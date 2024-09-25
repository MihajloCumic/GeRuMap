package raf.dsw.gerumap.gui.swing.view;

import com.sun.tools.javac.Main;
import raf.dsw.gerumap.gui.swing.tree.controller.MapTreeSelectionListener;
import raf.dsw.gerumap.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.gui.swing.tree.model.MindMapTreeItem;
import raf.dsw.gerumap.gui.swing.tree.model.ProjectMapTreeItem;
import raf.dsw.gerumap.observer.ISubscriber;
import raf.dsw.gerumap.observer.NotificatoinType;
import raf.dsw.gerumap.repository.composite.MapNode;
import raf.dsw.gerumap.repository.composite.MapNodeComposite;
import raf.dsw.gerumap.repository.implementation.MindMap;
import raf.dsw.gerumap.repository.implementation.Project;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.tree.TreePath;
import java.awt.*;

public class WorkspacePanel extends JPanel implements ISubscriber{

    private String openProject;
    private JLabel projectLabel;
    private JTabbedPane openProjectPane = null;
    private WorkspaceToolBar workspaceToolBar;

    public WorkspacePanel(){
        projectLabel = new JLabel("Workspace");
        workspaceToolBar = new WorkspaceToolBar();
        this.setLayout(new BorderLayout());
        this.add(projectLabel, BorderLayout.NORTH);
        this.add(workspaceToolBar, BorderLayout.EAST);
    }

    public void addTabbedPane(JTabbedPane tabbedPane){

        this.add(tabbedPane, BorderLayout.CENTER);
        this.openProjectPane = tabbedPane;
        update();
    }

    public void removeTabbedPane(JTabbedPane tabbedPane){
        if(this.openProjectPane == null) return;
        this.remove(tabbedPane);
        update();
    }

    private void update(){
        this.revalidate();
        this.repaint();
    }

    @Override
    public void update(Object notification, NotificatoinType type) {
        if(!(notification instanceof Project || notification instanceof MindMap)) return;

        if(type == NotificatoinType.ADDCHILD ){
            MapTreeItem item = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode().getLastChild();
            openCreated(item, notification);
        }
        if(type == NotificatoinType.REMOVECHILD ){
              MapTreeItem removeItem = MapTreeSelectionListener.wasSelected;
              removeView(removeItem, notification);
        }
        if(type == NotificatoinType.RENAME ){
            MapTreeItem item = (MapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode();
            rename(item, notification);
        }

    }

    private void openCreated(MapTreeItem item, Object notification){
        ProjectMapTreeItem projectMapTreeItem;
        if(notification instanceof MindMap){
            projectMapTreeItem = (ProjectMapTreeItem)item.getParent();
            projectMapTreeItem.getProjectPane().add(item.getMapNode().getName(),((MindMapTreeItem) item).getMindMapPanel());
        }else projectMapTreeItem = (ProjectMapTreeItem) item;
        this.removeTabbedPane(this.getOpenProjectPane());
        this.addTabbedPane( projectMapTreeItem.getProjectPane());
        this.setOpenProject(projectMapTreeItem.getMapNode().getName());
    }

    private void removeView(MapTreeItem removeItem, Object notification){
        if(notification instanceof Project){
            ((ProjectMapTreeItem)removeItem).getProjectPane();
            this.removeTabbedPane(((ProjectMapTreeItem)removeItem).getProjectPane());
            this.setOpenProject("Workspace");
        }
        if(notification instanceof MindMap){
            this.getOpenProjectPane().remove(((MindMapTreeItem)removeItem).getMindMapPanel());
        }
    }

    private void rename(MapTreeItem item, Object notification){
        if(item instanceof ProjectMapTreeItem){
            this.setOpenProject(((MapNode)notification).getName());
            update();
        }
        if(item instanceof MindMapTreeItem){
            MindMapPanel mindMapPanel = ((MindMapTreeItem) item).getMindMapPanel();
            ProjectPane projectPane = (ProjectPane) mindMapPanel.getParent();
            int index = projectPane.indexOfComponent(mindMapPanel);
            projectPane.setTitleAt(index, ((MapNode)notification).getName());
            update();
        }
    }


    public String getOpenProject() {
        return openProject;
    }

    public void setOpenProject(String openProject) {

        this.openProject = openProject;
        projectLabel.setText(openProject);
    }

    public JLabel getProjectLabel() {
        return projectLabel;
    }

    public void setProjectLabel(JLabel projectLabel) {
        this.projectLabel = projectLabel;
    }

    public JTabbedPane getOpenProjectPane() {
        return openProjectPane;
    }

    public void setOpenProjectPane(JTabbedPane openProjectPane) {
        this.openProjectPane = openProjectPane;
    }

}
