package raf.dsw.gerumap.gui.swing.view;

import raf.dsw.gerumap.state.StateManager;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;

public class ProjectPane extends JTabbedPane {

    private StateManager stateManager;

    public ProjectPane(){
        stateManager = new StateManager();
    }

    public void addTab(String tabName, JPanel panel){

        this.addTab(tabName, panel);

    }



    public void startPojamState(){
        this.stateManager.setPojamState();
    }

    public void startVezaState(){
        this.stateManager.setVezaState();
    }
    public void startDeleteState(){
        this.stateManager.setDeleteState();
    }
    public void startMoveState(){
        this.stateManager.setMoveState();
    }
    public void startSelectState(){
        this.stateManager.setSelectState();
    }

    public StateManager getStateManager() {
        return stateManager;
    }

}
