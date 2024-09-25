package raf.dsw.gerumap.gui.swing.painters;

import raf.dsw.gerumap.repository.implementation.Element;

import java.awt.*;

public abstract class ElementPainter {

    protected Element element;

    public ElementPainter(Element element){
        this.element = element;
    }

    public abstract void paint(Graphics2D g);

    public abstract boolean elementAt(Point position);

    public abstract void resetColor();

    public Element getElement() {
        return element;
    }

    public void setElement(Element element) {
        this.element = element;
    }


}
