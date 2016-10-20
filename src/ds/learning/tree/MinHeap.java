package ds.learning.tree;

/**
 * Created by hari.gudigundla on 16-10-20.
 */
public class MinHeap {

   int[] items;
    int last;
    int sizeOfArray;

    public MinHeap(int size){
        items=new int[size];
        last=-1;
        this.sizeOfArray=size;
    }

    public MinHeap(){
        this(10);
    }

    public void insert(int data){
        ensureEnoughSpace();
        items[++last]=data;
        bottomUpHeapify(last);
    }

    public int deleteMin(){
        if(last>=0) {
            int min = getMin();
            items[0] = items[last--];
            topDownHeapify(0);
            return min;
        } else try {
            throw new Exception();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return Integer.MAX_VALUE;
    }

    public void deleteByIndex(int index){
        items[index]=items[last--];
        int parentIndex=getParentIndex(index);
        if(parentIndex>=0){
            if(items[parentIndex]>items[index])
                bottomUpHeapify(index);
            else topDownHeapify(index);

        }
        else topDownHeapify(index);
    }

    public int getMin(){
        //we can throw an exception if heap is empty
        return last>=0? items[0] : Integer.MAX_VALUE;
    }

    private int getMax(){
        //search from n/2 to nth node, n-1/2 index to n-1 index
        //O(n) time
        return 0;
    }

    private void topDownHeapify(int index){
        if(index<=last) {
            if (violatingNode(index)) {
                int smallestChildIndex = findSmallestChildIndex(index);
                swap(index, smallestChildIndex);
                topDownHeapify(smallestChildIndex);
            } else return;
        } else return;
    }

    private void bottomUpHeapify(int index){
        int parentIndex=getParentIndex(index);

        if(parentIndex>=0){
            if(items[parentIndex]<=items[index])
                return;
            else{
                //if violating, find smallest child & swap parent with it, then bottomUpHeapify of parent
                if(violatingNode(parentIndex)) {
                    int smallestChildIndex = findSmallestChildIndex(parentIndex);
                    swap(parentIndex, smallestChildIndex);
                    bottomUpHeapify(parentIndex);
                }else return;
            }
        } else return;

    }

    private boolean violatingNode(int index){
        return items[index]<=left(index) && items[index]<=right(index) ? false: true;
    }

    private int findSmallestChildIndex(int parent){
        return left(parent)<=right(parent)? leftIndex(parent) : rightIndex(parent);
    }

    private int leftIndex(int parent){
        return 2*parent+1;
    }

    private int rightIndex(int parent){
        return 2*parent+2;
    }

    private int left(int parent){
        if(leftIndex(parent)<=last)
            return items[leftIndex(parent)];
        else return Integer.MAX_VALUE;
    }

    private int right(int parent){
        if(rightIndex(parent)<=last)
            return items[rightIndex(parent)];
        else return Integer.MAX_VALUE;
    }

    private void swap(int parentIndex, int childIndex){
        int parent=items[parentIndex];
        items[parentIndex]=items[childIndex];
        items[childIndex]=parent;
    }

    private int getParentIndex(int childIndex){
        return (int)Math.ceil(childIndex/2d) - 1;
    }

    private void ensureEnoughSpace(){
        if(last==sizeOfArray-1){
            //double the array, copy all elements
            int[] newItems=new int[2*sizeOfArray];
            for(int i=0;i<sizeOfArray;i++){
                newItems[i]=items[i];
            }
            items=newItems;
        }
    }
}
