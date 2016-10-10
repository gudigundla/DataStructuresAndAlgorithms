package learning.java.api;

import ds.learning.tree.BinaryTree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hari.gudigundla on 16-10-09.
 */
public class BinaryTreeIterable extends BinaryTree implements Iterable {

    public BinaryTreeIterable(int value){
        super.setValue(value);
    }

    public static BinaryTreeIterable createBinaryTree() {
        BinaryTreeIterable root=new BinaryTreeIterable(0);
        BinaryTreeIterable node1=new BinaryTreeIterable(1);
        BinaryTreeIterable node2=new BinaryTreeIterable(2);
        BinaryTreeIterable node3=new BinaryTreeIterable(3);
        BinaryTreeIterable node4=new BinaryTreeIterable(4);
        BinaryTreeIterable node5=new BinaryTreeIterable(5);
        BinaryTreeIterable node6=new BinaryTreeIterable(6);
        BinaryTreeIterable node7=new BinaryTreeIterable(7);
        BinaryTreeIterable node8=new BinaryTreeIterable(8);

        root.setLeft(node1);
        root.setRight(node2);
        node1.setLeft(node3);
        node1.setRight(node4);
        node2.setLeft(node5);
        node2.setRight(node6);
        node5.setLeft(node7);
        //node7.left=node8;

        return root;
    }


    @Override
    public Iterator iterator() {
        Queue<BinaryTree> queue= new LinkedList<>();
        queue.add(this);
        return new BinaryTreeIteratorBFS(queue);
    }

    @Override
    public String toString() {
        return String.valueOf(getValue());
    }
}
