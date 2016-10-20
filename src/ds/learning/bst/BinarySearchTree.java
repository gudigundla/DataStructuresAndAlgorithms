package ds.learning.bst;

import ds.learning.tree.BinaryTree;

/**
 * Created by hari.gudigundla on 16-10-06.
 */
public class BinarySearchTree extends BinaryTree {

    public BinarySearchTree(int value) {
        super(value);
    }

    public void addNode(BinaryTree node){
        if(node.getValue()<this.getValue()){
          if(this.getLeft()==null)
              this.setLeft(node);
          else this.getLeft().addNode(node);

        } else{
            if(this.getRight()==null){
                this.setRight(node);
            }else this.getRight().addNode(node);
        }
    }

    //returns the node after deletion
    //so ultimately returns the root of BST
    @Override
    public BinaryTree deleteNode(BinaryTree node, int value){

        //replacing by min from right sub tree
        return deleteNodeUsingMinFromRightSubTree(node,value);
        //replacing by max of left sub tree
        //return deleteNodeUsingMaxFromLeftSubTree(node, data);
    }

    private BinaryTree deleteNodeUsingMaxFromLeftSubTree(BinaryTree node, int value) {
        return null;
    }

    private BinaryTree deleteNodeUsingMinFromRightSubTree(BinaryTree node, int value){
        //search for the node to delete
        if(node==null)
            return null;
        else{
            if(node.getValue()<value){
                node.setRight(deleteNode(node.getRight(), value));
            }
            else if(node.getValue()>value){
                node.setLeft(deleteNode(node.getLeft(),value));
            }
            else{//if we found the node we want to delete
                //case-1: no children
                if(node.getLeft()==null && node.getRight()==null)
                    return null;
                else if(node.getRight()==null){ //left not NULL
                    return node.getLeft();
                }
                else if(node.getLeft()==null){ //right not NULL
                    return node.getRight();
                }
                else{//both children are not NULL
                    BinaryTree minNodeInRightSubTree= minNodeInTree(node.getRight());
                    node.setValue(minNodeInRightSubTree.getValue());
                    //delete the minNodeInRightSubTree node
                    node.setRight(deleteNode(node.getRight(),minNodeInRightSubTree.getValue()));
                }
            }

        }
        return node;
    }

    private BinaryTree minNodeInTree(BinaryTree node) {
        BinaryTree previous=null;
        while (node!=null){
            previous=node;
            node=node.getLeft();
        }
        return previous;
    }

    private BinaryTree maxNodeInTree(BinaryTree node){
        BinaryTree previous=null;
        while(node!=null){
            previous=node;
            node=node.getRight();
        }
        return previous;
    }

    @Override
    public BinaryTree searchNode(BinaryTree node,int value){
        BinaryTree reqNode=null;
        if(node.getValue()<value)
            reqNode=searchNode(node.getRight(),value);
        else if(node.getValue()>value)
            reqNode=searchNode(node.getLeft(),value);
        else reqNode=node;

       return reqNode;
    }

    public BinaryTree successor(BinaryTree node, int data){
        return minNodeInTree(node.getRight());
    }

    public BinaryTree predecessor(BinaryTree node, int data){
        return maxNodeInTree(node.getLeft());
    }
}