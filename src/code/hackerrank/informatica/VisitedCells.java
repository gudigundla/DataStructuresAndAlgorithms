package code.hackerrank.informatica;

/**
 * Created by hari.gudigundla on 16-10-16.
 */
public class VisitedCells {
    public static void main(String[] args){
        System.out.print(totalCellsVisited(9,9));
    }

    public static int totalCellsVisited(int n, int m) {
        //also covers (0,0) case
        //if n=1 or m=1, babai covers all cells
        if(m<2 || n<2){
            return m*n;
        }
        //if n is odd
        //using recursion, after babai covers first 2*n cells
        //its a new sub-problem to walk (m-2,n)
        else if(n%2==1){
            return 2*n + totalCellsVisited(m-2,n);
        }
        //if n is even
        //babai will only cover first 2 columns of all rows
        else{
            return 2*n;
        }
    }
}
