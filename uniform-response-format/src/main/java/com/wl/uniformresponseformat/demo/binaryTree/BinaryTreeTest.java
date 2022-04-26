package com.wl.uniformresponseformat.demo.binaryTree;

/**
 * @author WangLong
 * @Description:
 * @date 2022/4/14 15:23
 */
public class BinaryTreeTest {

    public static void main(String[] args) {
        BinaryTree binaryTree = new BinaryTree();
        TreeNode<String> root = binaryTree.create_tree();
        //binaryTree.preOrderTraversal(root);
        //TreeNode<String> node = binaryTree.find(root,"H");
        //System.out.println(node.data);
        binaryTree.levelOrderTraversal(root);
    }

}
