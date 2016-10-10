package learning.java.api;

import java.util.Iterator;

/**
 * Created by hari.gudigundla on 16-10-09.
 */
public class MyIteratorTester {
    public static void main(String[] args) {
        MyList list=new MyList();
        list.add(1);
        list.add(2);
        list.add(3);
        list.add(4);

        for(Object o:list){
            System.out.print((int) o);
        }
        System.out.println();

        Iterator iterator=list.iterator();
        while (iterator.hasNext()){
            System.out.print(iterator.next());
        }
        System.out.println();
    }
}
