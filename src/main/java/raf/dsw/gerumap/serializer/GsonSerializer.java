package raf.dsw.gerumap.serializer;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import raf.dsw.gerumap.core.Serializer;
import raf.dsw.gerumap.repository.composite.MapNode;
import raf.dsw.gerumap.repository.composite.MapNodeComposite;
import raf.dsw.gerumap.repository.implementation.*;

import java.io.*;
import java.lang.reflect.Type;

public class GsonSerializer implements Serializer {
    private final Gson gson = new Gson();

    public GsonSerializer(){}

    @Override
    public Project loadProject(File file) {
        try(FileReader fileReader = new FileReader(file)) {
            RuntimeTypeAdapterFactory<MapNode> adapter = RuntimeTypeAdapterFactory.of(MapNode.class, "type")
                    .registerSubtype(Project.class)
                    .registerSubtype(MindMap.class)
                    .registerSubtype(Pojam.class)
                    .registerSubtype(Veza.class);
            Gson g = new GsonBuilder().registerTypeAdapterFactory(adapter).create();
            return g.fromJson(fileReader, Project.class);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public void saveProject(Project project) {
        try (FileWriter writer = new FileWriter(project.getFilePath())) {
            gson.toJson(project, writer);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
