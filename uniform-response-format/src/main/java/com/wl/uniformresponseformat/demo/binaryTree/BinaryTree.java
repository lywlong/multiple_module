package com.wl.uniformresponseformat.demo.binaryTree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/14 15:10
 */
public class BinaryTree {

    static int size = 0;




    /**
     * 前序遍历 根–》左–》右
     * @param root
     */
    public void preOrderTraversal(TreeNode root){
        if (root == null) {
            return;
        }
        System.out.print(root.data+" ");
        preOrderTraversal(root.left);
        preOrderTraversal(root.right);
    }

    /**
     * 中序遍历 左–》根–》右
     * @param root
     */
    public void inOrderTraversal(TreeNode root){
        if (root == null) {
            return;
        }
        inOrderTraversal(root.left);
        System.out.print(root.data+" ");
        inOrderTraversal(root.right);
    }

    /**
     * 后序遍历 左–》右–》根
     * @param root
     */
    public void postOrderTraversal(TreeNode root){
        if (root == null) {
            return;
        }
        postOrderTraversal(root.left);
        postOrderTraversal(root.right);
        System.out.print(root.data+" ");
    }

    //求结点个数,传进一颗树
    public void getTreeNodeSize(TreeNode tree){
        if (tree == null) {
            return;
        }
        size++;
        getTreeNodeSize(tree.left);
        getTreeNodeSize(tree.right);
    }

    public int getTreeNodeSize2(TreeNode tree){
        if (tree == null) {
            return 0;
        }
        //size++;
        return getTreeNodeSize2(tree.left)+getTreeNodeSize2(tree.right)+1;
    }

    public TreeNode find(TreeNode root,String val){
        if (root == null) {
            return null;
        }
        if (root.data == val) {
            return root;
        }
        TreeNode treeNode = find(root.left, val);
        if (treeNode != null) {
            return treeNode;
        }
        treeNode = find(root.right,val);
        if (treeNode != null){
            return treeNode;
        }
        return null;
    }

    /**
     * 层序遍历
     * @param root
     */
    public void levelOrderTraversal(TreeNode root){
        if (root == null) {
            return;
        }
        //单端队列(FIFO)和双端队列(即先进来的可以从前出, 在后面的也可以从后面出)
        Queue<TreeNode> queue = new LinkedList<>();
        //offer:添加一个元素并返回true,如果队列已满返回false;
        queue.offer(root);
        while (!queue.isEmpty()) {
            //poll:移除并返回队列头部的元素，如果队列为空，则返回null;
            //peek:返回队列头部的元素，如果队列为空则返回null
            //put:添加一个元素，如果队列已满，则阻塞
            //take:移除并返回队列头部的元素，如果队列为空则阻塞
            TreeNode top = queue.poll();
            System.out.print(top.data+" ");
            if (top.left != null) {
                queue.offer(top.left);
            }
            if (top.right !=null) {
                queue.offer(top.right);
            }
        }
        System.out.println();
    }

    //创建一颗二叉树
    public TreeNode create_tree(){

        TreeNode<String> tA = new TreeNode("A");
        TreeNode<String> tB = new TreeNode("B");
        TreeNode<String> tC = new TreeNode("C");
        TreeNode<String> tD = new TreeNode("D");
        TreeNode<String> tE = new TreeNode("E");
        TreeNode<String> tF = new TreeNode("F");
        TreeNode<String> tG = new TreeNode("G");
        TreeNode<String> tH = new TreeNode("H");

        tA.left = tB;
        tA.right = tC;
        tB.left = tD;
        tB.right = tE;
        tE.right = tH;
        tC.left = tF;
        tC.right = tG;
        return tA;
    }

}
