package raf.dsw.gerumap.gui.swing.view;

import raf.dsw.gerumap.gui.swing.painters.ElementPainter;
import raf.dsw.gerumap.gui.swing.painters.PojamPainter;
import raf.dsw.gerumap.gui.swing.painters.VezaPainter;
import raf.dsw.gerumap.gui.swing.tree.model.MindMapTreeItem;
import raf.dsw.gerumap.observer.ISubscriber;
import raf.dsw.gerumap.observer.NotificatoinType;
import raf.dsw.gerumap.repository.factory.utils.FactoryUtils;
import raf.dsw.gerumap.repository.implementation.Element;
import raf.dsw.gerumap.repository.implementation.MindMap;
import raf.dsw.gerumap.repository.implementation.Pojam;
import raf.dsw.gerumap.state.implementation.SelectState;

import javax.swing.*;
import javax.swing.tree.TreePath;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

public class MindMapPanel extends JPanel implements ISubscriber {

    private List<ElementPainter> painters;
    private MindMap mindMapModel;
    private MindMapTreeItem mindMapTreeItem;
    private Point tmpLinePosition1 = null;
    private Point tmpLinePosition2 = null;

    private Point pressedPosition = null;
    private Rectangle2D selectionRectangle = new Rectangle2D.Float();
    private List<ElementPainter> selectedPainters = new ArrayList<>();

    private double translateX = 0;
    private double translateY = 0;
    private double scaling = 1;
    private AffineTransform transformation = new AffineTransform();

    private boolean imaCentralniPojam = false;
    private PojamPainter centralniPainter = null;


    public MindMapPanel(MindMap mindMapModel, MindMapTreeItem mindMapTreeItem){

        setUpTransformation();
        MouseController mouseController = new MouseController();
        addMouseListener(mouseController);
        addMouseMotionListener(mouseController);
        painters = new ArrayList<>();
        this.mindMapModel = mindMapModel;
        this.mindMapTreeItem = mindMapTreeItem;

    }

    @Override
    public void update(Object notification, NotificatoinType type) {
        if(type == NotificatoinType.REPAINT) this.repaint();
        if(type == NotificatoinType.MOVE) {
            this.repaint();
        }
        if(type == NotificatoinType.REMOVEELEMENT){
            ElementPainter painter = this.getDeletedElement((Element)notification);
            deleteElements(painter);
            this.painters.remove(painter);
            this.repaint();
        }
        if(type == NotificatoinType.CHANGEELEMENT){
            changeElementParameters();
            this.repaint();
        }
    }

    private void changeElementParameters(){
        for(ElementPainter painter: painters){
            painter.resetColor();

        }
    }

    private void deleteElements(ElementPainter painter){
        if(painter instanceof VezaPainter){
            this.painters.remove(painter);
            return;
        }
        List<VezaPainter> toBeDeleted = new ArrayList<>();
        for(ElementPainter vezaPainter: this.painters){
            if(vezaPainter instanceof VezaPainter){
                if(((VezaPainter) vezaPainter).getPojamPainterOd() == painter || ((VezaPainter) vezaPainter).getPojamPainterDo() == painter){
                    toBeDeleted.add((VezaPainter) vezaPainter);
                }
            }
        }
        if(toBeDeleted.isEmpty()) return;
        for(VezaPainter vezaPainter: toBeDeleted){
            this.painters.remove(vezaPainter);
        }
    }

    private ElementPainter getDeletedElement(Element element){
        for(ElementPainter painter: this.painters){
            if(painter.getElement() == element){
                return painter;
            }
        }
        return null;
    }

    private class MouseController extends MouseAdapter{
        @Override
        public void mousePressed(MouseEvent e) {
            //Fokusiranje odgovarujeceg cvora u stablu
            MindMapPanel mindMapPanel = (MindMapPanel)MainFrame.getInstance().getWorkspacePanel().getOpenProjectPane().getSelectedComponent();
            MindMapTreeItem mindMapTreeItem = mindMapPanel.getMindMapTreeItem();
            TreePath path = new TreePath(mindMapTreeItem.getPath());
            MainFrame.getInstance().getProjectExplorer().setSelectionPath(path);



            //Restartovanje selekcija
            if(e.getButton() == MouseEvent.BUTTON1){
                ((ProjectPane)MindMapPanel.this.getParent()).getStateManager().getCurrentState().misKliknut(e, MindMapPanel.this);

            }
        }

        @Override
        public void mouseDragged(MouseEvent e) {
            ((ProjectPane)MindMapPanel.this.getParent()).getStateManager().getCurrentState().misPovucen(e, MindMapPanel.this);

        }



        @Override
        public void mouseReleased(MouseEvent e) {
            if(e.getButton() == MouseEvent.BUTTON1){
                ((ProjectPane)MindMapPanel.this.getParent()).getStateManager().getCurrentState().misOtpisten(e, MindMapPanel.this);
            }
        }
    }


    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //g2.setTransform(transformation);
        for(ElementPainter painter: painters){
            if(painter instanceof VezaPainter){
                painter.paint(g2);
            }
        }
        for(ElementPainter painter: painters){
            if(painter instanceof PojamPainter){
                painter.paint(g2);
            }
        }
        if(pressedPosition != null){
            g2.setPaint(new Color(70, 118, 232, 128));
            g2.draw(selectionRectangle);
            g2.setPaint(new Color(74, 193, 250, 70));
            g2.fill(selectionRectangle);
        }
        if(tmpLinePosition1 != null && tmpLinePosition2 !=null){
            g2.setPaint(new Color(0,0,0, 57));
            g2.drawLine(tmpLinePosition1.x, tmpLinePosition1.y, tmpLinePosition2.x, tmpLinePosition2.y);
            tmpLinePosition1 = null;
            tmpLinePosition2 = null;
        }
    }

    public boolean doesVezaPainterExist(PojamPainter pojamPainterOd, PojamPainter pojamPainterDo){
        for(ElementPainter painter: painters){
            if(painter instanceof VezaPainter){
                if(painter instanceof VezaPainter){
                    if(((VezaPainter) painter).getPojamPainterOd() == pojamPainterOd && ((VezaPainter) painter).getPojamPainterDo() == pojamPainterDo){
                        this.repaint();
                        return true;
                    }
                    if(((VezaPainter) painter).getPojamPainterOd() == pojamPainterDo && ((VezaPainter) painter).getPojamPainterDo() == pojamPainterOd){
                        this.repaint();
                        return true;
                    }

                }
            }
        }
        return false;
    }

    public void checkIsPainterSelected(){
        resetPaintersColors();
        for(ElementPainter painter: painters){
            if(painter instanceof PojamPainter){
                if(selectionRectangle.contains(((Pojam)painter.getElement()).getPosition())){
                    selectedPainters.add(painter);
                    ((PojamPainter) painter).setColor(new Color(33, 253, 0, 255));
                }
                continue;
            }

            if(painter instanceof VezaPainter){
                if(selectedPainters.contains(((VezaPainter) painter).getPojamPainterOd()) && selectedPainters.contains(((VezaPainter) painter).getPojamPainterDo())){
                    selectedPainters.add(painter);
                    ((VezaPainter) painter).setColor(new Color(33, 253, 0, 255));
                }
            }

        }

    }

    public void resetPaintersColors(){
        for(ElementPainter painter: this.painters){
            painter.resetColor();
        }
        this.repaint();
    }

    private void setUpTransformation(){
        //transformation.setToIdentity();
        transformation.scale(scaling, scaling);
        transformation.translate(translateX, translateY);
        repaint();
    }

    public void zoomIn(){
        scaling *= 1.2;
        if(scaling > 3) scaling = 3;
        setUpTransformation();
    }

    public void zoomOut(){
        scaling *= 0.8;
        if(scaling < 0.4) scaling = 0.4;
        setUpTransformation();
    }

    public List<ElementPainter> getPainters() {
        return painters;
    }

    public void setPainters(List<ElementPainter> painters) {
        this.painters = painters;
    }

    public MindMap getMindMapModel() {
        return mindMapModel;
    }

    public void setMindMapModel(MindMap mindMapModel) {
        this.mindMapModel = mindMapModel;
    }

    public MindMapTreeItem getMindMapTreeItem() {
        return mindMapTreeItem;
    }

    public void setMindMapTreeItem(MindMapTreeItem mindMapTreeItem) {
        this.mindMapTreeItem = mindMapTreeItem;
    }

    public Point getTmpLinePosition1() {
        return tmpLinePosition1;
    }

    public void setTmpLinePosition1(Point tmpLinePosition1) {
        this.tmpLinePosition1 = tmpLinePosition1;
    }

    public Point getTmpLinePosition2() {
        return tmpLinePosition2;
    }

    public void setTmpLinePosition2(Point tmpLinePosition2) {
        this.tmpLinePosition2 = tmpLinePosition2;
    }

    public Point getPressedPosition() {
        return pressedPosition;
    }

    public void setPressedPosition(Point pressedPosition) {
        this.pressedPosition = pressedPosition;
    }

    public Rectangle2D getSelectionRectangle() {
        return selectionRectangle;
    }

    public void setSelectionRectangle(Rectangle2D selectionRectangle) {
        this.selectionRectangle = selectionRectangle;
    }

    public List<ElementPainter> getSelectedPainters() {
        return selectedPainters;
    }

    public void setSelectedPainters(List<ElementPainter> selectedPainters) {
        this.selectedPainters = selectedPainters;
    }

    public double getTranslateX() {
        return translateX;
    }

    public void setTranslateX(double translateX) {
        this.translateX = translateX;
    }

    public double getTranslateY() {
        return translateY;
    }

    public void setTranslateY(double translateY) {
        this.translateY = translateY;
    }

    public double getScaling() {
        return scaling;
    }

    public void setScaling(double scaling) {
        this.scaling = scaling;
    }

    public AffineTransform getTransformation() {
        return transformation;
    }

    public void setTransformation(AffineTransform transformation) {
        this.transformation = transformation;
    }

    public boolean isImaCentralniPojam() {
        return imaCentralniPojam;
    }

    public void setImaCentralniPojam(boolean imaCentralniPojam) {
        this.imaCentralniPojam = imaCentralniPojam;
    }

    public PojamPainter getCentralniPainter() {
        return centralniPainter;
    }

    public void setCentralniPainter(PojamPainter centralniPainter) {
        this.centralniPainter = centralniPainter;
    }
}
