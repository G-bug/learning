package com.test.concurrent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

public class FutureTest {

    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in);) {
            System.out.print("enter directory:");
            String dir = in.nextLine();
            System.out.println("enter keyword");
            String keyword = in.nextLine();

            MatchCounter1 counter = new MatchCounter1(new File(dir), keyword);
            FutureTask<Integer> task = new FutureTask<>(counter);

            Thread t = new Thread(task); // task 做Runnable
            t.start();

            try {
                System.out.println(task.get()); // task 做 Future
            } catch (InterruptedException e) {

            } catch (ExecutionException e) {
                e.printStackTrace();
            }
        }
    }
}

class MatchCounter1 implements Callable<Integer> {

    private File directory;
    private String keyword;

    public MatchCounter1(File directory, String keyword) {
        this.directory = directory;
        this.keyword = keyword;
    }

    public Integer call() {
        int count = 0;

        File[] files = directory.listFiles();
        List<Future<Integer>> results = new ArrayList<>();
        try {
            for (File file : files) {
                if (file.isDirectory()) {
                    MatchCounter1 counter = new MatchCounter1(file, keyword);
                    // 包装器将 callable 转换为 Future+Runnable
                    FutureTask<Integer> task = new FutureTask<>(counter);
                    results.add(task);
                    Thread t = new Thread(task);
                    t.start();
                } else {
                    if (search(file, keyword)) count++;
                }
            }

            for (Future<Integer> result : results) {
                try {
                    count += result.get();
                } catch (ExecutionException e) {
                    // 执行异常
                    e.printStackTrace();
                }
            }

        } catch (InterruptedException e) {
            // 中断异常
        }

        return count;
    }

    private boolean search(File file, String keyword) {

        try {

            try (Scanner in = new Scanner(file, "utf-8");) {

                boolean found = false;
                while (!found && in.hasNextLine()) {
                    String line = in.nextLine();
                    if (line.contains(keyword)) found = true;
                }

                return found;
            }
        } catch (IOException e) {
            return false;
        }
    }
}
