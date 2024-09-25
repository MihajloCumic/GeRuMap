package raf.dsw.gerumap.gui.swing.command.implementation;

import raf.dsw.gerumap.gui.swing.command.AbstractCommand;
import raf.dsw.gerumap.gui.swing.painters.ElementPainter;
import raf.dsw.gerumap.gui.swing.painters.VezaPainter;
import raf.dsw.gerumap.gui.swing.view.MindMapPanel;

import java.util.ArrayList;
import java.util.List;

public class DeleteElementCommand extends ElementCommand {

    private List<VezaPainter> vezaPainters;

    public DeleteElementCommand(MindMapPanel mapPanel, ElementPainter painter){
        super(mapPanel, painter);
        vezaPainters = new ArrayList<>();
    }

    @Override
    public void doCommand() {
        for(ElementPainter painter: this.mapPanel.getPainters()){
            if(painter instanceof VezaPainter){
                VezaPainter vezaPainter = (VezaPainter) painter;
                if(vezaPainter.getPojamPainterOd() == this.painter){
                    vezaPainters.add(vezaPainter);
                    continue;
                }
                if(vezaPainter.getPojamPainterDo() == this.painter){
                    vezaPainters.add(vezaPainter);
                }
            }
        }
        super.undoCommand();
    }

    @Override
    public void undoCommand() {
        for(VezaPainter painter: vezaPainters){
            this.mapPanel.getPainters().add(painter);
            this.mapPanel.getMindMapModel().addChild(painter.getElement());
        }
        super.doCommand();
    }
}
