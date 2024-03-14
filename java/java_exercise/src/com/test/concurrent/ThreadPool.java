package com.test.concurrent;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.*;

public class ThreadPool {


    public static void main(String[] args) {
        try (Scanner in = new Scanner(System.in)) {
            System.out.println("enter:");
            String directory = in.nextLine();
            System.out.println("keyword:");
            String keyword = in.nextLine();
            ExecutorService pool = Executors.newCachedThreadPool();

            MatchCounter counter = new MatchCounter(new File(directory), pool, keyword);
            Future<Integer> result = pool.submit(counter);

            try {
                result.get();
            } catch (InterruptedException e) {

            } catch (ExecutionException e) {
                e.printStackTrace();
            }

            pool.shutdown();

            int largestPoolSize = ((ThreadPoolExecutor)pool).getLargestPoolSize();
            System.out.println(largestPoolSize);

        }
    }
}

class MatchCounter implements Callable<Integer> {
    private File directory;
    private String keyword;
    private ExecutorService pool;
    private int count;

    public MatchCounter(File directory, ExecutorService pool, String keyword) {
        this.directory = directory;
        this.keyword = keyword;
        this.pool = pool;
    }

    @Override
    public Integer call() throws Exception {
        try {

            File[] files = directory.listFiles();
            List<Future<Integer>> results = new ArrayList<>();

            for (File file : files) {
                if (file.isDirectory()) {
                    MatchCounter counter = new MatchCounter(file, pool, keyword);
                    Future<Integer> result = pool.submit(counter);
                    results.add(result);
                } else {
                    if (search(file)) count++;
                }
            }

            for (Future<Integer> result : results) {
                try {
                    count += result.get();
                }catch (ExecutionException e){
                    e.printStackTrace();
                }
            }

        }catch (InterruptedException e){

        }
        return count;
    }

    private boolean search(File file) {
        try {
            try (Scanner in = new Scanner(file, "utf-8")) {
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

    final private boolean xx() {
        return false;
    }

    private boolean xx(int x){
        return true;
    }
}
