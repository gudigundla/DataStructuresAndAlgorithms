package ds.learning.trie;

import java.util.*;

/**
 * Created by hari.gudigundla on 16-09-23.
 *
 */
public class MyTrie {
    char letter;
    Map<Character,MyTrie> children;
    boolean endOfWord;

    public MyTrie(char letter){
        this.letter=letter;
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
                MyTrie temp=new MyTrie(word.charAt(i));
                currentNode.children.put(word.charAt(i),temp);
                currentNode=temp;
            }

            if(i==word.length()-1){
                currentNode.endOfWord=true;
            }
        }
    }


    //search MyTrie Node method
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

    //search word
    public boolean searchWord(String word){
        MyTrie lastNode=searcMyTrieNode(word);
        if(lastNode!=null && lastNode.endOfWord){
            return true;
        }else return false;

    }

    //search prefix
    public boolean searchPrefix(String prefix){
        MyTrie lastNode=searcMyTrieNode(prefix);
        if(lastNode!=null)
            return true;
        else return false;
    }

    //delete word from trie
    public void deleteWord(String word){

        MyTrie currentNode=this;
        //stack to keep track of all nodes visited
        Stack<MyTrie> visitedNodes=new Stack<MyTrie>();
        visitedNodes.push(this);
        for(int i=0; i<word.length(); i++){
            if(currentNode.children.get(word.charAt(i)) != null){
                currentNode=currentNode.children.get(word.charAt(i));
                visitedNodes.push(currentNode);
            }
            //else word doesn't exist
        }

        if(!currentNode.children.isEmpty()){
            currentNode.endOfWord=false;
        }
        else {
            while (!visitedNodes.isEmpty()) {
                MyTrie child = visitedNodes.pop();
                if (child.letter != '\0') {
                    MyTrie parent = visitedNodes.peek();
                    if (child.children.isEmpty())
                        parent.children.remove(child.letter);
                }
            }
        }

    }

    //helper method to find list of words with currentNode
    private void wordsWithCurrentNode(MyTrie node, String prefix,List<String> list){

        for (Map.Entry<Character,MyTrie>  letter: node.children.entrySet()){
            String tempPrefix= prefix+letter.getKey();
            if(letter.getValue().endOfWord){
                list.add(tempPrefix);
            }
            wordsWithCurrentNode(letter.getValue(),tempPrefix,list);
        }
    }

    //Find the list of words with the prefix
    public List<String> findAllWordsWithPrefix(String prefix){
        MyTrie lastNode=searcMyTrieNode(prefix);
        List<String> wordsWithPrefix = new ArrayList<String>();
        if(lastNode!=null){
            if(lastNode.endOfWord)
                wordsWithPrefix.add(prefix);
            wordsWithCurrentNode(lastNode, prefix, wordsWithPrefix);
        }
        return wordsWithPrefix;
    }

}