package raf.dsw.gerumap.gui.swing.painters;

import raf.dsw.gerumap.repository.implementation.Pojam;
import raf.dsw.gerumap.repository.implementation.Veza;

import java.awt.*;
import java.awt.geom.GeneralPath;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class VezaPainter extends ElementPainter{

    private Shape shape;
    private PojamPainter pojamPainterOd;
    private PojamPainter pojamPainterDo;
    private Color color;

    public VezaPainter(Veza element, PojamPainter pojamPainterOd, PojamPainter pojamPainterDo){
        super(element);
        this.color = element.getColor();
        this.pojamPainterOd = pojamPainterOd;
        this.pojamPainterDo = pojamPainterDo;
    }
    @Override
    public void paint(Graphics2D g) {
        Rectangle2D boundsOd = this.pojamPainterOd.getShape().getBounds2D();
        Rectangle2D boundsDo = this.pojamPainterDo.getShape().getBounds2D();
        float x1 = (float)(boundsOd.getX() + boundsOd.getWidth()/2);
        float y1 = (float) (boundsOd.getY() + boundsOd.getHeight()/2);
        float x2 = (float) (boundsDo.getX() + boundsDo.getWidth()/2);
        float y2 = (float) (boundsDo.getY() + boundsDo.getHeight()/2);
        shape = new Line2D.Float(x1,y1,x2,y2);
        BasicStroke stroke = new BasicStroke(element.getStroke());
        g.setPaint(this.color);
        g.setStroke(stroke);
        g.draw(shape);
    }

    @Override
    public boolean elementAt(Point position) {

        if(((Line2D)shape).ptSegDist(position) < 5){
            return true;
        }
        return false;
    }

    @Override
    public void resetColor() {
        this.color = this.getElement().getColor();
    }

    public PojamPainter getPojamPainterOd() {
        return pojamPainterOd;
    }

    public void setPojamPainterOd(PojamPainter pojamPainterOd) {
        this.pojamPainterOd = pojamPainterOd;
    }

    public PojamPainter getPojamPainterDo() {
        return pojamPainterDo;
    }

    public void setPojamPainterDo(PojamPainter pojamPainterDo) {
        this.pojamPainterDo = pojamPainterDo;
    }

    public Shape getShape() {
        return shape;
    }

    public void setShape(Shape shape) {
        this.shape = shape;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
