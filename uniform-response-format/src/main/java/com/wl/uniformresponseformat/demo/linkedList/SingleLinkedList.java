package com.wl.uniformresponseformat.demo.linkedList;

import lombok.extern.slf4j.Slf4j;

/**
 * @author WangLong
 * @Description: 单向链表，接收的数据我们用一个<T>泛型来规范
 * @date 2022/4/11 16:44
 */
@Slf4j
public class SingleLinkedList<T> {

    private int size;
    private Node<T> head = new Node<>();//头节点（先初始化一下）
    private Node<T> tail = head;//尾节点，刚开始指向头节点

    //数据节点类，相当于一个容器
    private class Node<T>{
        T data;//存储的数据
        Node<T> next;//存储一下节点的引用指针

        public Node(T data) {
            this.data = data;
            this.next = null;
        }

        public Node() {
        }

        @Override
        public String toString() {
            return "Node{data:"+data+"}";
        }
    }

    /**
     * 尾插法
     * @param data
     */
    public void addTail(T data){
        //根据添加的内容，创建一个新节点，next域为null
        //新节点初始化时next为null
        Node<T> newNode = new Node<>(data);
        //尾节点的next指向新节点
        tail.next = newNode;
        //将新节点设为尾节点
        tail = newNode;
        size++;
    }

    /**
     * 头插法
     * @param data
     */
    public void addHead(T data){
        //根据添加的内容，创建一个新节点，next域为null
        //head next指向新节点
        //新节点初始化时next为null
        Node<T> newNode = new Node<>(data);
        //新增节点next指向头节点的next节点
        newNode.next = head.next;
        //头节点的next域再指向新增节点
        head.next = newNode;
        size++;
    }

    /**
     * 将数据插入指定位置
     * @param data
     * @param index
     */
    public void insertData(T data,int index){
        if (index > size) {
            log.info("下标超出链表大小");
            return;
        }
        //定义一个临时节点赋值head
        Node temp = head;
        //找到插入的位置的前一个节点，索引为index-1,因为是单向链表，不能指向前一个节点
        for (int i = 0; i <= index-1; i++) {
            temp = temp.next;//temp每次循环在变
        }
        //根据添加的内容，创建一个新节点，next域为null
        Node<T> newNode = new Node<>(data);
        //关键逻辑
        newNode.next = temp.next;
        temp.next = newNode;
        size++;

    }

    /**
     * 删除指定下标的节点
     * @param index
     */
    public void delete(int index){
        if (index > size) {
            log.info("下标超出链表大小");
            return;
        }
        Node temp = head;
        //找到要删除节点的位置的前一个节点，索引为index-1
        for (int i = 0; i <= index-1; i++) {
            temp = temp.next;
        }
        //删除的前一个节点next指向删除节点的下一个节点
        temp.next = temp.next.next;
        if (index == size) {
            tail = temp;
        }
        size--;
    }

    /**
     * 悠指定下标的元素数据
     * @param data
     * @param index
     */
    public void update(T data,int index){
        if (index > size) {
            log.info("下标超出链表大小");
            return;
        }
        Node temp = head;
        //找到要修改节点的位置，索引为index
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        //找到指定节点后直接修改其data
        temp.data = data;
    }

    /**
     * 遍历链表
     */
    public void traversLinkList(){
        if (this.size == 0) {
            log.info("链表为空");
            return;
        }
        Node temp = head;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 获取指定下标节点的值
     * @param index
     * @return
     */
    public Object get(int index){
        if (index > size) {
            log.info("下标超出链表大小");
            return -1;
        }
        Node temp = head;
        for (int i = 0; i <= index; i++) {
            temp = temp.next;
        }
        return temp.data;
    }

    public static void testLinkList(){
        SingleLinkedList<String> linkedList = new SingleLinkedList<>();
        linkedList.addTail("a");
        linkedList.addTail("b");
        linkedList.addTail("c");
        linkedList.addTail("d");
        linkedList.addTail("e");
        linkedList.traversLinkList();
        System.out.println("size: " + linkedList.size);
    }

    //单向链表的反转
    public void reversal(){
        //首先创建一个临时链表
        SingleLinkedList tempLinkList = new SingleLinkedList<>();
        //开始遍历，从头部next开始
        Node curNode = this.head.next;
        while (true){
            if (curNode == null) break;
            //将当前节点利用头插法，添加到临时链表中
            tempLinkList.addHead(curNode.data);
            curNode = curNode.next;
        }
        //最后把临时链表的head索引赋值给当前链表的head
        this.head = tempLinkList.head;
    }

    public static void main(String[] args) {
        SingleLinkedList<String> linkedList = new SingleLinkedList<>();
        linkedList.addTail("a");
        linkedList.addTail("b");
        linkedList.addTail("c");
        linkedList.addTail("d");
        linkedList.addTail("e");
        linkedList.traversLinkList();
        System.out.println("size: " + linkedList.size);
        linkedList.reversal();
        linkedList.traversLinkList();
        System.out.println(2<<3);
    }

}
