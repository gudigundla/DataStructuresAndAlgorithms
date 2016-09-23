package ds.learning.ds.learning.stackqueue;

public class Main {


    public static void main(String[] args) {


        MyQueue myQueue=new MyQueue(5);

        myQueue.enqueue(34);
        myQueue.enqueue(63);
        myQueue.enqueue(12);
        myQueue.enqueue(10);
        myQueue.enqueue(77);


        System.out.println("Dequeued - " +myQueue.dequeue());
        System.out.println("Dequeued - " +myQueue.dequeue());

        myQueue.enqueue(6322);
        myQueue.enqueue(4567);

        System.out.println("Dequeued - " +myQueue.dequeue());
        System.out.println("Dequeued - " +myQueue.dequeue());

        myQueue.enqueue(888);

        System.out.println("Dequeued - " +myQueue.dequeue());
        System.out.println("Dequeued - " +myQueue.dequeue());
        System.out.println("Dequeued - " +myQueue.dequeue());
        System.out.println("Dequeued - " +myQueue.dequeue());

     /*   ds.learning.ds.learning.stackqueue.MyStack myStack=new ds.learning.ds.learning.stackqueue.MyStack(6);

        myStack.push(12);
        myStack.push(13);

        myStack.push(14);

        System.out.println("Peeked - " + myStack.peek());

        myStack.push(15);

        myStack.push(16);
        System.out.println("Peeked - " + myStack.peek());
        System.out.println("Popped - " + myStack.pop());
        System.out.println("Peeked - " + myStack.peek());

        myStack.push(17);
        System.out.println("Peeked - " + myStack.peek());

        System.out.println("Popped - " + myStack.pop());


        myStack.printStack();
      */

    }
}
