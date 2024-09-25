package raf.dsw.gerumap.state.implementation;

import com.sun.tools.javac.Main;
import raf.dsw.gerumap.gui.swing.command.AbstractCommand;
import raf.dsw.gerumap.gui.swing.command.implementation.PojamCommand;
import raf.dsw.gerumap.gui.swing.painters.ElementPainter;
import raf.dsw.gerumap.gui.swing.painters.PojamPainter;
import raf.dsw.gerumap.gui.swing.painters.VezaPainter;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.gui.swing.view.MindMapPanel;
import raf.dsw.gerumap.gui.swing.view.StylePickerDialog;
import raf.dsw.gerumap.repository.implementation.Pojam;
import raf.dsw.gerumap.state.State;

import javax.swing.tree.TreeModel;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.regex.Pattern;

public class PojamState implements State {

    private static Rectangle2D bounds = null;

    @Override
    public void misKliknut(MouseEvent e, MindMapPanel mindMapPanel) {
        mindMapPanel.resetPaintersColors();
    }

    @Override
    public void misPovucen(MouseEvent e, MindMapPanel mindMapPanel) {
        return;
    }

    @Override
    public void misOtpisten(MouseEvent e, MindMapPanel mindMapPanel) {
        Point position = e.getPoint();

        Pojam pojam = new Pojam("Pojam", mindMapPanel.getMindMapModel());
        pojam.setPosition(position);
        pojam.setColor(new Color(232, 11, 11));
        pojam.setStroke(3);
        PojamPainter painter = new PojamPainter(pojam);

        bounds = painter.getShape().getBounds2D();
        if(!isSpaceFree(mindMapPanel,position, painter)) {
            mindMapPanel.repaint();
            return;
        }

        //Commands
        AbstractCommand command = new PojamCommand(mindMapPanel, painter);
        MainFrame.getInstance().getCommandManager().addCommand(command);


    }

    private boolean isSpaceFree(MindMapPanel mindMapPanel, Point position, PojamPainter currPainter){
        if(bounds == null) return true;
        Point point1 = new Point((int) bounds.getX() + (int)bounds.getWidth(),(int) bounds.getY() + (int)bounds.getHeight());
        Point point2 = new Point((int) bounds.getX() + (int)bounds.getWidth(),(int) bounds.getY());
        Point point3 = new Point((int) bounds.getX(),(int) bounds.getY() + (int)bounds.getHeight());
        Point point4 = new Point((int) bounds.getX() + (int)bounds.getWidth()/2,(int) bounds.getY() + (int)bounds.getHeight()/2);
        Point point5 = new Point((int) bounds.getX() + (int)bounds.getWidth()/2,(int) bounds.getY());
        Point point6 = new Point((int) bounds.getX() ,(int) bounds.getY() + (int)bounds.getHeight()/2);
        for(ElementPainter painter: mindMapPanel.getPainters()){
            if(painter.elementAt(position) || painter.elementAt(point1) || painter.elementAt(point2) || painter.elementAt(point3) || painter.elementAt(point4) || painter.elementAt(point5) || painter.elementAt(point6)){
                return false;
            }
        }
        return true;
    }
}
