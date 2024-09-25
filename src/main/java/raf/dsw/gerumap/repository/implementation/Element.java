package raf.dsw.gerumap.repository.implementation;

import raf.dsw.gerumap.observer.ISubscriber;
import raf.dsw.gerumap.observer.NotificatoinType;
import raf.dsw.gerumap.repository.composite.MapNode;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Element extends MapNode {

    private Color color;
    private int stroke;

    public Element(String name, MapNode parent) {

        super(name, parent);
    }

    @Override
    public void setType() {
        if(this instanceof Veza) {
            this.type = "Veza";
            return;
        }
        this.type = "Pojam";

    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
        ((MindMap)this.getParent()).notify(this, NotificatoinType.CHANGEELEMENT);
    }

    public int getStroke() {
        return stroke;
    }

    public void setStroke(int stroke) {
        this.stroke = stroke;
    }
}
