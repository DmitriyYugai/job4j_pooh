package ru.job4j.pooh;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class QueuePost implements Mode {

    @Override
    public boolean accept(String s) {
        return s.contains("POST") && s.contains("queue");
    }

    @Override
    public void run(List<String> input, ConcurrentHashMap<String,
            BlockingQueue<MessagePojo>> map, ConcurrentHashMap<String, BlockingQueue<MessagePojo>> topic,
                    List<Runnable> tasks, PrintWriter out) {
        Gson gson = new GsonBuilder().create();
        MessagePojo message = gson.fromJson(input.get(1), MessagePojo.class);
        if (map.contains(message.getMode())) {
            map.get(message.getMode()).offer(message);
            out.println("Message was successfully added");
            return;
        }
        BlockingQueue queue = new ArrayBlockingQueue<>(5);
        queue.offer(message);
        map.put(message.getMode(), queue);
        out.println("Message was successfully added");
    }

}
