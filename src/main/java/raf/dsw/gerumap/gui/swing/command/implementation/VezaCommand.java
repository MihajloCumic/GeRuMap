package raf.dsw.gerumap.gui.swing.command.implementation;

import raf.dsw.gerumap.gui.swing.command.AbstractCommand;
import raf.dsw.gerumap.gui.swing.painters.ElementPainter;
import raf.dsw.gerumap.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.gui.swing.tree.model.MindMapTreeItem;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.gui.swing.view.MindMapPanel;
import raf.dsw.gerumap.repository.implementation.Element;

import javax.swing.tree.TreePath;

public class VezaCommand extends ElementCommand {
    public VezaCommand(MindMapPanel mapPanel, ElementPainter painter){
        super(mapPanel, painter);
    }
}
