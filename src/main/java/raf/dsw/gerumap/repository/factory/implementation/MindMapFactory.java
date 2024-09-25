package raf.dsw.gerumap.repository.factory.implementation;

import raf.dsw.gerumap.repository.composite.MapNode;
import raf.dsw.gerumap.repository.factory.NodeFactory;
import raf.dsw.gerumap.repository.implementation.MindMap;

public class MindMapFactory extends NodeFactory {
    @Override
    public NodeFactory createNode(String name, MapNode parent) {
        this.node = new MindMap(name, parent);
        return this;
    }
}
