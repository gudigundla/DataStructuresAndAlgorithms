package ds.learning.tree;

/**
 * Created by hari.gudigundla on 16-10-20.
 */
public class MinHeapTester {
    public static void main(String[] args){
        MinHeap heap=new MinHeap(3);
        heap.insert(10);
        heap.insert(20);
        heap.insert(30);
        heap.insert(40);
        heap.insert(5);

        System.out.println(heap.deleteMin());
        System.out.println(heap.deleteMin());
    }
}
