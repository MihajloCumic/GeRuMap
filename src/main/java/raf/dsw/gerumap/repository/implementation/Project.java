package raf.dsw.gerumap.repository.implementation;

import raf.dsw.gerumap.observer.NotificatoinType;
import raf.dsw.gerumap.repository.composite.MapNode;
import raf.dsw.gerumap.repository.composite.MapNodeComposite;

public class Project extends MapNodeComposite {

    private  int cnt = 1;
    private String filePath = null;

    public Project(String name, MapNode parent){

        super(name, parent);
        this.type = "Project";
    }

    @Override
    public void setType() {
        this.type = "Project";
    }

    @Override
    public void addChild(MapNode child) {
        if(child != null && child instanceof MindMap){
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

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
