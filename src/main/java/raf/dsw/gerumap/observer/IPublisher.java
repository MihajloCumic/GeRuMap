package raf.dsw.gerumap.observer;

public interface IPublisher {
    public void addSubscriber(ISubscriber sub);
    public void removeSubscriber(ISubscriber sub);
    public void notify(Object notification, NotificatoinType type);
}
