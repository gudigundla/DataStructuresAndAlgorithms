package ds.learning.stackqueue;

/**
 * Created by hari.gudigundla on 16-09-08.
 */
public class MyStack {
    private int top;
    private int[] stackElements;
    private int size;

    public MyStack(int n){
        stackElements= new int[n];
        size=n;
        top=-1;
    }

    public int pop()  {
        if(top>-1){
            return stackElements[top--];
        }
        else
            try {
                throw new Exception("Stack UnderFlow !!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        return 0;
    }

    public void push(int i) {
        if(top <size){
            stackElements[++top] =i;
            System.out.println("Pushed " + i);
        }
        else
            try {
                throw new Exception("Stack OverFlow !!");
            } catch (Exception e) {
                e.printStackTrace();
            }
    }

    public int peek() {
        if(top>-1){

            return stackElements[top];
        }
        else
            try {
                throw new Exception("Stack UnderFlow !!");

            } catch (Exception e) {
                e.printStackTrace();
            }
        return 0;
    }

    public void printStack(){
        for(int i=0; i<=top; i++){
            System.out.print(stackElements[i] + ", ");
        }
        System.out.println();
    }


}
