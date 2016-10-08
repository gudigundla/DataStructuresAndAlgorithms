package ds.learning.bst;

import ds.learning.tree.BinaryTree;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by hari.gudigundla on 16-10-06.
 */
public class BSTTester {
    public static void main(String[] args){
        BinarySearchTree bst= new BinarySearchTree(50);
        List<Integer> elements= Arrays.asList(25, 75, 15, 35, 65, 55, 70, 85, 80, 90,84);
        for(int e:elements){
            bst.addNode(new BinarySearchTree(e));
        }

        BinaryTree.inOrderTraversal(bst);
        System.out.println();
        BinaryTree.preOrderTraversal(bst);
        System.out.println();
        BinaryTree.postOrderTraversal(bst);
        System.out.println();
        BinaryTree.breadthFirstTraversal(bst);
        System.out.println();

        //before deleting 75
        BinaryTree node= bst.searchNode(bst,50);
        System.out.println(node.getRight().getValue());

        System.out.println("-----------------------------------------------");
        bst.deleteNode(bst,75);

        BinaryTree.inOrderTraversal(bst);
        System.out.println();
        BinaryTree.preOrderTraversal(bst);
        System.out.println();
        BinaryTree.postOrderTraversal(bst);
        System.out.println();
        BinaryTree.breadthFirstTraversal(bst);
        System.out.println();

        //after deleting 75
        node= bst.searchNode(bst,50);
        System.out.println(node.getRight().getValue());

    }


}
