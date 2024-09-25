package raf.dsw.gerumap.gui.swing.tree.model;

import raf.dsw.gerumap.gui.swing.view.MindMapPanel;
import raf.dsw.gerumap.repository.composite.MapNode;
import raf.dsw.gerumap.repository.implementation.MindMap;

import javax.swing.*;

public class MindMapTreeItem extends MapTreeItem{
    private MindMapPanel mindMapPanel;


    public MindMapTreeItem(MapNode nodeModel) {
        super(nodeModel);
        mindMapPanel = new MindMapPanel((MindMap) nodeModel, this);
        ((MindMap) nodeModel).setSubscriber(mindMapPanel);
    }

    public MindMapPanel getMindMapPanel() {
        return mindMapPanel;
    }

    public void setMindMapPanel(MindMapPanel mindMapPanel) {
        this.mindMapPanel = mindMapPanel;
    }
}
