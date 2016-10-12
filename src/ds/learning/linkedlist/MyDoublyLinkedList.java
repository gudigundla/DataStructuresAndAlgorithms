package ds.learning.linkedlist;

import java.util.Iterator;

/**
 * Created by hari.gudigundla on 16-10-12.
 */
public class MyDoublyLinkedList implements Iterable{

    private Node head;
    private Node tail;

    //should not be made public
    public Node getTail() {
        return tail;
    }

    public void add(Node node){
        if(head==null & tail==null){
            head=node;
            tail=node;
        } else{
          tail.setNext(node);
            node.setPrevious(tail);
            tail=node;
        }
    }

    public void delete(int value){
        Node current=head;
        Node previous=null;
        if(head==tail){
            head = tail =null;
        }
        else if(head.getValue()==value){
            head=head.getNext();
            head.setPrevious(null);
        }
        else{
            while (current!=null){
                if(current.getValue()==value){
                    //delete //previous will be null if we try to delete the first node itself
                    previous.setNext(current.getNext());
                    if(current.getNext()!=null){
                        current.getNext().setPrevious(previous);
                    }
                    else tail=previous;
                    //terminate
                    break;
                }
                //update
                previous=current;
                current=current.getNext();
            }//while
        }// else
    }

    @Override
    public Iterator iterator() {
        return new Itr();
    }

    private class Itr implements Iterator{

        Node cursor;

        public Itr(){
            cursor=head;
        }
        @Override
        public boolean hasNext() {
            return cursor!=null;
        }

        @Override
        public Object next() {
            Node temp=cursor;
            cursor=cursor.getNext();
            return temp;
        }
    }

}
