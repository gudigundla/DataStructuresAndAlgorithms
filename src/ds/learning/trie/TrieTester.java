package ds.learning.trie;

import java.util.List;

/**
 * Created by hari.gudigundla on 16-09-23.
 */
public class TrieTester {

    public static void main(String[] args){

        MyTrie root= new MyTrie('\0');
        //root.insertWord("apple");
        root.insertWord("ball");
        root.insertWord("cat");
        root.insertWord("catch");
        root.insertWord("cab");
        root.insertWord("camera");
        root.insertWord("cameron");
        root.insertWord("cameo");
        root.insertWord("car");
        root.insertWord("catepillar");
        root.insertWord("ca");
        root.insertWord("Apple");

        System.out.println(root.searchWord("apple"));
        System.out.println(root.searchPrefix("App"));
        System.out.println(root.searchWord("cat"));
        System.out.println(root.searchPrefix("catch"));
        System.out.println(root.searchPrefix(" "));


        System.out.println(root.searchPrefix("catch"));
        root.deleteWord("cat");
        root.deleteWord("cameo");
        System.out.println(root.searchPrefix("catch"));

        List<String> words=root.findAllWordsWithPrefix("ca");
        System.out.println(words);

    }
}
