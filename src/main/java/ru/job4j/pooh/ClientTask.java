package ru.job4j.pooh;

import ru.job4j.pooh.modes.*;
import ru.job4j.pooh.pojo.MessagePojo;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

public class ClientTask implements Runnable {
    private final Socket socket;
    private final ValidateInput validator;
    private final ConcurrentHashMap<String, BlockingQueue<MessagePojo>> map;
    private final ConcurrentHashMap<String, BlockingQueue<MessagePojo>> topic =
            new ConcurrentHashMap<>();
    private final List<Runnable> tasks;
    private final Mode[] modes = {new QueuePost(), new QueueGet(), new TopicPost(), new TopicGet()};

    public ClientTask(Socket socket, ConcurrentHashMap<String,
            BlockingQueue<MessagePojo>> map, List<Runnable> tasks, ValidateInput validator) {
        this.socket = socket;
        this.map = map;
        this.tasks = tasks;
        this.validator = validator;
    }

    public ConcurrentHashMap<String, BlockingQueue<MessagePojo>> getTopic() {
        return topic;
    }

    @Override
    public void run() {
        try (PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(socket.getOutputStream())), true);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()))) {
            while (true) {
                String[] input = in.readLine().split(" ");
                if (input[0].equals("Exit")) {
                    System.out.println("Exit");
                    break;
                }
                if (!validator.isValid(input)) {
                    out.println("Wrong input");
                    continue;
                }
                for (Mode mode : modes) {
                    if (mode.accept(input)) {
                        mode.run(input, map, topic, tasks, out);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
