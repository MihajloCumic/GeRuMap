package raf.dsw.gerumap.repository.factory.implementation;

import raf.dsw.gerumap.repository.composite.MapNode;
import raf.dsw.gerumap.repository.factory.NodeFactory;
import raf.dsw.gerumap.repository.implementation.Element;
import raf.dsw.gerumap.repository.implementation.MindMap;

public class ElementFactory extends NodeFactory {
    @Override
    public NodeFactory createNode(String name, MapNode parent) {

        this.node = new Element(name, parent);
        return this;
    }
}
