package com.test.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * ServerSocket多连接测试
 *
 * @author g-bug
 * @date 2020/4/12 下午11:10
 */
public class ServerSocketConcurrentTest {

    public static void main(String[] args) throws IOException {

        try (ServerSocket serverSocket = new ServerSocket(8189)) {

            int i = 1;

            while (true) {
                Socket socket = serverSocket.accept();
                System.out.println("Spawning " + i);
                Runnable r = new ThreadEchoHandler(socket);
                Thread t = new Thread(r);
                t.start();
                i++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }       
    }
}

class ThreadEchoHandler implements Runnable {

    private Socket socket;

    public ThreadEchoHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void run() {
        try (InputStream inputStream = socket.getInputStream();
             OutputStream outputStream = socket.getOutputStream()) {

            Scanner scanner = new Scanner(inputStream, "UTF-8");

            PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true);
            printWriter.println("enter BYE to exit");

            boolean done = false;
            while (!done && scanner.hasNextLine()) {
                String line = scanner.nextLine();
                System.out.println(line);
                printWriter.println(line);
                if (line.trim().equals("BYE")) {
                    done = true;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
