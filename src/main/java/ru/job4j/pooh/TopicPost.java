package ru.job4j.pooh;

import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.function.Predicate;

public class TopicPost implements Mode {

    @Override
    public boolean accept(String s) {
        return false;
    }

    @Override
    public void run(List<String> input, ConcurrentHashMap<String,
            BlockingQueue<MessagePojo>> map, ConcurrentHashMap<String,
            BlockingQueue<MessagePojo>> topic, List<Runnable> tasks, PrintWriter out) {

    }
}
