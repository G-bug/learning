package com.test.net;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * @author g-bug
 * @date 2020/4/12 上午10:52
 */
public class ServerSocketTest {

    public static void main(String[] args) throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(8189);
             // 阻塞等待连接
             Socket socket = serverSocket.accept()) {

            // 客户端 输出
            InputStream inputStream = socket.getInputStream();
            // 客户端 输入
            OutputStream outputStream = socket.getOutputStream();

            // 写入器
            try (Scanner scanner = new Scanner(inputStream, "UTF-8")) {
                // 扫描器
                PrintWriter out = new PrintWriter(new OutputStreamWriter(outputStream, StandardCharsets.UTF_8), true);
                out.println("Enter BYE to exit");

                boolean done = false;
                while (!done && scanner.hasNextLine()) {
                    String line = scanner.nextLine();
                    out.println(line);
                    if (line.trim().equals("BYE")) {
                        done = true;
                    }
                }
            }

        }
    }
}
