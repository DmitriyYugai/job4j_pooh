package ru.job4j.pooh.modes;

import ru.job4j.pooh.pojo.MessagePojo;

import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public interface Mode {
    boolean accept(String[] input);

    void run(String[] input, ConcurrentHashMap<String, BlockingQueue<MessagePojo>> map,
             ConcurrentHashMap<String, BlockingQueue<MessagePojo>> topic,
             List<Runnable> tasks, PrintWriter out);
}
