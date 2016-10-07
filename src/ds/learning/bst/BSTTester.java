package ds.learning.bst;

/**
 * Created by hari.gudigundla on 16-10-06.
 */
public class BSTTester {
    public static void main(String[] args){
        NodeBST bst= new NodeBST(10);
        bst.addNode(new NodeBST(8));
        bst.addNode(new NodeBST(12));

        bst.inOrderTraversal();
    }


}
