package com.test.concurrent;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.*;

public class Main {

    public static void main(String[] args) {

        /*ActionListener a = new java.Tim();
        Timer t = new Timer(1000, (e)->Toolkit.getDefaultToolkit().beep());
        t.start();
        JOptionPane.showMessageDialog(null,"确定?");
        System.exit(0);*/


        TreeSet<String> set = new TreeSet<>();
        set.add("vv");
        set.add("vv");
        System.out.println(set.toString());

        LinkedHashMap<Integer, String> cc = new LinkedHashMap<>(10, 0.75f, true);
        cc.put(3, "zz");
        cc.put(1, "cc");
        cc.put(2, "dd");
        cc.get(3);
        System.out.println(cc.toString());

    }
}

class Tim implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        Toolkit.getDefaultToolkit().beep();
    }
}