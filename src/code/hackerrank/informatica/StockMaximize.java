package code.hackerrank.informatica;

/**
 * Created by hari.gudigundla on 16-10-15.
 */
public class StockMaximize {

    public static void main(String[] args){

        //int prices[] = {501, 100, 200, 300, 400, 100, 500, 600,500, 200, 100,100};
        int prices[] = {1,3,1,2};
        int N = prices.length;
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

        for(int j=0;j<N;j++){
            System.out.println(prices[j] + " - "+ buy[j]);
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
                profit+=numOfStocks*prices[j];
                numOfStocks=0;
            }
        }

        System.out.println("PROFIT : "+profit);

    }



}
