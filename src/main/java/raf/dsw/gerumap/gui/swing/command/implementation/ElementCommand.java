package raf.dsw.gerumap.gui.swing.command.implementation;

import raf.dsw.gerumap.gui.swing.command.AbstractCommand;
import raf.dsw.gerumap.gui.swing.painters.ElementPainter;
import raf.dsw.gerumap.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.gui.swing.tree.model.MindMapTreeItem;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.gui.swing.view.MindMapPanel;
import raf.dsw.gerumap.repository.implementation.Element;

import javax.swing.tree.TreePath;

public abstract class ElementCommand implements AbstractCommand {
    protected MindMapPanel mapPanel;
    protected ElementPainter painter;

    public ElementCommand(MindMapPanel mapPanel, ElementPainter painter){
        this.mapPanel = mapPanel;
        this.painter = painter;
    }

    @Override
    public void doCommand() {
        if(mapPanel == null || painter == null) return;
        mapPanel.getPainters().add(painter);
        MainFrame.getInstance().getProjectExplorer().setSelectionPath(new TreePath(mapPanel.getMindMapTreeItem().getPath()));
        mapPanel.getMindMapModel().addChild(painter.getElement());
    }

    @Override
    public void undoCommand() {
        if(mapPanel == null || painter == null) return;
        //mapPanel.getPainters().remove(painter);
        setFocusedElementTreeNode(mapPanel.getMindMapTreeItem(), painter.getElement());
        mapPanel.getMindMapModel().deleteChild(painter.getElement());
    }

    private void setFocusedElementTreeNode(MindMapTreeItem node, Element element){
        int childCount = node.getChildCount();
        for(int i = 0; i < childCount; i++){
            MapTreeItem child = (MapTreeItem) node.getChildAt(i);
            if(child.getMapNode() == element){
                TreePath path = new TreePath(child.getPath());
                MainFrame.getInstance().getProjectExplorer().setSelectionPath(path);
                return;
            }

        }
    }
}
