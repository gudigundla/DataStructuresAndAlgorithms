package algo.sorting.implementation;
/**
 * Created by hari.gudigundla on 16-09-30.
 */
public class GQCalculator {

    public static void main(String[] args){

        //int[] items=new int[]{3,1,7,5,6};
        //int[] items=new int[]{100,100,100};
        int[] items=new int[]{4,1,7,5,6};

        QuickSort sort=new QuickSort();
        sort.quicksort(items);
        GQCalculator gq = new GQCalculator();
        int GQ = gq.GQCalculatorFunction(items.length, items);
        System.out.println("GQ - " + GQ);

    }

    public int GQCalculatorFunction(int sizeOfList, int[] items){
        //intial assumption for GQ
        int GQ=sizeOfList;
        for(int i=0;i<sizeOfList;i++){
            if(GQ>=0 && items[i]>=GQ && GQ==sizeOfList-i){
                break;
            }
            else {
                if(GQ>0)
                    GQ--;
                else break;
            }
        }
        return GQ;
    }

}
