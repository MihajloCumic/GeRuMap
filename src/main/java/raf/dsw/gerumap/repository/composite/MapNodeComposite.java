package raf.dsw.gerumap.repository.composite;

import raf.dsw.gerumap.observer.NotificatoinType;

import java.util.ArrayList;
import java.util.List;

public abstract class MapNodeComposite extends MapNode{

    protected List<MapNode> children;

    public MapNodeComposite(String name, MapNode parent){
        super(name, parent);
        children = new ArrayList<>();

    }

    public abstract void addChild(MapNode child);
    public abstract void deleteChild(MapNode child);

    public List<MapNode> getChildren() {
        return children;
    }

    public void setChildren(List<MapNode> children) {
        this.children = children;
    }

    public void loadChildren(){
        for(MapNode node: this.children){
            node.setType();
            node.setParent(this);
            notify(node, NotificatoinType.ADDCHILD);
        }
    }
}
