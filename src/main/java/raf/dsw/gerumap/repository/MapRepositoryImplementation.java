package raf.dsw.gerumap.repository;

import raf.dsw.gerumap.core.MapRepository;
import raf.dsw.gerumap.repository.factory.NodeFactory;
import raf.dsw.gerumap.repository.factory.utils.FactoryUtils;
import raf.dsw.gerumap.repository.implementation.ProjectExplorer;

public class MapRepositoryImplementation implements MapRepository {

    private ProjectExplorer projectExplorer;

    public MapRepositoryImplementation(){
        NodeFactory factory = FactoryUtils.getFactory("ProjectExplorer");
        factory.createNode("root", null);
        projectExplorer = (ProjectExplorer) factory.getNode();
    }

    @Override
    public ProjectExplorer getProjectExplorer() {
        return projectExplorer;
    }
}
