package raf.dsw.gerumap.gui.swing.painters;

import raf.dsw.gerumap.repository.implementation.Element;
import raf.dsw.gerumap.repository.implementation.Pojam;

import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;

public class PojamPainter extends ElementPainter{

    private Shape shape;
    private Color color;

    public PojamPainter(Pojam element){
        super(element);
        shape = new Ellipse2D.Float(((Pojam)element).getPosition().x, ((Pojam)element).getPosition().y,   70, 40);
        this.color = element.getColor();
    }

    @Override
    public void paint(Graphics2D g) {
        int textWidth = g.getFontMetrics().stringWidth(element.getName());
        shape = new Ellipse2D.Float(((Pojam)element).getPosition().x, ((Pojam)element).getPosition().y,   textWidth + 20, textWidth/2 + 10);
        BasicStroke stroke = new BasicStroke(element.getStroke());
        g.setPaint(color);
        g.setStroke(stroke);
        g.draw(shape);
        g.setPaint(Color.lightGray);
        g.fill(shape);
        g.setPaint(Color.black);
        g.drawString(element.getName(), ((Pojam)element).getPosition().x + 10, ((Pojam)element).getPosition().y + textWidth/4 + 10);
    }

    @Override
    public boolean elementAt(Point position) {
        return shape.contains(position);
    }

    @Override
    public void resetColor() {
        this.color = this.getElement().getColor();
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
