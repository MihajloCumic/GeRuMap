package raf.dsw.gerumap.gui.swing.view;

import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.core.ApplicationFramework;
import raf.dsw.gerumap.gui.swing.command.CommandManager;
import raf.dsw.gerumap.gui.swing.controller.ActionManager;
import raf.dsw.gerumap.gui.swing.tree.MapTree;
import raf.dsw.gerumap.gui.swing.tree.MapTreeImplementation;

import javax.swing.*;
import java.awt.*;

public class MainFrame extends JFrame {

    private static MainFrame instance = null;

    private ActionManager actionManager;
    private CommandManager commandManager;

    private MyMenuBar myMenuBar;
    private ToolBar toolBar;
    private MapTree mapTree;
    private JTree projectExplorer;

    private ProjectExplorerPanel projectExplorerPanel;
    private WorkspacePanel workspacePanel;
    private JSplitPane splitPane;

    private MainFrame(){

    }

    private void initialise(){
        actionManager = new ActionManager();
        commandManager = new CommandManager();
        mapTree = new MapTreeImplementation();
        initialiseGUI();
    }
    private void initialiseGUI(){

        Toolkit kit = Toolkit.getDefaultToolkit();
        Dimension screenSize = kit.getScreenSize();
        setSize(screenSize.width/2 + 100, screenSize.height/2 + 50);

        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("GeRuMap");

        myMenuBar = new MyMenuBar();
        setJMenuBar(myMenuBar);

        toolBar = new ToolBar();
        add(toolBar, BorderLayout.NORTH);

        projectExplorer = mapTree.generateTree(AppCore.getInstance().getMapRepository().getProjectExplorer());


        projectExplorerPanel = new ProjectExplorerPanel(projectExplorer);
        workspacePanel = new WorkspacePanel();
        AppCore.getInstance().getMapRepository().getProjectExplorer().addSubscriber(workspacePanel);
        splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, projectExplorerPanel, workspacePanel);
        getContentPane().add(splitPane, BorderLayout.CENTER);
        splitPane.setDividerLocation(200);
        splitPane.setOneTouchExpandable(true);


    }

    public static MainFrame getInstance(){
        if(instance == null){
            instance = new MainFrame();
            instance.initialise();
        }
        return instance;
    }

    public ActionManager getActionManager() {
        return actionManager;
    }

    public MapTree getMapTree() {
        return mapTree;
    }

    public WorkspacePanel getWorkspacePanel() {
        return workspacePanel;
    }

    public void setWorkspacePanel(WorkspacePanel workspacePanel) {
        this.workspacePanel = workspacePanel;
    }

    public JTree getProjectExplorer() {
        return projectExplorer;
    }

    public void setProjectExplorer(JTree projectExplorer) {
        this.projectExplorer = projectExplorer;
    }

    public CommandManager getCommandManager() {
        return commandManager;
    }

    public void setCommandManager(CommandManager commandManager) {
        this.commandManager = commandManager;
    }
}
