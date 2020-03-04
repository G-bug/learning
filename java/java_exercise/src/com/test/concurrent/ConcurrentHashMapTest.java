package com.test.concurrent;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.stream.Stream;

/**
 * ConcurrentHashMap 虽是并发集合, 但操作不当仍会有问题
 * 多个线程向同个key添加value时会出现重复添加导致的失败
 * 应用 putIfAbsent(); 已存在则返回null,否则添加成功
 * putIfAbsent(),内部判断和放入是原子性的:加了synchronized
 */
public class ConcurrentHashMapTest {

    static ConcurrentHashMap<String, List<String>> map = new ConcurrentHashMap<>();

    public static void main(String[] args) {

        Thread t = new Thread(() -> {

            List<String> list1 = new CopyOnWriteArrayList();
            list1.add("divice1");
            list1.add("divic2");

            List<String> topic1 = map.putIfAbsent("topic1", list1);
            if (topic1 != null) {
                topic1.addAll(list1);
            }

            Stream.of(map).forEach(System.out::println);
        });

        Thread t1 = new Thread(() -> {
            List list1 = new CopyOnWriteArrayList();
            list1.add("divice3");
            list1.add("divice4");

            List<String> topic1 = map.putIfAbsent("topic1", list1);
            if (topic1 != null) {
                topic1.addAll(list1);
            }

            Stream.of(map).forEach(System.out::println);
        });

        t.start();
        t1.start();
    }

}
