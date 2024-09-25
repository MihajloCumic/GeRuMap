package raf.dsw.gerumap.repository.factory.utils;

import raf.dsw.gerumap.repository.factory.NodeFactory;
import raf.dsw.gerumap.repository.factory.implementation.ElementFactory;
import raf.dsw.gerumap.repository.factory.implementation.MindMapFactory;
import raf.dsw.gerumap.repository.factory.implementation.ProjectExFactory;
import raf.dsw.gerumap.repository.factory.implementation.ProjectFactory;

public final class FactoryUtils {

    public static NodeFactory getFactory(String factory){
        if(factory.equals("ProjectExplorer")) return new ProjectExFactory();
        if(factory.equals("Project")) return new ProjectFactory();
        if(factory.equals("MindMap")) return new MindMapFactory();
        if(factory.equals("Element")) return new ElementFactory();
        return null;
    }
}
