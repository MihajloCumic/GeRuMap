package raf.dsw.gerumap.repository.implementation;

import raf.dsw.gerumap.observer.ISubscriber;
import raf.dsw.gerumap.observer.NotificatoinType;
import raf.dsw.gerumap.repository.composite.MapNode;
import raf.dsw.gerumap.repository.composite.MapNodeComposite;

import java.util.ArrayList;
import java.util.List;

public class MindMap extends MapNodeComposite {

    private  int cnt = 1;
    private transient ISubscriber subscriber;

    public MindMap(String name, MapNode parent){

        super(name, parent);
        this.type = "MindMap";
    }

    @Override
    public void setType() {
        this.type = "MindMap";
    }

    @Override
    public void notify(Object notification, NotificatoinType type) {
        if(notification == null || this.subscriber == null || type == null)
            return;
        this.subscriber.update(notification, type);
    }

    @Override
    public void addChild(MapNode child) {
        if(this.children.contains(child)){
            return;}
        if(child != null && child instanceof Element){
            this.children.add(child);
            child.setName(child.getName());
            if(child instanceof Pojam){
                super.notify(child, NotificatoinType.ADDCHILD);
            }
            notify(child, NotificatoinType.REPAINT);
        }
    }

    @Override
    public void deleteChild(MapNode child) {

        if(this.children.contains(child)){
            this.children.remove(child);
            if(child instanceof Pojam){
                super.notify(child, NotificatoinType.REMOVECHILD);
            }
            notify(child, NotificatoinType.REMOVEELEMENT);
        }
    }

    public ISubscriber getSubscriber() {
        return subscriber;
    }

    public void setSubscriber(ISubscriber subscriber) {
        this.subscriber = subscriber;
    }
}
