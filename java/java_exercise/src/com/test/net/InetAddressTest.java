package com.test.net;

import java.io.IOException;
import java.net.InetAddress;

/**
 * @author g-bug
 * @date 2020/4/12 上午10:19
 */
public class InetAddressTest {

    public static void main(String[] args) throws IOException {
        if (args.length > 0) {

            String host = args[0];
            InetAddress[] addresses = InetAddress.getAllByName(host);
            for (InetAddress address : addresses) {
                System.out.println(address);
            }
        } else {
            InetAddress inetAddress = InetAddress.getLocalHost();
            System.out.println(inetAddress);
        }
    }

}
