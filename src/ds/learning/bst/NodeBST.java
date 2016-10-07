package ds.learning.bst;

/**
 * Created by hari.gudigundla on 16-10-06.
 */
public class NodeBST {

    private int value;
    private NodeBST left;
    private NodeBST right;

    public NodeBST(int value){
        this.value=value;
    }

    public void addNode(NodeBST node){
        if(node.value<value){
          if(this.left==null){
              this.left=node;
          }else this.left.addNode(node);

        } else{
            if(this.right==null){
                this.right=node;
            }else this.right.addNode(node);
        }
    }

    public void deleteNode(){

    }

    public void inOrderTraversal(){
        if(this.left==null)
            System.out.println(this.left.value);
        else this.left.inOrderTraversal();
        if(this!=null)
        System.out.println(this.value);
        if(this.right==null)
            System.out.println(this.right.value);
        else this.right.inOrderTraversal();
    }


}
