package string.related.problems;

/**
 * Created by hari.gudigundla on 16-10-13.
 */
public class StringIteration {
    public static void main(String[] args){
        String sample="This is a sample Text !!";

        for(int i=0; i<sample.length();i++){
            System.out.print(sample.charAt(i));
        }
        System.out.println();

        for(char c:sample.toCharArray()){
            System.out.print(c);
        }
        System.out.println();
    }
}
