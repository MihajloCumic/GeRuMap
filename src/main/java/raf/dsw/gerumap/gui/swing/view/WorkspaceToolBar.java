package raf.dsw.gerumap.gui.swing.view;

import javax.swing.*;
import java.awt.*;

public class WorkspaceToolBar extends JToolBar {

    public WorkspaceToolBar(){
        super(VERTICAL);
        setFloatable(false);

        setBackground(Color.lightGray);
        add(MainFrame.getInstance().getActionManager().getCentralniPojamAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getPojamAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getVezaAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getDeleteElementAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getMoveAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getSelectAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getStyleAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getZoomInAction());
        addSeparator();
        add(MainFrame.getInstance().getActionManager().getZoomOutAction());

    }
}
