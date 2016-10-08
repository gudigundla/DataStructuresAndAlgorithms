package algo.recursion.examples;

/**
 * Created by hari.gudigundla on 16-10-02.
 */
public class Fibonacci {

    int sum = 0;
    public static void main(String[] args){

        System.out.println(new Fibonacci().fibinacci(10));
        System.out.println();

    }

    public int fibinacci(int n){
        if(n>=3)
            sum = fibinacci(n-1) + fibinacci(n-2);
        if(n==0)
            return 0;
        if(n==1 || n==2)
            return 1;
        return sum;
    }

}
