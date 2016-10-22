package ds.learning.graph;

import java.util.*;

/**
 * Created by hari.gudigundla on 16-10-22.
 * Simple directed weighted acyclic graph representation - Adjacency lists
 * graph- https://upload.wikimedia.org/wikipedia/commons/3/3b/Shortest_path_with_direct_weights.svg
 */
public class MyGraph {

    //Adjacency Lists
    char[] vertices;
    Map<Character,Node> edges;

    public MyGraph(char[] vertices){
        this.vertices=vertices ;
        edges= new HashMap();
    }

    public void addEdge(char source, char destination, int weight){
        if(edges.containsKey(source)){
            Node node = edges.get(source);
            Node newNode=new MyGraph.Node(destination,weight);
            newNode.next=node;
            edges.put(source,newNode);
        }else{
            edges.put(source,new MyGraph.Node(destination,weight));
        }
    }

    public void DFS(char startVertex, Map<Character,Boolean> visited){
        Stack<Character> stack=new Stack();
        stack.add(startVertex);
        visited.put(startVertex,true);
        System.out.print(startVertex +" ");

        while(!stack.isEmpty()){
            char vertex=stack.peek();
            //if(!visited.get(vertex)){

                //add all neighbors
                Node neighbour=edges.get(vertex);
                //to find atleast one unvisited neighbour
                boolean flag=false;
                while(neighbour!=null){
                    if(!visited.get(neighbour.vertex)){
                        stack.add(neighbour.vertex);
                        System.out.print(neighbour.vertex +" ");
                        visited.put(neighbour.vertex,true);
                        flag=true;
                        break;
                    }
                    neighbour=neighbour.next;
                }
                if(!flag)
                    stack.pop();
            //}
        }

        for(char v:vertices){
            if(!visited.get(v)){
                DFS(v, visited);
            }
        }
        System.out.println();
    }

    public void BFS(char startVertex, Map<Character,Boolean> visited){
        Queue<Character> queue=new LinkedList();
        queue.add(startVertex);
        System.out.print(startVertex + " ");
        visited.put(startVertex,true);
        while(!queue.isEmpty()){
            char vertex=queue.remove();
            Node neighbour=edges.get(vertex);
            while(neighbour!=null){
                if(!visited.get(neighbour.vertex)) {
                    queue.add(neighbour.vertex);
                    System.out.print(neighbour.vertex + " ");
                    visited.put(neighbour.vertex, true);
                }
                neighbour = neighbour.next;
            }
        }

        for(char v:vertices){
            if(!visited.get(v)){
                BFS(v,visited);
            }
        }
        System.out.println();
    }

    private Map<Character,Boolean> getVisistedMap(){
        Map<Character,Boolean> visited=new HashMap();
        for(char c:vertices){
            visited.put(c,false);
        }
        return visited;
    }

    public static void main(String[] args){
        //create a graph, add edges using adjacency lists
        MyGraph graph=new MyGraph(new char[]{'A', 'B', 'C', 'D', 'E', 'F',});

        graph.addEdge('A','C',2);
        graph.addEdge('B','C',5);
        graph.addEdge('A','B',4);
        graph.addEdge('B','D',10);
        graph.addEdge('D','F',11);
        graph.addEdge('C','E',3);
        graph.addEdge('E','D',4);

        Map<Character,Boolean> visited=graph.getVisistedMap();

//        graph.DFS('C',visited);
        graph.BFS('B',visited);
    }

    public static class Node{
        char vertex;
        int weight;
        Node next;

        public Node(char vertex, int weight){
            this.vertex=vertex;
            this.weight=weight;
        }
     }
}
