package com.test.stack;

import java.util.Stack;

/**
 * @author g-bug
 * @date 2021/11/9 下午8:33
 */
public class StackToQueue {

    public static void main(String[] args) throws Exception {
        StackToQueueClass st = new StackToQueueClass();
        st.push(1);
        st.push(2);
        st.push(3);
        while (st!=null) {
            System.out.println(st.pop());
        }
        st.push(4);
        System.out.println(st.pop());
    }

}

class StackToQueueClass {

    Stack<Integer> sk1=new Stack<Integer>();
    Stack<Integer> sk2=new Stack<Integer>();
    public void push(int val){
        sk1.push(val);
    }
    public int pop()throws Exception{//将栈1依次出栈，并压入栈2
        if(sk1.isEmpty()&&sk2==null){
            throw new Exception("queue is empty");
        }
        while(sk2.isEmpty()){
            while(!sk1.isEmpty()){
                sk2.push(sk1.pop());
            }
        }
        return sk2.pop();
    }

}
