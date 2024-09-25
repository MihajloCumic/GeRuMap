package raf.dsw.gerumap;

import raf.dsw.gerumap.core.ApplicationFramework;
import raf.dsw.gerumap.core.Gui;
import raf.dsw.gerumap.core.MapRepository;
import raf.dsw.gerumap.core.Serializer;
import raf.dsw.gerumap.gui.swing.SwingGui;
import raf.dsw.gerumap.logger.ConsoleLogger;
import raf.dsw.gerumap.logger.FileLogger;
import raf.dsw.gerumap.logger.Logger;
import raf.dsw.gerumap.messagegenerator.MessageGenerator;
import raf.dsw.gerumap.messagegenerator.MessageGenereatorImplementation;
import raf.dsw.gerumap.observer.ISubscriber;
import raf.dsw.gerumap.repository.MapRepositoryImplementation;
import raf.dsw.gerumap.serializer.GsonSerializer;

public class AppCore extends ApplicationFramework {

    private static AppCore instance;

    private AppCore(){}

    public static AppCore getInstance(){
        if(instance == null){
            instance =  new AppCore();
        }
        return instance;
    }

    @Override
    public void run() {
        this.gui.start();
    }

    public static void main(String[] args) {
        ApplicationFramework appCore = AppCore.getInstance();
        Gui gui = new SwingGui();
        MapRepository mapRepository = new MapRepositoryImplementation();
        MessageGenerator messageGenerator = new MessageGenereatorImplementation();
        Logger logger = new ConsoleLogger();
        Logger fileLogger = new FileLogger();
        Serializer gsonSerializer = new GsonSerializer();
        messageGenerator.addSubscriber((ISubscriber) logger);
        messageGenerator.addSubscriber((ISubscriber) fileLogger);
        messageGenerator.addSubscriber((ISubscriber)gui);
        appCore.initialise(gui, mapRepository, messageGenerator, gsonSerializer);
        appCore.run();

    }
}
