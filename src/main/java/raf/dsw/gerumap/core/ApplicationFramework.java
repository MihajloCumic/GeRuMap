package raf.dsw.gerumap.core;

import raf.dsw.gerumap.messagegenerator.MessageGenerator;

public abstract class ApplicationFramework {


    protected Gui gui;
    protected MapRepository mapRepository;
    protected MessageGenerator messageGenerator;
    protected Serializer serializer;

    public ApplicationFramework(){

    }


    public abstract void run();

    public void initialise(Gui gui, MapRepository mapRepository, MessageGenerator messageGenerator, Serializer serializer){

        this.gui = gui;
        this.mapRepository = mapRepository;
        this.messageGenerator = messageGenerator;
        this.serializer = serializer;

    }

    public Gui getGui() {
        return gui;
    }

    public void setGui(Gui gui) {
        this.gui = gui;
    }

    public MapRepository getMapRepository() {
        return mapRepository;
    }

    public void setMapRepository(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    public MessageGenerator getMessageGenerator() {
        return messageGenerator;
    }

    public void setMessageGenerator(MessageGenerator messageGenerator) {
        this.messageGenerator = messageGenerator;
    }

    public Serializer getSerializer() {
        return serializer;
    }

    public void setSerializer(Serializer serializer) {
        this.serializer = serializer;
    }
}
