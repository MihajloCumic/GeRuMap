package raf.dsw.gerumap.state.implementation;

import raf.dsw.gerumap.gui.swing.command.AbstractCommand;
import raf.dsw.gerumap.gui.swing.command.implementation.VezaCommand;
import raf.dsw.gerumap.gui.swing.painters.ElementPainter;
import raf.dsw.gerumap.gui.swing.painters.PojamPainter;
import raf.dsw.gerumap.gui.swing.painters.VezaPainter;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.gui.swing.view.MindMapPanel;
import raf.dsw.gerumap.repository.implementation.Pojam;
import raf.dsw.gerumap.repository.implementation.Veza;
import raf.dsw.gerumap.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

public class VezaState implements State {
    private static PojamPainter pojamPainterOd = null;
    private static PojamPainter pojamPainterDo = null;
    @Override
    public void misKliknut(MouseEvent e, MindMapPanel mindMapPanel) {
        mindMapPanel.resetPaintersColors();
        for(ElementPainter painter: mindMapPanel.getSelectedPainters()){
            System.out.println(painter.getElement().getName());
        }
        Point position = e.getPoint();
        pojamPainterOd = isItOnPojam(position, mindMapPanel);
    }

    @Override
    public void misPovucen(MouseEvent e, MindMapPanel mindMapPanel) {
        if(pojamPainterOd == null) return;
        Point position = new Point((int) pojamPainterOd.getShape().getBounds2D().getCenterX(), (int) pojamPainterOd.getShape().getBounds2D().getCenterY());
        mindMapPanel.setTmpLinePosition1(position);
        mindMapPanel.setTmpLinePosition2(e.getPoint());
        mindMapPanel.repaint();
    }

    @Override
    public void misOtpisten(MouseEvent e, MindMapPanel mindMapPanel) {
        Point position = e.getPoint();
        pojamPainterDo = isItOnPojam(position, mindMapPanel);
        if(mindMapPanel.doesVezaPainterExist(pojamPainterOd, pojamPainterDo))return;
        if(pojamPainterOd != null && pojamPainterDo != null && pojamPainterOd != pojamPainterDo){
            Veza veza = new Veza("Veza", mindMapPanel.getMindMapModel());
            veza.setPojamOd((Pojam) pojamPainterOd.getElement());
            veza.setPojamDo((Pojam) pojamPainterDo.getElement());
            veza.setColor(Color.BLACK);
            veza.setStroke(3);
            VezaPainter vezaPainter = new VezaPainter(veza, pojamPainterOd, pojamPainterDo);

            AbstractCommand command = new VezaCommand(mindMapPanel, vezaPainter);
            MainFrame.getInstance().getCommandManager().addCommand(command);
        }else {
            mindMapPanel.repaint();
        }


    }

    private PojamPainter isItOnPojam(Point position, MindMapPanel mindMapPanel){
        for(ElementPainter painter: mindMapPanel.getPainters()){
            if(painter instanceof PojamPainter){
                if(painter.elementAt(position)){
                    return (PojamPainter) painter;
                }
            }
        }
        return null;
    }
}
