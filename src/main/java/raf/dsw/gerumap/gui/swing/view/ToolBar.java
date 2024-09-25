package raf.dsw.gerumap.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class ToolBar extends JToolBar {

    public ToolBar(){
        super(HORIZONTAL);
        setFloatable(false);

        setBackground(Color.lightGray);

        add(MainFrame.getInstance().getActionManager().getNewAction());
        add(MainFrame.getInstance().getActionManager().getRemoveAction());
        add(MainFrame.getInstance().getActionManager().getSaveAsAction());
        add(MainFrame.getInstance().getActionManager().getOpenProjectAction());
        add(MainFrame.getInstance().getActionManager().getRedoAction());
        add(MainFrame.getInstance().getActionManager().getUndoAction());
        add(MainFrame.getInstance().getActionManager().getExportImageAction());
        add(MainFrame.getInstance().getActionManager().getInfoAction());


    }
}
