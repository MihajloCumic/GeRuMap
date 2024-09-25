package raf.dsw.gerumap.gui.swing.tree;

import raf.dsw.gerumap.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.gui.swing.tree.model.MindMapTreeItem;
import raf.dsw.gerumap.gui.swing.tree.model.ProjectMapTreeItem;
import raf.dsw.gerumap.gui.swing.tree.view.MapTreeView;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.gui.swing.view.MindMapPanel;
import raf.dsw.gerumap.observer.ISubscriber;
import raf.dsw.gerumap.observer.NotificatoinType;
import raf.dsw.gerumap.repository.composite.MapNode;
import raf.dsw.gerumap.repository.composite.MapNodeComposite;
import raf.dsw.gerumap.repository.implementation.Element;
import raf.dsw.gerumap.repository.implementation.MindMap;
import raf.dsw.gerumap.repository.implementation.Project;
import raf.dsw.gerumap.repository.implementation.ProjectExplorer;

import javax.swing.*;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreePath;
import java.util.Random;

public class MapTreeImplementation implements MapTree, ISubscriber {
    private MapTreeView treeView;
    private DefaultTreeModel treeModel;

    @Override
    public MapTreeView generateTree(ProjectExplorer projectExplorer) {
        MapTreeItem root = new MapTreeItem(projectExplorer);
        treeModel = new DefaultTreeModel(root);
        treeView = new MapTreeView(treeModel);
        projectExplorer.addSubscriber(this);
        return treeView;
    }

    @Override
    public void addChild(MapTreeItem child) {
        if(!(child instanceof ProjectMapTreeItem) && !(child instanceof MindMapTreeItem)){

        }
        getSelectedNode().add(child);
        update();
    }

    @Override
    public void removeChild(MapTreeItem parent, MapTreeItem child) {
        DefaultMutableTreeNode parentItem = (DefaultMutableTreeNode)child.getParent();
        parentItem.remove(child);
        update();
    }

    private void update(){
        treeView.expandPath(treeView.getSelectionPath());
        SwingUtilities.updateComponentTreeUI(treeView);
    }

    @Override
    public MapTreeItem getSelectedNode() {
        return (MapTreeItem) treeView.getLastSelectedPathComponent();
    }


    @Override
    public void update(Object notification, NotificatoinType type) {

        if (type == NotificatoinType.ADDCHILD) {
            if (notification instanceof Project) {
                addChild(new ProjectMapTreeItem((MapNode) notification));
                return;
            }
            if (notification instanceof MindMap) {
                addChild(new MindMapTreeItem((MapNode) notification));
                return;
            }
            addChild(new MapTreeItem((MapNode) notification));
            return;
        }

        if (type == NotificatoinType.REMOVECHILD) {
            removeChild(null, getSelectedNode());
        }

        if (type == NotificatoinType.RENAME) {
            if (notification instanceof Element && getSelectedNode() instanceof MindMapTreeItem) {
                MindMapTreeItem node = (MindMapTreeItem) getSelectedNode();
                int childCount = node.getChildCount();
                for (int i = 0; i < childCount; i++) {
                    MapTreeItem child = (MapTreeItem) node.getChildAt(i);
                    if (child.getMapNode() == notification) {
                        TreePath path = new TreePath(child.getPath());
                        MainFrame.getInstance().getProjectExplorer().setSelectionPath(path);
                        break;
                    }
                }
                MapTreeItem item = getSelectedNode();
                getSelectedNode().setName(item.getMapNode().getName());
                TreePath path = new TreePath(node.getPath());
                MainFrame.getInstance().getProjectExplorer().setSelectionPath(path);

            }
        }
    }

    public MapTreeView getTreeView() {
        return treeView;
    }

    public void setTreeView(MapTreeView treeView) {
        this.treeView = treeView;
    }
}
