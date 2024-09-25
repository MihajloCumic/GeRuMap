package raf.dsw.gerumap.repository.composite;

import raf.dsw.gerumap.observer.IPublisher;
import raf.dsw.gerumap.observer.ISubscriber;
import raf.dsw.gerumap.observer.NotificatoinType;
import raf.dsw.gerumap.repository.implementation.Element;
import raf.dsw.gerumap.repository.implementation.MindMap;

import java.util.ArrayList;
import java.util.List;

public abstract class MapNode implements IPublisher {
    public String type = "node";
    private String name;
    private transient MapNode parent;
    private transient static List<ISubscriber> subscribers;

    public MapNode(String name, MapNode parent){
        this.name = name;
        this.parent = parent;
    }

    public abstract void setType();

    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub == null)
            return;
        if(this.subscribers ==null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(sub == null || this.subscribers == null || !this.subscribers.contains(sub))
            return;
        this.subscribers.remove(sub);
    }

    @Override
    public void notify(Object notification, NotificatoinType type) {
        if(notification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;

        for(ISubscriber listener : subscribers){
            listener.update(notification, type);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
        notify(this, NotificatoinType.RENAME);
        if(this instanceof Element){
            ((MindMap)this.getParent()).notify(this, NotificatoinType.CHANGEELEMENT);
        }
    }

    public MapNode getParent() {
        return parent;
    }

    public void setParent(MapNode parent) {
        this.parent = parent;
    }

    public List<ISubscriber> getSubscribers() {
        return subscribers;
    }

    public void setSubscribers(List<ISubscriber> subscribers) {
        this.subscribers = subscribers;
    }
}
