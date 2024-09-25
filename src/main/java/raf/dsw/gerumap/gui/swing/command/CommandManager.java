package raf.dsw.gerumap.gui.swing.command;

import raf.dsw.gerumap.core.ApplicationFramework;
import raf.dsw.gerumap.gui.swing.command.implementation.MoveCommand;
import raf.dsw.gerumap.gui.swing.tree.MapTreeImplementation;
import raf.dsw.gerumap.gui.swing.view.MainFrame;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class CommandManager {
    private List<AbstractCommand> commands;
    private int currentCommand = 0;

    public CommandManager(){
        this.commands = new ArrayList<>();
    }

    public void addCommand(AbstractCommand command){
        while(currentCommand < commands.size()){
            commands.remove(currentCommand);
        }
        commands.add(command);
        doCommand();


    }
    public void doCommand(){
        if(currentCommand < commands.size()){
            commands.get(currentCommand++).doCommand();
            SwingUtilities.updateComponentTreeUI(((MapTreeImplementation)MainFrame.getInstance().getMapTree()).getTreeView());
        }

    }

    public void undoCommand(){
        if(currentCommand > 0){
            commands.get(--currentCommand).undoCommand();
            SwingUtilities.updateComponentTreeUI(((MapTreeImplementation)MainFrame.getInstance().getMapTree()).getTreeView());
        }
    }
}
