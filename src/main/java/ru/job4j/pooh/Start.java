package ru.job4j.pooh;

import ru.job4j.pooh.pojo.MessagePojo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Start {

    public static void run(ConcurrentHashMap<String, BlockingQueue<MessagePojo>> map,
                           List<Runnable> tasks, ExecutorService executor,
                           ValidateInput validator) {
        try (ServerSocket server = new ServerSocket(9000)) {
            while (true) {
                Socket socket = server.accept();
                Runnable task = new ClientTask(socket, map, tasks, validator);
                tasks.add(task);
                executor.execute(task);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        ExecutorService executor = Executors.newCachedThreadPool();
        ConcurrentHashMap<String, BlockingQueue<MessagePojo>> map = new ConcurrentHashMap<>();
        List<Runnable> tasks = new ArrayList<>();
        ValidateInput validator = new ValidateInput();
        run(map, tasks, executor, validator);
    }
}
