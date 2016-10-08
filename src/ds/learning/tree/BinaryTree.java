package ds.learning.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hari.gudigundla on 16-10-07.
 */
public class BinaryTree {
    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public void setLeft(BinaryTree left) {
        this.left = left;
    }

    public BinaryTree getRight() {
        return right;
    }

    public void setRight(BinaryTree right) {
        this.right = right;
    }

    int value;
    BinaryTree left;
    BinaryTree right;

    public BinaryTree(int value){
        this.value=value;
    }

    //traversals
    public static void inOrderTraversal(BinaryTree root){
        if(root==null){
            return;
        }else{
            inOrderTraversal(root.left);
            System.out.print(root.value + ", ");
            inOrderTraversal(root.right);
        }
    }

    public static void preOrderTraversal(BinaryTree root){
        if(root==null)
            return;
        else{
            System.out.print(root.value + ", ");
            preOrderTraversal(root.left);
            preOrderTraversal(root.right);
        }
    }

    public static void postOrderTraversal(BinaryTree root){
        if(root==null)
            return;
        else{
            postOrderTraversal(root.left);
            postOrderTraversal(root.right);
            System.out.print(root.value + ", ");
        }
    }

    public static void breadthFirstTraversal(BinaryTree root){
        Queue<BinaryTree> queue= new LinkedList<BinaryTree>();
        queue.add(root);

        while (!queue.isEmpty()){
            BinaryTree node=queue.poll();
            if(node.left!=null)
                queue.add(node.left);
            if(node.right!=null)
                queue.add(node.right);
            System.out.print(node.value + ", ");
        }
    }

    public void addNode(BinaryTree node){

    }

    public BinaryTree deleteNode(BinaryTree node, int value){
        return null;
    }

    public BinaryTree searchNode(BinaryTree node,int value){
        return null;
    }
}
