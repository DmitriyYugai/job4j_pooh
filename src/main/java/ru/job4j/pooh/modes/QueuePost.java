package ru.job4j.pooh.modes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.pooh.pojo.MessagePojo;
import ru.job4j.pooh.pojo.MessageQueuePojo;

import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class QueuePost implements Mode {

    @Override
    public boolean accept(String[] input) {
        return input[0].equals("POST") && input[1].contains("queue");
    }

    @Override
    public void run(String[] input, ConcurrentHashMap<String,
            BlockingQueue<MessagePojo>> map, ConcurrentHashMap<String,
            BlockingQueue<MessagePojo>> topic,
                    List<Runnable> tasks, PrintWriter out) {
        Gson gson = new GsonBuilder().create();
        MessageQueuePojo message = gson.fromJson(input[2], MessageQueuePojo.class);
        if (map.containsKey(message.getQueue())) {
            map.get(message.getQueue()).offer(message);
            out.println("Message was successfully added.");
            return;
        }
        BlockingQueue<MessagePojo> queue = new ArrayBlockingQueue<>(5);
        queue.offer(message);
        map.put(message.getQueue(), queue);
        out.println("Message was successfully added.");
    }

}
