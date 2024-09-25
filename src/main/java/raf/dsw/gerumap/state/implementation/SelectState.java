package raf.dsw.gerumap.state.implementation;

import raf.dsw.gerumap.gui.swing.painters.ElementPainter;
import raf.dsw.gerumap.gui.swing.painters.PojamPainter;
import raf.dsw.gerumap.gui.swing.painters.VezaPainter;
import raf.dsw.gerumap.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.gui.swing.tree.model.MindMapTreeItem;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.gui.swing.view.MindMapPanel;
import raf.dsw.gerumap.repository.implementation.Element;
import raf.dsw.gerumap.repository.implementation.Pojam;
import raf.dsw.gerumap.repository.implementation.Veza;
import raf.dsw.gerumap.state.State;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;

public class SelectState implements State {
    private static ElementPainter selectedPainter = null;

    @Override
    public void misKliknut(MouseEvent e, MindMapPanel mindMapPanel) {
        mindMapPanel.setSelectedPainters(new ArrayList<>());
        for(ElementPainter painter: mindMapPanel.getPainters()){
            if(painter.elementAt(e.getPoint())){
                selectedPainter = painter;
                break;
            }
        }
        if(selectedPainter == null){
            mindMapPanel.setPressedPosition(e.getPoint());
            return;
        }
        mindMapPanel.resetPaintersColors();
        if(selectedPainter instanceof PojamPainter){
            ((PojamPainter) selectedPainter).setColor(new Color(33, 253, 0, 255));
            return;
        }
        ((VezaPainter)selectedPainter).setColor(new Color(33, 253, 0, 255));


    }

    @Override
    public void misPovucen(MouseEvent e, MindMapPanel mindMapPanel) {
        if(selectedPainter != null) return;
        if(mindMapPanel.getPressedPosition() == null) return;
        double x = mindMapPanel.getPressedPosition().x;
        double y = mindMapPanel.getPressedPosition().y;

        double currX = e.getPoint().x;
        double currY = e.getPoint().y;

        //na gore levo
        if(currX < x && currY < y){
            mindMapPanel.getSelectionRectangle().setRect(currX,currY, x - currX, y - currY);
            mindMapPanel.checkIsPainterSelected();
            mindMapPanel.repaint();
            mindMapPanel.setSelectedPainters(new ArrayList<>());
            return;
        }
        //dole levo
        if(currX < x && currY > y){
            mindMapPanel.getSelectionRectangle().setRect(currX, y, x - currX, currY - y);
            mindMapPanel.checkIsPainterSelected();
            mindMapPanel.repaint();
            mindMapPanel.setSelectedPainters(new ArrayList<>());
            return;
        }
        //gore desno
        if(currX > x && currY < y){
            mindMapPanel.getSelectionRectangle().setRect(x, currY, currX - x, y - currY);
            mindMapPanel.checkIsPainterSelected();
            mindMapPanel.repaint();
            mindMapPanel.setSelectedPainters(new ArrayList<>());
            return;
        }
        //dole desno
        mindMapPanel.getSelectionRectangle().setRect(x,y, currX - x, currY - y);
        mindMapPanel.checkIsPainterSelected();
        mindMapPanel.repaint();
        mindMapPanel.setSelectedPainters(new ArrayList<>());
    }

    @Override
    public void misOtpisten(MouseEvent e, MindMapPanel mindMapPanel) {
        if(selectedPainter != null){
            mindMapPanel.getSelectedPainters().add(selectedPainter);
            mindMapPanel.repaint();
            selectedPainter = null;
            return;
        }
        mindMapPanel.checkIsPainterSelected();
        mindMapPanel.setSelectionRectangle(new Rectangle2D.Float());
        mindMapPanel.setPressedPosition(new Point());
        mindMapPanel.repaint();
    }
}
