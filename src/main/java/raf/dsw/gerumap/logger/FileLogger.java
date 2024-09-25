package raf.dsw.gerumap.logger;

import raf.dsw.gerumap.messagegenerator.Message;
import raf.dsw.gerumap.observer.ISubscriber;
import raf.dsw.gerumap.observer.NotificatoinType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileLogger implements Logger, ISubscriber {
    //private File file = new File("messages.txt");
    @Override
    public void log(String message) {
        System.out.println("stigo");
        try {
            BufferedWriter writer = new BufferedWriter(new FileWriter("src/main/resources/messages.txt", true));
            writer.append("\n");
            writer.append(message);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void update(Object notification, NotificatoinType type) {
        if(type == NotificatoinType.LOGMESSAGE){
            log(((Message)notification).toString());
        }
    }
}
