package ds.learning.trie;

/**
 * Created by hari.gudigundla on 16-09-23.
 */
public class TrieTester {

    public static void main(String[] args){

        MyTrie root= new MyTrie();
        root.insertWord("apple");
        root.insertWord("ball");
        root.insertWord("cat");
        root.insertWord("catch");

        System.out.println(root.searchWord("apple"));
        System.out.println(root.searchPrefix("app"));
        System.out.println(root.searchWord("cat"));
        System.out.println(root.searchPrefix("ca"));

    }
}
