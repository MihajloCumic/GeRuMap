package raf.dsw.gerumap.messagegenerator;

import raf.dsw.gerumap.observer.IPublisher;

import java.time.LocalDateTime;

public interface MessageGenerator extends IPublisher {

    public Message generateMessage(String generator);
}
