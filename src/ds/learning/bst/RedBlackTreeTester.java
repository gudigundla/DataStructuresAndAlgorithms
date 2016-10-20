package ds.learning.bst;

/**
 * Created by hari.gudigundla on 16-10-18.
 */
public class RedBlackTreeTester {
    public static void main(String[] args) {
        RedBlackTree rbTree = new RedBlackTree();
        RedBlackTree.Node root = null;

        root = rbTree.insert(root, 10);
        root = rbTree.insert(root, 50);
        root = rbTree.insert(root, 40);
        root = rbTree.insert(root, 30);
        root = rbTree.insert(root, 20);
        root = rbTree.insert(root, 25);
        root = rbTree.insert(root, 60);
        root = rbTree.insert(root, 35);
        root = rbTree.insert(root, 55);
        root = rbTree.insert(root, 100);
        root = rbTree.insert(root, 63);
        root = rbTree.insert(root, 47);
        root = rbTree.insert(root, 33);


        BTreePrinter.printNode(root);
        rbTree.delete(root,40);
        BTreePrinter.printNode(root);
        }
}