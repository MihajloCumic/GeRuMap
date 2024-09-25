package raf.dsw.gerumap.repository.factory.implementation;

import raf.dsw.gerumap.repository.composite.MapNode;
import raf.dsw.gerumap.repository.factory.NodeFactory;
import raf.dsw.gerumap.repository.implementation.MindMap;
import raf.dsw.gerumap.repository.implementation.Project;
import raf.dsw.gerumap.repository.implementation.ProjectExplorer;

public class ProjectFactory extends NodeFactory {
    @Override
    public NodeFactory createNode(String name, MapNode parent) {

        this.node = new Project(name, parent);
        return this;
    }
}
