package algo.sorting.implementation;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by hari.gudigundla on 16-09-30.
 */
public class InsertionSort {

    public static void main(String[] args){
        int[] numbers=new int[]{3,2,1,2,6,100,234, 234234,9,3,7,10,7,65};
        InsertionSort sort=new InsertionSort();

        long lStartTime = new Date().getTime();

        sort.insertionSort(numbers);

        long lEndTime = new Date().getTime();
        long difference = lEndTime - lStartTime;

        System.out.println("Elapsed milliseconds: " + difference);

        for(int i:numbers)
            System.out.print(i + " ");
    }

    public  void insertionSort(int[] numbers){
        for(int i=0;i<numbers.length-1;i++){

            //find correct place of i+1th element and re-arrange the elements
            for(int j=i;j>=0;j--){
                if(numbers[j]<=numbers[j+1]){
                    //its place is j+1
                    //swap j & i+1
                    break;
                }
                int temp=numbers[j];
                numbers[j]=numbers[j+1];
                numbers[j+1]=temp;
                //System.out.print("\t");
                /*for(int num:numbers){
                    System.out.print( num+ " ");
                }
                System.out.println();*/
            }
            /*System.out.print(i+ "-> ");
            for(int num:numbers){
                System.out.print( num+ " ");
            }
            System.out.println();*/
        }




    }

}
