package raf.dsw.gerumap.core;

import raf.dsw.gerumap.repository.implementation.Project;

import java.io.File;

public interface Serializer {

    public Project loadProject(File file);
    public void saveProject(Project project);
}
