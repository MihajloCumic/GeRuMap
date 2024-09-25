package raf.dsw.gerumap.state.implementation;

import raf.dsw.gerumap.gui.swing.command.AbstractCommand;
import raf.dsw.gerumap.gui.swing.command.implementation.MoveCommand;
import raf.dsw.gerumap.gui.swing.painters.ElementPainter;
import raf.dsw.gerumap.gui.swing.painters.PojamPainter;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.gui.swing.view.MindMapPanel;
import raf.dsw.gerumap.repository.implementation.Pojam;
import raf.dsw.gerumap.state.State;

import java.awt.*;
import java.awt.event.MouseEvent;

public class MoveState implements State {

    private static Point startPostition = null;
    private MoveCommand command;
    @Override
    public void misKliknut(MouseEvent e, MindMapPanel mindMapPanel) {
        command = null;
        for(ElementPainter painter: mindMapPanel.getSelectedPainters()){
                if(painter.elementAt(e.getPoint())){
                    startPostition = e.getPoint();
                    command = new MoveCommand(mindMapPanel,startPostition, mindMapPanel.getSelectedPainters());
                    MainFrame.getInstance().getCommandManager().addCommand(command);
                }
        }
    }

    @Override
    public void misPovucen(MouseEvent e, MindMapPanel mindMapPanel) {
        if(startPostition == null) return;
        for(ElementPainter painter: mindMapPanel.getSelectedPainters()){
            if(painter instanceof PojamPainter){
                Pojam pojam = (Pojam)painter.getElement();
                Point pojamPosition = pojam.getPosition();
                Point newPosition = new Point(pojamPosition.x + (e.getPoint().x - startPostition.x), pojamPosition.y + (e.getPoint().y - startPostition.y));
                pojam.setPosition(newPosition);
            }
        }
        startPostition = e.getPoint();
    }

    @Override
    public void misOtpisten(MouseEvent e, MindMapPanel mindMapPanel) {
        if(command != null){
            command.setEndPosition(e.getPoint());
        }
        startPostition = null;
    }
}
