package raf.dsw.gerumap.messagegenerator;

import raf.dsw.gerumap.observer.ISubscriber;
import raf.dsw.gerumap.observer.NotificatoinType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MessageGenereatorImplementation implements MessageGenerator{
    private static List<ISubscriber> subscribers;

    public MessageGenereatorImplementation(){

    }
    @Override
    public Message generateMessage(String generator) {
        Message message =  null;
        if(generator.equalsIgnoreCase("NODE_CANNOT_BE_DELETED")){
            message = new Message("Projext Explorer se ne moze obrisati", MessageType.ERROR, LocalDateTime.now());
        }
        if(generator.equalsIgnoreCase("CANNOT_BE_EMPTY")){
            message = new Message("Polje ne moze biti prazno", MessageType.WARNING, LocalDateTime.now());
        }
        if(generator.equalsIgnoreCase("CANNOT_ADD_CHILD")){
            message = new Message("Element ne moze imati decu", MessageType.NOTIFICATION, LocalDateTime.now());
        }
        notify(message, NotificatoinType.LOGMESSAGE);
        return message;
    }

    @Override
    public void addSubscriber(ISubscriber sub) {
        if(sub == null)
            return;
        if(this.subscribers ==null)
            this.subscribers = new ArrayList<>();
        if(this.subscribers.contains(sub))
            return;
        this.subscribers.add(sub);
    }

    @Override
    public void removeSubscriber(ISubscriber sub) {
        if(sub == null || this.subscribers == null || !this.subscribers.contains(sub))
            return;
        this.subscribers.remove(sub);
    }

    @Override
    public void notify(Object notification, NotificatoinType type) {
        if(notification == null || this.subscribers == null || this.subscribers.isEmpty())
            return;

        for(ISubscriber listener : subscribers){
            listener.update(notification, type);
        }
    }
}
