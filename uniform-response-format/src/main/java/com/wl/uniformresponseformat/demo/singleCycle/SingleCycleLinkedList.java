package com.wl.uniformresponseformat.demo.singleCycle;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/14 10:01
 */
public class SingleCycleLinkedList<T> {

    private int size;
    private Node<T> head = null;//头节点
    private Node<T> tail = null;//尾节点

    public void add(T data){
        //创建一个新节点
        Node<T> newNode = new Node<>();
        //插入第一个节点
        if (size == 0) {
            head = newNode;
            tail = newNode;
            tail.next = head;
            size++;
            return;
        }
        //如果不是第一个节点
        tail.next = newNode;
        tail = newNode;
        tail.next = head;
        size++;
    }

    public void remove(T data){
        Node temp = head;
        while (true) {
            //找到要移除的节点
            if (data.equals(temp.next.data)){
                //如果是头节点
                if (temp.next == head) {
                    head = temp.next.next;
                }
                //如果尾节点
                if (temp.next == tail) {
                    tail = temp;
                }
                temp.next = temp.next.next;
                System.out.println("移除的节点："+data);
                size--;
                break;
            }
            temp = temp.next;
            if (temp == head) {
                System.out.println("不存在该节点");
                return;
            }
        }
    }


    public void showList(){
        if (this.size == 0) {
            System.out.println("链表为空");
        }
        Node temp = head;
        System.out.println(temp.data+"->");
        while (true) {
            temp = temp.next;
            if (temp == head) {
                break;
            }
            System.out.println(temp.data+"->");
        }
    }

    private class Node<T>{
        T data;//存储的数据
        Node<T> next;//指向下一个节点的引用指针

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public Node() {
        }

        @Override
        public String toString() {
            return "Node{" +
                    "data=" + data +
                    '}';
        }
    }

}
