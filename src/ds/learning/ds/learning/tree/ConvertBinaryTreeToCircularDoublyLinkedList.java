package ds.learning.ds.learning.tree;

/**
 * Created by hari.gudigundla on 16-09-17.
 */
public class ConvertBinaryTreeToCircularDoublyLinkedList {
    static Node head=null;
    static Node temp =null;

    public static void main(String[] args) {

        Node tree= Node.createBinaryTree();

        convertBinaryTreeToCircularDoublyLinkedList(tree);
        printLinkedList(head);
    }

    private static void convertBinaryTreeToCircularDoublyLinkedList(Node tree){
        if(tree.getLeft()!=null)
            convertBinaryTreeToCircularDoublyLinkedList(tree.getLeft());
        if(tree!=null){
            System.out.println("tree Out- "+tree.getData());
            if(temp !=null){
                System.out.println("tree In - "+tree.getData());
           //     System.out.println("temp - " + temp.getData());
                temp.setRight(tree);
                tree.setLeft(temp);
                temp=tree;
            } else{
                temp=tree;
                if(head==null)
                    head=temp;
            }
        }
        if(tree.getRight() !=null)
            convertBinaryTreeToCircularDoublyLinkedList(tree.getRight());
    }

    private static void printLinkedList(Node list){
        System.out.println(list.getData());
        if(list.getLeft()!=null)
            printLinkedList(list.getLeft());
    }
}