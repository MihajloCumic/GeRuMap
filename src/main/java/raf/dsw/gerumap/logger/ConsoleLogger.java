package raf.dsw.gerumap.logger;

import raf.dsw.gerumap.messagegenerator.Message;
import raf.dsw.gerumap.observer.ISubscriber;
import raf.dsw.gerumap.observer.NotificatoinType;

public class ConsoleLogger implements Logger, ISubscriber {
    @Override
    public void log(String message) {
        System.out.println(message);
    }

    @Override
    public void update(Object notification, NotificatoinType type) {
        if(type == NotificatoinType.LOGMESSAGE){
            log(((Message)notification).toString());
        }
    }
}
