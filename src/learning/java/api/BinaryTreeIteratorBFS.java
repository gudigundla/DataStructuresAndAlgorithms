package learning.java.api;

import ds.learning.tree.BinaryTree;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hari.gudigundla on 16-10-09.
 */
public class BinaryTreeIteratorBFS implements Iterator {
    Queue<BinaryTree> queue= new LinkedList<>();

    public BinaryTreeIteratorBFS(Queue<BinaryTree> queue){
        this.queue=queue;
    }

    @Override
    public boolean hasNext() {
        return !queue.isEmpty();
    }

    @Override
    public Object next() {
        BinaryTree node=queue.poll();
        if(node.getLeft()!=null)
        queue.add(node.getLeft());
        if(node.getRight()!=null)
        queue.add(node.getRight());
        return node;
    }
}