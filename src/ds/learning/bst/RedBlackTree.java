package ds.learning.bst;

import java.util.LinkedList;
import java.util.Optional;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicReference;

/**
 * Created by hari.gudigundla on 16-10-18.
 */
public class RedBlackTree {
    public enum Color{
        RED,
        BLACK
    }

    public class Node{
        int data;
        Color color;
        Node left;
        Node right;
        Node parent;
        boolean isNullLeaf;
    }

    public Node insert(Node root,int data){
        return insert(null,root,data);
    }

    public Node delete(Node root,int data){
        AtomicReference<Node> rootReference=new AtomicReference();
        delete(root,data,rootReference);
        if(rootReference.get()==null){
            return root;
        }else return rootReference.get();
    }

    public void printRedBlackTree(Node root){
        printRedBlackTree(root,0);
    }

    public void breadthFirstTraversal(Node node){
        Queue<Node> queue=new LinkedList();
        queue.add(node);
        int level=-1;
        while(!queue.isEmpty()){
            Node next=queue.remove();
            level++;
            if(!next.isNullLeaf) {
                queue.add(next.left);
                queue.add(next.right);
                System.out.print(next.data+ "("+ next.color+")("+level+") ");}
            }
    }

    private void delete(Node current,int data,AtomicReference<Node> rootReference){
        if(current==null || current.isNullLeaf){
            return;
        }
        if(current.data==data){
            //if current has 0/1 child
            if(current.left.isNullLeaf || current.right.isNullLeaf){
                deleteOneChild(current, rootReference);
            }else { //else 2 children
                //replace current with in order successor & delete it
                Node inOrderSuccessor=findSmallest(current.right);
                current.data=inOrderSuccessor.data;
                delete(current.right,inOrderSuccessor.data,rootReference);
            }
        }else if(current.data>data){
            delete(current.left,data,rootReference);
        }else { //current.data < data
            delete(current.right,data,rootReference);
        }

    }

    private Node findSmallest(Node node){
        Node previous=null;
        while(node!=null && !node.isNullLeaf){
            previous=node;
            node=node.left;
        }
        return previous!=null ? previous : node;
    }

    private void deleteOneChild(Node nodeToBeDeleted, AtomicReference<Node> root){
        Node child=nodeToBeDeleted.right.isNullLeaf? nodeToBeDeleted.left : nodeToBeDeleted.right;
        replaceNode(nodeToBeDeleted,child,root);

        if(nodeToBeDeleted.color ==Color.BLACK){
            if(child.color==Color.RED){
                child.color=Color.BLACK;
            }else{
                deleteCase1 (child,root);
            }
        }
    }

    //if doubleBlackNode becomes root
    private void deleteCase1(Node doubleBlackNode, AtomicReference<Node> root){
        if(doubleBlackNode.parent==null){
            root.set(doubleBlackNode);
            return;
        }
        deleteCase2(doubleBlackNode, root);
    }

    //if sibling is red
    private void deleteCase2(Node doubleBlackNode, AtomicReference<Node> root) {
        Node sibling=findSiblingNode(doubleBlackNode).get();
        if(sibling.color==Color.RED){
            if(isLeftChild(sibling))
                rightRotate(sibling,true);
            else
                leftRotate(sibling,true);

            if(sibling.parent==null)
                root.set(sibling);
        }
        deleteCase3(doubleBlackNode,root);
    }

    //if sibling, sibling children, parent are all black, turn sibling into RED
    //parent will become doubleBlackNode
    private void deleteCase3(Node doubleBlackNode, AtomicReference<Node> root){
        Node sibling=findSiblingNode(doubleBlackNode).get();
        if(doubleBlackNode.parent.color==Color.BLACK && sibling.color==Color.BLACK
                && sibling.left.color==Color.BLACK && sibling.right.color==Color.BLACK){
            sibling.color=Color.RED;
            deleteCase1(doubleBlackNode.parent,root);
        }else{
            deleteCase4(doubleBlackNode,root);
        }
    }

    //if sibling is black, sibling children are black, parent is red
    //switch colors of sibling & parent.
    //This will fix it
    private void deleteCase4(Node doubleBlackNode, AtomicReference<Node> root){
        Node sibling=findSiblingNode(doubleBlackNode).get();
        if(doubleBlackNode.parent.color==Color.RED && sibling.color==Color.BLACK &&
                sibling.left.color==Color.BLACK && sibling.right.color==Color.BLACK){
            sibling.color=Color.RED;
            sibling.parent.color=Color.BLACK;
            return;
        }else
            deleteCase5(doubleBlackNode,root);
    }

    //if sibling is black
    private void deleteCase5(Node doubleBlackNode, AtomicReference<Node> root){
        Node sibling=findSiblingNode(doubleBlackNode).get();
        if(sibling.color==Color.BLACK){
            if(isLeftChild(doubleBlackNode) && sibling.right.color==Color.BLACK &&
                    sibling.left.color==Color.RED){
                rightRotate(sibling.left,true);
            } else if(!isLeftChild(doubleBlackNode) && sibling.right.color==Color.BLACK &&
                    sibling.left.color==Color.RED){
                leftRotate(sibling.right,true);
            }
        }
        deleteCase6(doubleBlackNode,root);
    }

    //sibling is black, sibling left is black, sibling right is red
    private void deleteCase6(Node doubleBlackNode, AtomicReference<Node> root){
        Node sibling=findSiblingNode(doubleBlackNode).get();
        sibling.color=sibling.parent.color;
        sibling.parent.color=Color.BLACK;
        if(isLeftChild(doubleBlackNode)){
            sibling.right.color=Color.BLACK;
            leftRotate(sibling,false);
        }else{
            sibling.left.color=Color.BLACK;
            rightRotate(sibling,false);
        }
        if(sibling.parent==null)
            root.set(sibling);
    }

    private void replaceNode(Node current,Node child, AtomicReference<Node> rootReference){
        child.parent=current.parent;
        //if current is root
        if(current.parent==null){
            rootReference.set(child);
        }else{
            if(!isLeftChild(current)){
                current.parent.right=child;
            }else{
                current.parent.left=child;
            }
        }
    }

    private void printRedBlackTree(Node root,int space){
        if(root==null || root.isNullLeaf){
            return;
        }
        printRedBlackTree(root.right, space + 5);
        for(int i=0;i<space;i++)
            System.out.print(" ");
        System.out.println(root.data+ " "+ (root.color == Color.RED ? "R" : "B"));
        printRedBlackTree(root.left,space+5);
    }

    private Node insert(Node parent, Node current, int data){
        if(current==null || current.isNullLeaf){
            if(parent!=null)
                return createRedNode(parent,data);
            else
                return createBlackNode(data);
        }

        if(current.data==data)
            throw new IllegalArgumentException("Duplicate data "+ data);

        boolean isLeft=false;
        if(current.data>data){
            System.out.println(data);
            Node left=insert(current,current.left,data);

            if(left==current.parent)
                return left;

            current.left=left;
            isLeft=true;
        }else{
            Node right=insert(current,current.right,data);

            if(right==current.parent)
                return right;

            current.right=right;
            isLeft=false;
        }

        if(isLeft){
            if(current.color ==Color.RED && current.left.color==Color.RED){
                Optional<Node> sibling= findSiblingNode(current);

                if(!sibling.isPresent() || sibling.get().color==Color.BLACK){

                    if(isLeftChild(current)){ //left-left
                        rightRotate(current, true);
                        System.out.println("Right Rotation- "+ current.data);
                    }else{ //left-right
                        rightRotate(current.left, false);
                        System.out.println("Right Rotation- " + current.data);
                        current=current.parent;
                        leftRotate(current,true);
                        System.out.println("Left Rotation- "+ current.data);
                    }

                }else{   //uncle RED
                    //color flip
                    System.out.println("Color Flip!!");

                    current.color=Color.BLACK;
                    sibling.get().color=Color.BLACK;

                    //if current is not RED
                    if(current.parent.parent!=null)
                        current.parent.color=Color.RED;
                }
            }
        } else{ //isLeft == false
            if(current.color==Color.RED && current.right.color==Color.RED){
                Optional<Node> sibling = findSiblingNode(current);
                if(!sibling.isPresent() || sibling.get().color == Color.BLACK){
                    if(!isLeftChild(current)){
                        leftRotate(current,true);
                        System.out.println("Left Rotation- "+ current.data);

                    }else{
                        leftRotate(current.right, false);
                        System.out.println("Left Rotation- " + current.data);
                        current=current.parent;
                        rightRotate(current,true);
                        System.out.println("Right Rotation- "+ current.data);
                    }
                }else{
                    System.out.println("Color Flip!!");
                    current.color=Color.BLACK;
                    sibling.get().color=Color.BLACK;
                    if(current.parent.parent!=null)
                        current.parent.color=Color.RED;
                }
            }
        }
        return current;
    }

    private void rightRotate(Node node, boolean changeColor){
        Node parent=node.parent;
        node.parent=parent.parent;
        if(parent.parent!=null){
            if(parent.parent.right==parent){
                parent.parent.right=node;
            }else{
                parent.parent.left=node;
            }
        }
        Node right=node.right;
        node.right=parent;
        parent.parent=node;
        parent.left=right;
        if(right!=null)
            right.parent=parent;

        if(changeColor){
            node.color=Color.BLACK;
            parent.color=Color.RED;
        }
    }

    private void leftRotate(Node node, boolean changeColor){
        Node parent=node.parent;
        node.parent=parent.parent;
        if(parent.parent!=null){
            if(parent.parent.right==parent){
                parent.parent.right=node;
            }else{
                parent.parent.left=node;
            }
        }

        Node left=node.left;
        node.left=parent;
        parent.parent=node;
        parent.right=left;
        if(left!=null)
            left.parent=parent;
        if(changeColor){
            node.color=Color.BLACK;
            parent.color=Color.RED;
        }

    }

    private Optional<Node> findSiblingNode(Node node){
        Node parent = node.parent;
        if(isLeftChild(node))
            return Optional.ofNullable(parent.right.isNullLeaf ? null : parent.right);
        else
            return Optional.ofNullable(parent.left.isNullLeaf ? null : parent.left);
    }

    private boolean isLeftChild(Node node){
        Node parent=node.parent;
        if(parent.left==node)
            return true;
        else return false;
    }

    private Node createNullLeafNode(Node parent){
        Node leaf=new Node();
        leaf.color=Color.BLACK;
        leaf.isNullLeaf=true;
        leaf.parent=parent;
        return leaf;
    }

    private Node createBlackNode(int value){
        Node bNode=new Node();
        bNode.data =value;
        bNode.color=Color.BLACK;
        bNode.left=createNullLeafNode(bNode);
        bNode.right=createNullLeafNode(bNode);
        return bNode;
    }

    private Node createRedNode(Node parent,int value){
        Node rNode=new Node();
        rNode.data =value;
        rNode.color=Color.RED;
        rNode.left=createNullLeafNode(rNode);
        rNode.right=createNullLeafNode(rNode);
        rNode.parent=parent;
        return rNode;
    }
}
