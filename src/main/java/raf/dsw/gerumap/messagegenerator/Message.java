package raf.dsw.gerumap.messagegenerator;

import java.time.LocalDateTime;

public class Message {
    private String text;
    private MessageType type;
    private LocalDateTime time;

    public Message(String text, MessageType type, LocalDateTime time){
        this.text = text;
        this.type = type;
        this.time = time;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MessageType getType() {
        return type;
    }

    public void setType(MessageType type) {
        this.type = type;
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    @Override
    public String toString() {
        return "["+type+"] ["+time+"] "+ text;
    }
}
