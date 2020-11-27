package ru.job4j.pooh.pojo;

public class MessageQueuePojo extends MessagePojo {
    private String queue;

    public MessageQueuePojo() {
    }

    public String getQueue() {
        return queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    @Override
    public String toString() {
        return "MessageQueuePojo{"
                + "queue='" + queue + '\''
                + '}';
    }
}
