package ds.learning.linkedlist;

/**
 * Created by hari.gudigundla on 16-10-12.
 */
public class MyDoublyLinkedListTester {
    public static void main(String[] args) {
        MyDoublyLinkedList list = new MyDoublyLinkedList();
        list.add(new Node(24));
        list.add(new Node(4));
        list.add(new Node(67));
        list.add(new Node(21));

        for(Object node:list){
            Node n=(Node)node;
            System.out.println(n.getValue());
        }
        System.out.println("-----");
        Node tail=list.getTail();
        while (tail!=null){
            System.out.println(tail.getValue());
            tail=tail.getPrevious();
        }
        System.out.println("-----");
        list.delete(21);
        list.delete(4);

        for(Object node:list){
            Node n=(Node)node;
            System.out.println(n.getValue());
        }
        System.out.println("-----");
         Node tail1=list.getTail();
        while (tail1!=null){
            System.out.println(tail1.getValue());
            tail1=tail1.getPrevious();
        }
        System.out.println("-----");


    }
}
