package learning.java.api;

import ds.learning.tree.BinaryTree;

import java.util.Iterator;

/**
 * Created by hari.gudigundla on 16-10-09.
 */
public class BinarySearchIteratorBFSTester {
    public static void main(String[] args) {
        BinaryTreeIterable tree= BinaryTreeIterable.createBinaryTree();

        Iterator iterator=tree.iterator();
        for(Object node:tree){
            System.out.println((BinaryTreeIterable)node);
        }

    }

}
