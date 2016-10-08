package ds.learning.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hari.gudigundla on 16-10-07.
 */
public class BinaryTreeTester {
    public static void main(String[] args) {
        BinaryTree root=new BinaryTree(0);
        BinaryTree node1=new BinaryTree(1);
        BinaryTree node2=new BinaryTree(2);
        BinaryTree node3=new BinaryTree(3);
        BinaryTree node4=new BinaryTree(4);
        BinaryTree node5=new BinaryTree(5);
        BinaryTree node6=new BinaryTree(6);

        root.left=node1;
        root.right=node2;
        node1.left=node3;
        node1.right=node4;
        node2.left=node5;
        node2.right=node6;

        BinaryTree.inOrderTraversal(root);
        System.out.println();
        BinaryTree.preOrderTraversal(root);
        System.out.println();
        BinaryTree.postOrderTraversal(root);
        System.out.println();

        BinaryTree.breadthFirstTraversal(root);
    }
}
