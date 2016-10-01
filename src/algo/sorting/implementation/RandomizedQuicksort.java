package algo.sorting.implementation;

import java.util.Date;
import java.util.Random;

/**
 * Created by hari.gudigundla on 16-09-30.
 * Quicksort with Randomized partitioning
 */
public class RandomizedQuicksort {

    private int[] numbersToBeSorted;
    Random random;

    public RandomizedQuicksort(){
        random= new Random();
    }

    public static void main(String[] args) {
        int[] numbers=new int[]{3,2,1,-2,-6,100,234, 234234,9,387,7,10, 7,7,7,7,65};

        RandomizedQuicksort sort=new RandomizedQuicksort();

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
        //lets start quicksortHelperMthod with the whole array
        quicksortHelperMthod(0, items.length - 1);
    }

    private void quicksortHelperMthod(int left, int right) {
        int a = left, b = right;
        // Pivot is chosen randomly
        int pivot = numbersToBeSorted[left + random.nextInt(right-left+1)];

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

        //Divide into two parts and apply quicksortHelperMthod recursively
        //quickort left part
        if (left < b)
            quicksortHelperMthod(left, b);
        //quicksortHelperMthod right part
        if (a < right)
            quicksortHelperMthod(a, right);
    }
}
