package com.wl.uniformresponseformat.demo.doubleLink;

/**
 * @author WangLong
 * @Description: 双向链表
 * @date 2022/4/12 14:52
 */
public class DoubleLinkedList<T> {

    private int size;
    //头节点，初始化
    private Node<T> head = new Node<>();
    private Node<T> tail = head;

    private class Node<T> {
        T data;//存储的数据
        Node<T> next;//存储一下节点的引用指针
        Node<T> pre;//存储上一个节点的引用指针

        public Node(T data) {
            this.data = data;
            this.next = null;
            this.pre = null;
        }

        public Node() {
        }

        @Override
        public String toString() {
            return "Node{data:" + data + "}";
        }
    }

    /**
     * 从尾部添加数据（尾插法）
     *
     * @param data
     */
    public void addTail(T data) {
        //先创建一个新节点
        Node<T> newNode = new Node<>(data);
        //尾节点的next指向新节点
        tail.next = newNode;
        //新节点的pre指向tail
        newNode.pre = tail;
        //将新添加的节点设为尾节点
        tail = newNode;
        size++;
    }

    /**
     * 从头部添加数据（头插法）
     *
     * @param data
     */
    public void addHead(T data) {
        //先创建一个新节点
        Node<T> newNode = new Node<>(data);
        newNode.next = head.next;
        head.next.pre = newNode;
        //
        head.next = newNode;
        newNode.pre = head;
        size++;
    }

    /**
     * 删除指定下标的节点
     *
     * @param index
     */
    public void delete(int index) {
        if (index > size) {
            System.out.println("下标超出链表大小 ");
            return;
        }
        Node temp = head;
        for (int i = 1; i <= index; i++) {
            temp = temp.next;
        }
        //要删除的节点不是最后一个节点
        if (index < size) {
            temp.pre.next = temp.next;
            temp.next.pre = temp.pre;
        } else {//要删除的节点是最后一个节点
            //将倒数第二个节点设为尾节点
            tail = temp.pre;
            //然后将倒数第二个节点的next指向为null
            temp.pre.next = null;
        }
        size--;
    }

    /**
     * 修改指定下标的节点
     * @param data
     * @param index
     */
    public void update(T data,int index){
        if (index > size) {
            System.out.println("下标超出链表大小 ");
            return;
        }
        Node temp = head;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        temp.data = data;
    }

    /**
     * 遍历链表
     */
    public void showLink(){
        if (this.size == 0) {
            System.out.println("链表大小为空");
            return;
        }
        Node temp = head;
        while (true) {
            if (temp == null) break;
            System.out.println(temp);
            //这里很重要，注意顺序，先print再将临时节点后移。
            temp = temp.next;
        }
    }

    /**
     * 给指定索引处插入节点
     * @param data
     * @param index
     */
    public void add(T data,int index){
        if (index > size) {
            System.out.println("下标超出链表大小 ");
            return;
        }
        //先找到插入下标位置的上一个节点
        Node temp = head;
        for (int i = 0; i <= index-1; i++) {
            temp = temp.next;
        }

        //创建一个新节点
        Node<T> newNode = new Node<>(data);
        temp.next = newNode;
        newNode.pre = temp;
        newNode.next = temp.next;
        temp.next.pre = newNode;
        size++;
    }

    public static void main(String[] args) {
        DoubleLinkedList<Object> dll = new DoubleLinkedList<>();
        dll.addTail("aa");
        dll.addTail("bb");
        dll.addHead("cc");
        dll.addHead("ee");
        dll.delete(3);
        dll.addTail("dd");
        dll.showLink();
    }

}
