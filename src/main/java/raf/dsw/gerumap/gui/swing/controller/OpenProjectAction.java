package raf.dsw.gerumap.gui.swing.controller;


import raf.dsw.gerumap.AppCore;
import raf.dsw.gerumap.gui.swing.painters.ElementPainter;
import raf.dsw.gerumap.gui.swing.painters.PojamPainter;
import raf.dsw.gerumap.gui.swing.painters.VezaPainter;
import raf.dsw.gerumap.gui.swing.tree.model.MapTreeItem;
import raf.dsw.gerumap.gui.swing.view.MainFrame;
import raf.dsw.gerumap.gui.swing.view.MindMapPanel;
import raf.dsw.gerumap.repository.composite.MapNode;
import raf.dsw.gerumap.repository.composite.MapNodeComposite;
import raf.dsw.gerumap.repository.implementation.*;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class OpenProjectAction extends AbstractGerumapAction{

    public OpenProjectAction(){
        //putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke(KeyEvent.VK_I, ActionEvent.CTRL_MASK));
        //putValue(SMALL_ICON, loadIcon("/resursi/info.svg"));
        putValue(NAME, "Open");
        putValue(SHORT_DESCRIPTION, "Open");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        JFileChooser fileChooser = new JFileChooser();

        if(fileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            Project project = AppCore.getInstance().getSerializer().loadProject(file);
            project.setParent(AppCore.getInstance().getMapRepository().getProjectExplorer());
            AppCore.getInstance().getMapRepository().getProjectExplorer().addChild(project);


            MapTreeItem node = MainFrame.getInstance().getMapTree().getSelectedNode();

            int childCount = node.getChildCount();
            for (int i = 0; i < childCount; i++) {
                MapTreeItem child = (MapTreeItem) node.getChildAt(i);
                if (child.getMapNode() == project) {
                    TreePath path = new TreePath(child.getPath());
                    MainFrame.getInstance().getProjectExplorer().setSelectionPath(path);
                }
            }

            project.loadChildren();

            node = MainFrame.getInstance().getMapTree().getSelectedNode();
            childCount = node.getChildCount();
            for (MapNode m : project.getChildren()) {
                if(m instanceof MindMap) {

                    for (int i = 0; i < childCount; i++) {
                        MapTreeItem child = (MapTreeItem) node.getChildAt(i);
                        if (child.getMapNode() == m) {
                            TreePath path = new TreePath(child.getPath());
                            MainFrame.getInstance().getProjectExplorer().setSelectionPath(path);
                        }
                    }
                    ((MindMap) m).loadChildren();
                    List<MapNode> elements = ((MindMap) m).getChildren();
                    ((MindMap) m).setChildren(new ArrayList<>());
                    for (MapNode el : elements) {
                        if (el instanceof Element) {
                            ((MindMap) m).addChild(el);
                            MindMapPanel panel = (MindMapPanel) ((MindMap) m).getSubscriber();
                            if (el instanceof Pojam) {
                                panel.getPainters().add(new PojamPainter((Pojam) el));
                                continue;
                            }

                        }
                    }

                    for (MapNode veza : elements) {
                        if (veza instanceof Veza) {
                            MindMapPanel panel = (MindMapPanel) ((MindMap) m).getSubscriber();
                            setVezaPainter((Veza) veza, panel, (MindMap) m);

                        }
                    }
                }
            }
        }
    }

    private void setVezaPainter(Veza veza, MindMapPanel panel, MindMap m){
        PojamPainter pojamOd = null;
        PojamPainter pojamDo = null;
        for(ElementPainter painter: panel.getPainters()){
            if(painter instanceof PojamPainter){
                if(veza.getPojamOd().getPosition().x == ((Pojam)painter.getElement()).getPosition().x && veza.getPojamOd().getPosition().y == ((Pojam)painter.getElement()).getPosition().y){
                    pojamOd = (PojamPainter) painter;
                    continue;
                }
                if(veza.getPojamDo().getPosition().x == ((Pojam)painter.getElement()).getPosition().x && veza.getPojamDo().getPosition().y == ((Pojam)painter.getElement()).getPosition().y){
                    pojamDo = (PojamPainter) painter;
                }

            }
        }

        if(pojamOd == null || pojamDo == null) return;
        panel.getPainters().add(new VezaPainter(veza, pojamOd, pojamDo));
        veza.setPojamOd((Pojam) pojamOd.getElement());
        veza.setPojamDo((Pojam) pojamDo.getElement());
        m.addChild(veza);
        panel.revalidate();
        panel.repaint();
    }
}
