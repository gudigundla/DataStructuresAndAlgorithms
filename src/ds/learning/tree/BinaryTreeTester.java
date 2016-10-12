package ds.learning.tree;

import java.util.*;

/**
 * Created by hari.gudigundla on 16-10-07.
 */
public class BinaryTreeTester {
    public static void main(String[] args) {
        BinaryTree root = BinaryTree.createBinaryTree(8);

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

        BinaryTree.connectSiblingsOfaTree(root);

        System.out.println();

        List<Integer> items= Arrays.asList(1,2,3,4,5);
        ListIterator<Integer> iterator= items.listIterator(items.size());
        while (iterator.hasPrevious()){
            System.out.print(iterator.previous()+ "("+ iterator.nextIndex() +")");
        }
        System.out.println();
        while (iterator.hasNext()){
            System.out.print(iterator.next());
        }
    }
}
