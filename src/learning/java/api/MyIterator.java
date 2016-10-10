package learning.java.api;

import java.util.Iterator;

/**
 * Created by hari.gudigundla on 16-10-09.
 * Implementing a custom iterator.
 * Inspired by http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/6-b14/java/util/ArrayList.java
 */
public class MyIterator implements Iterator {
    int cursor; //next element
    int size;
    int[] items;

    public MyIterator(int[] items,int size){
        this.size=size;
        this.items=items;
    }

    @Override
    public boolean hasNext() {
        return cursor!=size;
    }

    @Override
    public Object next() {
        int temp=cursor++;
        return items[temp];
        }


}
