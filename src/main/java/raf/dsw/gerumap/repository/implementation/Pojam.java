package raf.dsw.gerumap.repository.implementation;

import raf.dsw.gerumap.observer.NotificatoinType;
import raf.dsw.gerumap.repository.composite.MapNode;

import java.awt.*;

public class Pojam extends Element{

    private Point position;
    public Pojam(String name, MapNode parent) {
        super(name, parent);
        this.type = "Pojam";
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(Point position) {
        this.position = position;
        this.getParent().notify(this, NotificatoinType.MOVE);
    }
}
