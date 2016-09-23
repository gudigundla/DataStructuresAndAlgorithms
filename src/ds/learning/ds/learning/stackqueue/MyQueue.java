package ds.learning.ds.learning.stackqueue;

/**
 * Created by hari.gudigundla on 16-09-09.
 */
public class MyQueue {

    private int head;
    private int tail;
    private int[] queueElements;
    private int size;
    private boolean isEmpty;
    private boolean isFull;

    public MyQueue(int n){
        queueElements=new int[n];
        size=n;
        head=0;
        tail=0;
        isEmpty=true;
    }

    public void enqueue(int i){
        if(!isFull){
            queueElements[tail] = i;

            if(tail==size-1){
                tail=0;
            }
            else{
                tail++;
            }

            if(head==tail)
                isFull=true;

            isEmpty=false;
            System.out.println("Enqueued - "+ i);
        }
        else try {
            throw new Exception("Queue OverFlow !!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public int dequeue(){
        if(!isEmpty){
            isFull=false;
            if(head==size-1){
                head=0;
                if(tail==0)
                    isEmpty=true;
                return queueElements[size-1];
            }
            else {

                if(head+1 ==tail)
                    isEmpty=true;
                return queueElements[head++];
            }


        } else {
            try {
                throw new Exception("Queue UnderFlow");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return 0;
    }
}
