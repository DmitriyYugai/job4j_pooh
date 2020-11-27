package ru.job4j.pooh.clients.queue;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ClientPost {
    public static void main(String[] args) {
        try (Socket socket = new Socket("localhost", 9000);
             BufferedReader in = new BufferedReader(
                     new InputStreamReader(socket.getInputStream()));
             PrintWriter out = new PrintWriter(new BufferedWriter(
                     new OutputStreamWriter(socket.getOutputStream())), true)) {
            while (true) {
                System.out.println("Enter message:");
                Scanner scanner = new Scanner(System.in);
                String message = scanner.nextLine();
                System.out.println("Your message is: " + message);
                out.println(message);
                if (message.equals("Exit")) {
                    break;
                }
                System.out.println("Answer from the server: ");
                System.out.println(in.readLine());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
