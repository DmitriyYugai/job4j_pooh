package ru.job4j.pooh.modes;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import ru.job4j.pooh.pojo.MessagePojo;
import ru.job4j.pooh.pojo.MessageTopicPojo;

import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class TopicGet implements Mode {

    @Override
    public boolean accept(String[] input) {
        return input[0].equals("GET") && input[1].contains("topic");
    }

    @Override
    public void run(String[] input, ConcurrentHashMap<String,
            BlockingQueue<MessagePojo>> map, ConcurrentHashMap<String,
            BlockingQueue<MessagePojo>> topic, List<Runnable> tasks, PrintWriter out) {
        if (!topic.containsKey(input[1].split("/")[2])) {
            out.println("No such message");
            return;
        }
        BlockingQueue<MessagePojo> queue = topic.get(input[1].split("/")[2]);
        if (queue.isEmpty()) {
            out.println("No such message");
            return;
        }
        MessageTopicPojo message = (MessageTopicPojo) queue.poll();
        Gson gson = new GsonBuilder().create();
        String json = gson.toJson(message);
        out.println(json);
    }
}
