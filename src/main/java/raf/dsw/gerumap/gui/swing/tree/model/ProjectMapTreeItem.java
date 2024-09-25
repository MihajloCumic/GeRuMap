package raf.dsw.gerumap.gui.swing.tree.model;

import raf.dsw.gerumap.gui.swing.view.ProjectPane;
import raf.dsw.gerumap.repository.composite.MapNode;

public class ProjectMapTreeItem extends MapTreeItem{

    private ProjectPane projectPane;
    public ProjectMapTreeItem(MapNode nodeModel) {
        super(nodeModel);
        projectPane = new ProjectPane();
    }

    public ProjectPane getProjectPane() {
        return projectPane;
    }

    public void setProjectPane(ProjectPane projectPane) {
        this.projectPane = projectPane;
    }
}
