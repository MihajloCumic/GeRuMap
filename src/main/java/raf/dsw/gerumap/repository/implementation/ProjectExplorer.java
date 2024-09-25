package raf.dsw.gerumap.repository.implementation;

import raf.dsw.gerumap.observer.NotificatoinType;
import raf.dsw.gerumap.repository.composite.MapNode;
import raf.dsw.gerumap.repository.composite.MapNodeComposite;

public class ProjectExplorer extends MapNodeComposite {

    private int cnt = 1;

    public ProjectExplorer(String name){
        super(name, null);
    }

    @Override
    public void addChild(MapNode child) {
        if(child != null && child instanceof Project){
            this.children.add(child);
            child.setName(child.getName() + cnt++);
            notify(child, NotificatoinType.ADDCHILD);
        }
    }

    @Override
    public void deleteChild(MapNode child) {

        if(this.children.contains(child)){
            this.children.remove(child);
            notify(child, NotificatoinType.REMOVECHILD);
        }
    }


    @Override
    public void setType() {
        return;
    }
}
