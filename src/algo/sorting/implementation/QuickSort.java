package algo.sorting.implementation;

import java.util.Date;

/**
 * Created by hari.gudigundla on 16-09-30.
 * Quicksort with fixed point partitioning
 */
public class QuickSort {

    private int[] numbersToBeSorted;

    public static void main(String[] args) {
        int[] numbers=new int[]{3,2,1,-2,-6,100,234, 234234,9,3,7,10,7,65};
        QuickSort sort=new QuickSort();

        long lStartTime = new Date().getTime();

        sort.quicksort(numbers);

        long lEndTime = new Date().getTime();
        long difference = lEndTime - lStartTime;

        System.out.println("Elapsed milliseconds: " + difference);

        for(int i:numbers)
            System.out.print(i + " ");
    }

    public void quicksort(int[] items) {

        if (items==null || items.length==0){
            return;
        }
        this.numbersToBeSorted = items;
        //lets start quicksortHelper with the whole array
        quicksortHelper(0, items.length - 1);
    }

    private void quicksortHelper(int left, int right) {
        int a = left, b = right;
        // Pivot is chosen from middle of the list
        int pivot = numbersToBeSorted[left + (right-left)/2];

        while (a <= b) {
            while (numbersToBeSorted[a] < pivot) {
                a++;
            }
            while (numbersToBeSorted[b] > pivot) {
                b--;
            }
            if (a <= b) {
                int temp = numbersToBeSorted[a];
                numbersToBeSorted[a] = numbersToBeSorted[b];
                numbersToBeSorted[b] = temp;

                a++;
                b--;
            }
        }

        //Divide into two parts and apply quicksortHelper recursively
        //quickort left part
        if (left < b)
            quicksortHelper(left, b);
        //quicksortHelper right part
        if (a < right)
            quicksortHelper(a, right);
    }
}
