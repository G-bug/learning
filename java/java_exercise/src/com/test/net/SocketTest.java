package com.test.net;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * SocketTest 获取当前时间
 *
 * @author g-bug
 * @date 2020/4/12 上午9:54
 */
public class SocketTest {

    public static void main(String[] args) throws IOException {
        try (Socket socket = new Socket("time-a.nist.gov", 13);
             Scanner in = new Scanner(socket.getInputStream(), "UTF-8")) {
            while (in.hasNextLine()) {
                String line = in.nextLine();
                System.out.println(line);
            }
        }
    }
}
