package code.hackerrank.informatica;

/**
 * Created by hari.gudigundla on 16-10-16.
 * Stock Maximize - challenge on HackerRank
 * Tested with test cases from Hackerrank
 */
import java.util.*;

public class Solution {

    public static void main(String[] args) {
        /* Enter your code here. Read input from STDIN. Print output to STDOUT. Your class should be named Solution. */
        Scanner scanner=new Scanner(System.in);
        int T =scanner.nextInt();
        for(int index=0;index<T;index++){
            scanner.nextLine();
            int N=scanner.nextInt();
            scanner.nextLine();
            int[] prices=new int[N];
            for(int j=0;j<N;j++){
                prices[j]=scanner.nextInt();
            }

            //System.out.println(N+ " -> "+prices.length);

            //int N = prices.length;
            int i=N-1;
            boolean[] buy=new boolean[N];
            int maxSoFar= Integer.MIN_VALUE;

            //find when to buy and when to sell if you have any stocks
            while(i>=0){
                if(prices[i]>=maxSoFar){
                    buy[i]=false;
                    maxSoFar=prices[i];
                }
                else buy[i]=true;
                i--;
            }

            //profit calculation
            long profit=0;
            int numOfStocks=0;
            for(int j=0;j<N;j++){
                if(buy[j]) {
                    numOfStocks++;
                    profit -= prices[j];
                }
                else{
                    //sell all shares and update profit
                    //important to convert to long, else test cases fails, if we multiple to ints, we get int
                    profit+=(long)numOfStocks*(long)prices[j];
                    numOfStocks=0;
                }
            }
            System.out.println(profit);
        }
    }
}