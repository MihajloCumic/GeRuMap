package raf.dsw.gerumap.state;

import raf.dsw.gerumap.gui.swing.view.MindMapPanel;

import java.awt.event.MouseEvent;

public interface State {
    public void misKliknut(MouseEvent e, MindMapPanel mindMapPanel);
    public void misPovucen(MouseEvent e, MindMapPanel mindMapPanel);
    public void misOtpisten(MouseEvent e, MindMapPanel mindMapPanel);
}
