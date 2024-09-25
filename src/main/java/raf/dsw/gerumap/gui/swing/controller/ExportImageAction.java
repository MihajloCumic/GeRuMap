package raf.dsw.gerumap.gui.swing.controller;

import raf.dsw.gerumap.gui.swing.tree.model.MindMapTreeItem;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.gui.swing.view.MindMapPanel;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ExportImageAction extends AbstractGerumapAction{
    public ExportImageAction(){
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/resursi/info.svg"));
        putValue(NAME, "Export");
        putValue(SHORT_DESCRIPTION, "Export");
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        MindMapPanel map = (MindMapPanel) MainFrame.getInstance().getWorkspacePanel().getOpenProjectPane().getSelectedComponent();
        MainFrame.getInstance().getProjectExplorer().setSelectionPath(new TreePath(map.getMindMapTreeItem()));

        JFileChooser fileChooser = new JFileChooser();
        File chosenFile = null;

        if(fileChooser.showSaveDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION){
            chosenFile = fileChooser.getSelectedFile();
        }
        MindMapPanel mapPanel = null;
        if(MainFrame.getInstance().getMapTree().getSelectedNode() instanceof MindMapTreeItem){
            mapPanel = ((MindMapTreeItem) MainFrame.getInstance().getMapTree().getSelectedNode()).getMindMapPanel();
        }
        if(mapPanel == null) return;

        BufferedImage image = new BufferedImage(mapPanel.getWidth(), mapPanel.getHeight(), BufferedImage.TYPE_INT_RGB);
        Graphics2D g2 = image.createGraphics();
        mapPanel.printAll(g2);
        g2.dispose();

        try {
            ImageIO.write(image, "png", new File(chosenFile + ".png"));
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }

    }
}
