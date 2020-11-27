package ru.job4j.pooh;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class TopicPost implements Mode {

    @Override
    public boolean accept(String[] input) {
        return input[0].equals("POST") && input[1].contains("topic");
    }

    @Override
    public void run(String[] input, ConcurrentHashMap<String,
            BlockingQueue<MessagePojo>> map, ConcurrentHashMap<String,
            BlockingQueue<MessagePojo>> topic, List<Runnable> tasks, PrintWriter out) {
        Gson gson = new GsonBuilder().create();
        MessageTopicPojo message = gson.fromJson(input[2], MessageTopicPojo.class);
        for (Runnable t : tasks) {
            ClientTask task = (ClientTask) t;
            ConcurrentHashMap<String, BlockingQueue<MessagePojo>> topicMap = task.getTopic();
            if (topicMap.containsKey(message.getTopic())) {
                topicMap.get(message.getTopic()).offer(message);
                continue;
            }
            BlockingQueue<MessagePojo> queue = new ArrayBlockingQueue<>(5);
            queue.offer(message);
            topicMap.put(message.getTopic(), queue);
        }
        out.println("Message was successfully added.");
    }
}
