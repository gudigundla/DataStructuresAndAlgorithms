package ds.learning.graph;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by hari.gudigundla on 16-10-23.
 */
public class HeapMap {

        VertexDistance[] items;
        Map<Integer,Integer> map;
        int last;
        int sizeOfArray;


        public HeapMap(int size){
            items=new VertexDistance[size];
            map=new HashMap();
            last=-1;
            this.sizeOfArray=size;
        }

        public HeapMap(){
            this(10);
        }

        public void insert(VertexDistance data){
            ensureEnoughSpace();
            items[++last]=data;
            map.put(data.vertex,last);
            bottomUpHeapify(last);
        }

        public boolean contains(int vertex){
            return map.containsKey(vertex);
        }

        public boolean isEmpty(){
            return map.isEmpty();
        }

        public double getShortestDistanceSoFar(int vertex){
            int index=map.get(vertex);
            return distance(items[index]);
        }

        public void decrease(int vertex, double distance){
            int index=map.get(vertex);
            items[index].distance=distance;
            bottomUpHeapify(index);
        }

        public VertexDistance deleteMin(){
            if(last>=0) {
                VertexDistance min = getMin();
                items[0] = items[last--];
                topDownHeapify(0);
                map.remove(min.vertex);
                return min;
            } else try {
                throw new Exception();
            } catch (Exception e) {
                e.printStackTrace();
            }

            return null;
        }

        public void deleteByIndex(int index){
            items[index]=items[last--];
            int parentIndex=getParentIndex(index);
            if(parentIndex>=0){
                if(distance(items[parentIndex])>distance(items[index]))
                    bottomUpHeapify(index);
                else topDownHeapify(index);

            }
            else topDownHeapify(index);
        }

        public VertexDistance getMin(){
            //we can throw an exception if heap is empty
            return last>=0? items[0] : null;
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
                if(distance(items[parentIndex])<=distance(items[index]))
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
            return distance(items[index])<=distance(left(index))&& distance(items[index])<=distance(right(index))? false: true;
        }

        private int findSmallestChildIndex(int parent){
            return distance(left(parent))<=distance(right(parent))? leftIndex(parent) : rightIndex(parent);
        }

        private double distance(VertexDistance node){
            return node==null? Double.MAX_VALUE : node.distance;
        }

        private int leftIndex(int parent){
            return 2*parent+1;
        }

        private int rightIndex(int parent){
            return 2*parent+2;
        }

        private VertexDistance left(int parent){
            if(leftIndex(parent)<=last)
                return items[leftIndex(parent)];
            else return null;
        }

        private VertexDistance right(int parent){
            if(rightIndex(parent)<=last)
                return items[rightIndex(parent)];
            else return null;
        }

        private void swap(int parentIndex, int childIndex){
            VertexDistance parent=items[parentIndex];
            VertexDistance child=items[childIndex];

            items[parentIndex]=items[childIndex];
            items[childIndex]=parent;



            swapMapItems(child.vertex,parent.vertex);
        }

        private void swapMapItems(int a, int b){
            int temp=map.get(a);
            map.put(a,map.get(b));
            map.put(b,temp);
        }

        private int getParentIndex(int childIndex){
            return (int)Math.ceil(childIndex/2d) - 1;
        }

        private void ensureEnoughSpace(){
            if(last>=sizeOfArray-1){
                //double the array, copy all elements
                VertexDistance[] newItems=new VertexDistance[2*sizeOfArray];

                for(int i=0;i<sizeOfArray;i++){
                    newItems[i]=items[i];
                }
                sizeOfArray=2*sizeOfArray;
                items=newItems;
            }
        }
    public static class VertexDistance{
        int vertex;
        double distance;

        public VertexDistance(int vertex,int distance){
            this.vertex=vertex;
            this.distance=distance;
        }
    }

    }
