package com.test.stack;

/**
 * @author g-bug
 * @date 2021/11/9 下午9:13
 */
public class LinkedToStack {

    Node head;
    Node tail;
    int size = 0;

    static class Node {
        Integer val;
        Node per;
        Node next;

        Node(int val, Node per, Node next) {
            this.val = val;
            this.per = per;
            this.next = next;
        }
    }

    public void push(int v) {
        Node t = tail;
        Node node = new Node(v, t, null);
        tail = node;
        if (t == null) {
            head = node;
        } else {
            t.next = node;
        }
        size++;
    }

    public int pop() {
        if (tail == null) {
            return -1;
        }
        int v = tail.val;
        tail = tail.per;
        size--;
        return v;
    }

    public static void main(String[] args) {
        LinkedToStack lt = new LinkedToStack();
        lt.push(3);
        lt.push(1);
        lt.push(2);
        while (lt.size > 0) {
            System.out.println(lt.pop());
        }
    }
}

