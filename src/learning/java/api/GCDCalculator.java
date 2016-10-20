package learning.java.api;

import java.math.BigInteger;

/**
 * Created by hari.gudigundla on 16-10-15.
 */
public class GCDCalculator {
    public static void main(String[] args){

        int[] numbers= new int[]{12,45,-67,34,100000};

        for(int i=0;i<numbers.length;i++){
            int count=0;
            for(int j=1;j<=numbers[i];j++){
                if(coPrime(j,numbers[i]))
                    count++;
                    //System.out.println("(" +j+ ", "+ numbers[i] + ")");
            }
            System.out.println(numbers[i] + " "+ count);
        }

    }

    private static int GCD(int a, int b){
        BigInteger bigA= BigInteger.valueOf(a);
        BigInteger bigB= BigInteger.valueOf(b);

        BigInteger gcd= bigA.gcd(bigB);
        return gcd.intValue();

    }

    private static boolean coPrime(int a, int b){
        if(GCD(a,b)>1) return false;
        else return true;
    }
}
