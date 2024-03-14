package com.test.concurrent;


import java.util.Random;
import java.util.concurrent.PriorityBlockingQueue;

/**
 * 优先级队列测试
 */
public class TestPriorityBlockingQueue {

    static class Task implements Comparable<Task> {

        private int priority = 0;

        private String taskName;

        public int getPriority() {
            return priority;
        }

        public void setPriority(int priority) {
            this.priority = priority;
        }

        public String getTaskName() {
            return taskName;
        }

        public void setTaskName(String taskName) {
            this.taskName = taskName;
        }

        @Override
        public int compareTo(Task o) {
            if (this.priority >= o.getPriority()) {
                return 1;
            } else {
                return -1;
            }
        }

        public void doSomeThing() {
            System.out.println(taskName + ":" + priority);
        }

    }

    public static void main(String[] args) {

        PriorityBlockingQueue<Task> priorityQueue = new PriorityBlockingQueue();
        // 默认种子的随机数生成器(CAS更新种子以满足多线程需求)
        Random random = new Random();
        for (int i = 0; i < 10; i++) {
            Task task = new Task();
            task.setTaskName("name_" + i);
            task.setPriority(random.nextInt(10));
            priorityQueue.offer(task);
        }

        while (!priorityQueue.isEmpty()) {
            Task task = priorityQueue.poll();
            if (task != null) {
                task.doSomeThing();
            }
        }

    }

}
