package ds.learning.linkedlist;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by hari.gudigundla on 16-10-11.
 */
public class MyLinkedListTester {

    public static void main(String[] args) {
        MyLinkedList list= new MyLinkedList();
        list.add(new Node(24));
        list.add(new Node(4));
        list.add(new Node(67));
        list.add(new Node(21));

        for(Object node:list){
            Node n=(Node)node;
            System.out.println(n.getValue());
        }

        System.out.println("--");
        list.delete(24);
        list.delete(21);

        for(Object node:list){
            List list1= new ArrayList();
            Node n=(Node)node;
            System.out.println(n.getValue());
        }
    }
}
