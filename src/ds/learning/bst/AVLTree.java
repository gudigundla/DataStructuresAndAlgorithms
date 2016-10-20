package ds.learning.bst;

/**
 * Created by hari.gudigundla on 16-10-19.
 */
public class AVLTree {

    public class Node{
        int data;
        int height;
        int size;
        Node left;
        Node right;

       public Node(int data){
           this.data=data;
       }
    }

    public Node insert(Node root,int data){
        if(root==null){
            return new Node(data);
        }
        if(root.data <= data){
            root.right=insert(root.right,data);
        }else{
            root.left=insert(root.left,data);
        }

        int balance = balance(root.left, root.right);

        if(balance>1){ //left is taller than right
            if(height(root.left.left) >= height(root.left.right))
                root=rightRotate(root);
            else{
                root.left=leftRotate(root.left);
                root=rightRotate(root);
            }
        } else if(balance<-1){ //right is taller than left
            if(height(root.right.right)>=height(root.right.left))
                root=leftRotate(root);
            else{
                root.right=rightRotate(root.right);
                root=leftRotate(root);
            }
        } else { //balanced
            root.height=setHeight(root);
            root.size=setSize(root);
        }
        return root;
    }

    public Node delete(Node root, int data){
        if(root.data==data){
            //check if root has 0/1 childs
            if(root.left==null || root.right==null){
                //delete single child node case
                //return the node that replaces the toBeDeleted node
                return root.right==null? root.left: root.right;
            }else {
                Node inOrderSuccessor = findMin(root.right);
                root.data = inOrderSuccessor.data;
                return delete(root.right, inOrderSuccessor.data);
            }
        }

        if(root.data<=data)
            root.right=delete(root.right,data);
        else
            root.left=delete(root.left,data);

        //check if node is balanced and balance it if necessary
        int balance = balance(root.left, root.right);

        if(balance>1){ //left is taller than right
            if(height(root.left.left) >= height(root.left.right))
                root=rightRotate(root);
            else{
                root.left=leftRotate(root.left);
                root=rightRotate(root);
            }
        } else if(balance<-1){ //right is taller than left
            if(height(root.right.right)>=height(root.right.left))
                root=leftRotate(root);
            else{
                root.right=rightRotate(root.right);
                root=leftRotate(root);
            }
        } else { //balanced
            root.height=setHeight(root);
            root.size=setSize(root);
        }
        return root;
    }

    private Node findMin(Node node){
        Node previous=null;
        while(node!=null){
            previous=node;
            node=node.left;
        }
        return previous;
    }

    private Node leftRotate(Node node){
        Node newRoot=node.right;
        node.right=newRoot.left;
        newRoot.left=node;

        node.height=setHeight(node);
        node.size=setSize(node);

        newRoot.height=setHeight(newRoot);
        newRoot.size=setSize(newRoot);

        return newRoot;
    }

    private Node rightRotate(Node node){
        Node newRoot=node.left;
        node.left=newRoot.right;
        newRoot.right=node;

        node.height=setHeight(node);
        node.size=setSize(node);

        newRoot.height=setHeight(newRoot);
        newRoot.size=setSize(newRoot);
        return newRoot;
    }

    private int setHeight(Node node){
        if(node==null)
            return 0;
        else return 1+ Math.max(node.left==null?-1:node.left.height,node.right==null?-1:node.right.height);
    }

    private int setSize(Node node){
        if(node==null)
            return 0;
        else return 1 + Math.max(node.left==null ? 0 : node.left.size, node.right==null ? 0 : node.right.size);
    }

    private int balance(Node left, Node right){
        return height(left) - height(right);
    }

    private int height(Node node){
        return node==null? 0 : node.height;
    }
}
