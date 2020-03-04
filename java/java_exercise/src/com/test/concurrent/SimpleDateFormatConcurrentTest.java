package com.test.concurrent;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * SimpleDateFormat是线程不安全的,多个线程用一个simpleDateFormat对象解析会出问题
 * 加入ThreadLocal可以解决且相比synchronized 和 每个线程new一个对象更好
 */
public class SimpleDateFormatConcurrentTest {

    static ThreadLocal<DateFormat> safeFormat = ThreadLocal.withInitial(() -> new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(() -> {
                try {
                    System.out.println(safeFormat.get().parse("2019-12-28 22:08:33"));
                } catch (ParseException e) {
                    e.printStackTrace();
                } finally {
                    // 使用完要清除, 以防OOM
                    safeFormat.remove();
                }
            });

            t.start();
        }
    }

}
