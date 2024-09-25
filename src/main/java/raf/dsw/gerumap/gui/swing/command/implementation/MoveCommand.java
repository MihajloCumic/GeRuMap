package raf.dsw.gerumap.gui.swing.command.implementation;

import com.sun.tools.javac.Main;
import raf.dsw.gerumap.gui.swing.command.AbstractCommand;
import raf.dsw.gerumap.gui.swing.painters.ElementPainter;
import raf.dsw.gerumap.gui.swing.painters.PojamPainter;
import raf.dsw.gerumap.gui.swing.painters.VezaPainter;
import raf.dsw.gerumap.gui.swing.tree.model.MindMapTreeItem;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.gui.swing.view.MindMapPanel;
import raf.dsw.gerumap.gui.swing.view.ProjectPane;
import raf.dsw.gerumap.repository.implementation.Pojam;

import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.InputEvent;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class MoveCommand implements AbstractCommand {
    private Point startPosition;
    private Point endPosition = null;
    private List<ElementPainter> selectedPainters;

    private MindMapPanel mapPanel;

    public MoveCommand(MindMapPanel mapPanel,Point startPosition, List<ElementPainter> selectedPainters){
        this.startPosition = startPosition;
        this.selectedPainters = new ArrayList<>(selectedPainters);

        this.mapPanel = mapPanel;
    }

    @Override
    public void doCommand() {
        if(endPosition == null) return;
        for(ElementPainter painter: selectedPainters){
            if(painter instanceof PojamPainter){
                Pojam pojam = (Pojam)painter.getElement();
                Point pojamPosition = pojam.getPosition();
                Point newPosition = new Point(pojamPosition.x + (endPosition.x - startPosition.x), pojamPosition.y + (endPosition.y - startPosition.y));
                pojam.setPosition(newPosition);
            }
        }
        simulateClick();
    }

    @Override
    public void undoCommand() {
        if(endPosition == null) return;
        for(ElementPainter painter: selectedPainters){
            if(painter instanceof PojamPainter){
                Pojam pojam = (Pojam)painter.getElement();
                Point pojamPosition = pojam.getPosition();
                Point newPosition = new Point(pojamPosition.x - (endPosition.x - startPosition.x), pojamPosition.y - (endPosition.y - startPosition.y));
                pojam.setPosition(newPosition);
            }
        }

        simulateClick();


    }

    //veza ne prati pjmove kada se izvrsavaju undo/redo za pomeranje
    private void simulateClick(){
        ((ProjectPane)mapPanel.getParent()).getStateManager().setSelectState();
        try{
            Robot bot = new Robot();
            Point beforeClick = MouseInfo.getPointerInfo().getLocation();
            Point p = mapPanel.getLocationOnScreen();
            bot.mouseMove(p.x,p.y);
            bot.mousePress(InputEvent.BUTTON1_DOWN_MASK);
            bot.mouseRelease(InputEvent.BUTTON1_DOWN_MASK);
            bot.mouseMove(beforeClick.x,beforeClick.y);
        }catch (java.awt.AWTException e){
            System.out.println(e);
        }
    }

    public Point getEndPosition() {
        return endPosition;
    }

    public void setEndPosition(Point endPosition) {
        this.endPosition = endPosition;
    }
}
