package ds.learning.bst;

/**
 * Created by hari.gudigundla on 16-10-20.
 */
public class AVLTreeTester {
    public static void main(String[] args){
        AVLTree avl=new AVLTree();
        AVLTree.Node root=null;

        root=avl.insert(root,50);
        root=avl.insert(root,40);
        root=avl.insert(root,30);
        root=avl.insert(root,20);

        root=avl.delete(root,40);

        //BTreePrinter.printNode(root);

    }
}
