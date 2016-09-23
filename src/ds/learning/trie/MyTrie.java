package ds.learning.trie;

import java.util.Map;
import java.util.TreeMap;

/**
 * Created by hari.gudigundla on 16-09-23.
 */
public class MyTrie {

    Map<Character,MyTrie> children;
    boolean endOfWord;

    public MyTrie(){
        children=new TreeMap<Character, MyTrie>();
        endOfWord=false;
    }

    //insert method
    public void insertWord(String word){
        MyTrie currentNode=this;
        for(int i=0; i< word.length(); i++){
            if(currentNode.children.get(word.charAt(i)) != null){
                currentNode=currentNode.children.get(word.charAt(i));
            }
            else{
                MyTrie temp=new MyTrie();
                currentNode.children.put(word.charAt(i),temp);
                currentNode=temp;
            }

            if(i==word.length()-1){
                currentNode.endOfWord=true;
            }
        }
    }


    //search method
    public MyTrie searcMyTrieNode(String word){
        MyTrie currentNode=this;
        for(int i=0; i<word.length(); i++){
            if(currentNode.children.get(word.charAt(i)) != null){
                currentNode=currentNode.children.get(word.charAt(i));
            }
            else return null;

        }
       return currentNode;
    }

    public boolean searchWord(String word){
        MyTrie lastNode=searcMyTrieNode(word);
        if(lastNode!=null && lastNode.endOfWord){
            return true;
        }else return false;

    }

    public boolean searchPrefix(String prefix){
        MyTrie lastNode=searcMyTrieNode(prefix);
        if(lastNode!=null)
            return true;
        else return false;
    }


}
