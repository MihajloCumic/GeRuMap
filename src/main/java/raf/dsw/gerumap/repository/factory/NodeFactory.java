package raf.dsw.gerumap.repository.factory;

import raf.dsw.gerumap.repository.composite.MapNode;
import raf.dsw.gerumap.repository.implementation.MindMap;

public abstract class NodeFactory {

    protected MapNode node;

    public NodeFactory(){}

    public MapNode getNode(){
        return node;
    }

    public abstract NodeFactory createNode(String name, MapNode parent);

}
