package ds.learning.tree;

/**
 * Created by hari.gudigundla on 16-10-07.
 */
public class BinaryTreeTester {
    public static void main(String[] args) {
        BinaryTree root = BinaryTree.createBinaryTree();

        BinaryTree.inOrderTraversal(root);
        System.out.println();
        BinaryTree.preOrderTraversal(root);
        System.out.println();
        BinaryTree.postOrderTraversal(root);
        System.out.println();

        BinaryTree.breadthFirstTraversal(root);
        System.out.println();
        System.out.println("Height+ "+BinaryTree.heightOfNode(root));

        if(BinaryTree.isBalanced(root))
            System.out.println("Balanced");
        else System.out.println("Unbalanced");

    }


}
