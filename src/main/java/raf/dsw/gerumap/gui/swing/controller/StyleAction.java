package raf.dsw.gerumap.gui.swing.controller;

import raf.dsw.gerumap.gui.swing.painters.ElementPainter;
import raf.dsw.gerumap.gui.swing.painters.VezaPainter;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.gui.swing.view.MindMapPanel;
import raf.dsw.gerumap.gui.swing.view.StylePickerDialog;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.util.regex.Pattern;

public class StyleAction extends AbstractGerumapAction{

    public StyleAction(){
        putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/resursi/new.svg"));
        putValue(NAME, "Style");
        putValue(SHORT_DESCRIPTION, "Style");
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        MindMapPanel mindMapPanel = (MindMapPanel) MainFrame.getInstance().getWorkspacePanel().getOpenProjectPane().getSelectedComponent();

        boolean isNameChangeAllowed = false;
        if(mindMapPanel.getSelectedPainters().size() == 1){
            isNameChangeAllowed = true;
            if(mindMapPanel.getSelectedPainters().get(0) instanceof VezaPainter) isNameChangeAllowed = false;
        }

        StylePickerDialog dialog = new StylePickerDialog(isNameChangeAllowed);

        String name = dialog.getName();
        String stroke = dialog.getStroke();
        Color color = dialog.getColor();

        boolean flagName = false;
        boolean flagStroke = false;
        boolean flagColor = false;

        if(name != null && !name.equalsIgnoreCase("Change name...") && !name.equalsIgnoreCase("")){
            flagName = true;
        }
        Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");
        if(stroke != null && pattern.matcher(stroke).matches()){
            flagStroke = true;
        }
        if(color != null){
            flagColor = true;
        }
        for(ElementPainter painter: mindMapPanel.getSelectedPainters()){
            if(flagName){
                painter.getElement().setName(name);
            }
            if(flagStroke){
                painter.getElement().setStroke(Integer.parseInt(stroke));
            }
            if(flagColor){
                painter.getElement().setColor(color);
            }
        }
    }
}
