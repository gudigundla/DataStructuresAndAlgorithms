package ds.learning.tree;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Created by hari.gudigundla on 16-10-07.
 */
public class BinaryTree {

    int value;
    BinaryTree left;
    BinaryTree right;

    public BinaryTree(){}

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

    public static BinaryTree createBinaryTree(int n){
        Queue<BinaryTree> queue=new LinkedList<BinaryTree>();
        BinaryTree root=new BinaryTree(0);
        queue.add(root);
        int value=1;
        while (!queue.isEmpty() && value<n){
            BinaryTree node= queue.poll();
            BinaryTree left= new BinaryTree(value++);
            BinaryTree right=new BinaryTree(value++);
            node.setLeft(left);
            node.setRight(right);
            queue.add(left);
            queue.add(right);
        }
        return root;
    }

    public static BinaryTree createBinaryTree() {
        BinaryTree root=new BinaryTree(0);
        BinaryTree node1=new BinaryTree(1);
        BinaryTree node2=new BinaryTree(2);
        BinaryTree node3=new BinaryTree(3);
        BinaryTree node4=new BinaryTree(4);
        BinaryTree node5=new BinaryTree(5);
        BinaryTree node6=new BinaryTree(6);
        BinaryTree node7=new BinaryTree(7);
        BinaryTree node8=new BinaryTree(8);

        root.left=node1;
        root.right=node2;
        node1.left=node3;
        node1.right=node4;
        node2.left=node5;
        node2.right=node6;
        node5.left=node7;
        //node7.left=node8;

        return root;
    }

    public static boolean isBalanced(BinaryTree node){
        if(balancedHeight(node)>-1)
            return true;
        else return false;
    }
    //find 2 heights of a node and if they differ by 0/1 return maxHeight, else return -1
    private static int balancedHeight(BinaryTree node){
        if(node==null) return 0;
        else{
            int lHeight=balancedHeight(node.left);
            int rHeight=balancedHeight(node.right);

            if(lHeight>-1 && rHeight>-1) {
                if (Math.abs(lHeight-rHeight)<=1)
                    return 1 + Math.max(lHeight, rHeight);
                else return -1;
            }
            else return -1;
        }
    }

    //post order traversal
    //gives height of tree if root is passed
    public static int heightOfNode(BinaryTree node){
        if(node==null) return 0;
        else{
            int lHeight= heightOfNode(node.left);
            int rHeight= heightOfNode(node.right);
            return 1+ Math.max(lHeight,rHeight);
        }
    }

    public static void getElementsOfALevel(BinaryTree node, int k){
        getElementsOfALevel(node,0,k);
    }

    private static void getElementsOfALevel(BinaryTree node, int cLevel, int rLevel){
        if(node==null) return;
        if(cLevel==rLevel){
            System.out.print(node.value + ", ");
            return;
        }
        else{
            cLevel++;
            getElementsOfALevel(node.getLeft(),cLevel,rLevel);
            getElementsOfALevel(node.getRight(),cLevel,rLevel);
        }
    }

    public void addNode(BinaryTree node){}

    public BinaryTree deleteNode(BinaryTree node, int value){
        return null;
    }

    public BinaryTree searchNode(BinaryTree node,int value){
        return null;
    }
}
