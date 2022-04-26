package com.wl.uniformresponseformat.demo;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/14 16:45
 */
public class QueueDemo {

    static Queue<String> stringQueue = new LinkedList<>();

    static {
        stringQueue.offer("aaa");
        stringQueue.offer("bbb");
        stringQueue.offer("ccc");
        stringQueue.offer("ddd");
        stringQueue.offer("eee");
    }

    public static void main(String[] args) {



        System.out.println("stringQueue : " + stringQueue);
        System.out.println(stringQueue.getClass());
        //在这里可以看出，会去默认使用LinkedList的方法
        //但是如果一定要使用Queue的方法，也会自动转型

        //返回第一个元素，如果队列是空，则会输出null
        System.out.println("第一个元素变成："+stringQueue.peek());

        //返回第一个元素，如果队列是空，则报错
        System.out.println("第一个元素："+stringQueue.element());

        //返回第一个元素，并且将这个元素删除
        System.out.println("第一个元素："+stringQueue.poll()+",并且已经被删除。");
        System.out.println(stringQueue);
    }


}
