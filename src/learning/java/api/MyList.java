package learning.java.api;

import java.util.Iterator;

/**
 * Created by hari.gudigundla on 16-10-09.
 */
public class MyList implements  Iterable {
    int cursor;
    public int[] items= new int[10];

    @Override
    public Iterator iterator(){
        return new MyIterator(items,cursor);
    }

    public void add(int a){
        if(cursor<10){
            items[cursor]=a;
            cursor++;
        }
    }

}
