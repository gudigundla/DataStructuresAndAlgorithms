package algo.sorting.implementation;

import java.util.Date;
import java.util.Random;

/**
 * Created by hari.gudigundla on 16-09-30.
 */
public class SortingTester {

    public static void main(String[] args){

        int size=1000000;
        int[] items= new int[size];
        Random rand= new Random();
        for(int i =0;i<size;i++){
            //items[i]= rand.nextInt(size);
            items[i]=size-i;
        }

        InsertionSort sort=new InsertionSort();

        long lStartTime = new Date().getTime();
        sort.insertionSort(items);
        long lEndTime = new Date().getTime();
        long difference = lEndTime - lStartTime;
        System.out.println("Elapsed milliseconds: " + difference);

        QuickSort quickSort=new QuickSort();

        lStartTime = new Date().getTime();
        quickSort.quicksort(items);
        lEndTime = new Date().getTime();
        difference = lEndTime - lStartTime;
        System.out.println("Elapsed milliseconds: " + difference);

        RandomizedQuicksort randomizedQuicksort=new RandomizedQuicksort();

        lStartTime = new Date().getTime();
        randomizedQuicksort.quicksort(items);
        lEndTime = new Date().getTime();
        difference = lEndTime - lStartTime;
        System.out.println("Elapsed milliseconds: " + difference);



    }
}
