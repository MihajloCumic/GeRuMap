package raf.dsw.gerumap.state.implementation;

import raf.dsw.gerumap.gui.swing.command.AbstractCommand;
import raf.dsw.gerumap.gui.swing.command.implementation.DeleteElementCommand;
import raf.dsw.gerumap.gui.swing.painters.ElementPainter;
import raf.dsw.gerumap.gui.swing.painters.PojamPainter;
import raf.dsw.gerumap.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.gui.swing.tree.model.MindMapTreeItem;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.gui.swing.view.MindMapPanel;
import raf.dsw.gerumap.repository.implementation.Element;
import raf.dsw.gerumap.repository.implementation.MindMap;
import raf.dsw.gerumap.state.State;

import javax.swing.tree.TreePath;
import java.awt.event.MouseEvent;

public class DeleteState implements State {

    @Override
    public void misKliknut(MouseEvent e, MindMapPanel mindMapPanel) {
        mindMapPanel.resetPaintersColors();
    }

    @Override
    public void misPovucen(MouseEvent e, MindMapPanel mindMapPanel) {

    }

    @Override
    public void misOtpisten(MouseEvent e, MindMapPanel mindMapPanel) {
        for(ElementPainter painter: mindMapPanel.getPainters()){
            if(painter.elementAt(e.getPoint())){
                AbstractCommand command = new DeleteElementCommand(mindMapPanel, painter);
                MainFrame.getInstance().getCommandManager().addCommand(command);
                return;
            }
        }
    }
}
