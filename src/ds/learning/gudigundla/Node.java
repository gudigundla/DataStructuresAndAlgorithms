package ds.learning.gudigundla;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hari.gudigundla on 16-09-16.
 */
public class Node {

    private int data;
    private Node left;
    private Node right;

    public Node(int data) {
        this.data=data;
    }

    public Node(int data, Node left, Node right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    public static Node createBinaryTree(){

        Node n3 = new Node(3);
        Node n4 = new Node(4);
        Node n5 = new Node(5);
        Node n6 = new Node(6);

        Node n1 = new Node(1,n3,n4);
        Node n2 = new Node(2,n5,n6);

        Node myBinaryTree = new Node(0,n1,n2);

        return myBinaryTree;
    }

    public static void LevelTraversal(Node tree){

        Queue<Node> tracker = new LinkedList<Node>();
        tracker.add(tree);

        while (!tracker.isEmpty()){
            Node temp = tracker.remove();
            System.out.println(temp.getData());
            if(temp.getLeft() != null)
                tracker.add(temp.getLeft());
            if(temp.getRight() != null)
                tracker.add(temp.getRight());
        }
    }

    public static void PreOrderTraversal(Node tree){
        if(tree != null)
            System.out.println(tree.getData());
        if(tree.getLeft() != null)
            PreOrderTraversal(tree.getLeft());
        if(tree.getRight() != null)
            PreOrderTraversal(tree.getRight());
    }

    public static void InorderTraversal(Node tree){

        if(tree.getLeft() != null)
            InorderTraversal(tree.getLeft());
        if(tree != null)
            System.out.println(tree.getData());
        if(tree.getRight() != null)
            InorderTraversal(tree.getRight());
    }

    public static void PostOrderTraversal(Node tree){

        if(tree.getLeft() != null)
            PostOrderTraversal(tree.getLeft());
        if(tree.getRight() != null)
            PostOrderTraversal(tree.getRight());
        if(tree != null)
            System.out.println(tree.getData());
    }

    public int getData() {
        return data;
    }

    public void setData(int data) {
        this.data = data;
    }

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }
}
