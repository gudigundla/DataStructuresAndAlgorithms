package ds.learning.graph;

import java.util.*;

/**
 * Created by hari.gudigundla on 16-10-22.
 * Simple directed weighted acyclic graph representation - Adjacency lists
 * DFS, BFS, Topological Sort, Dijkstra's Algorithm
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


    }

    public void DFT(){
        Map<Character,Boolean> visited=this.getVisistedMap();
        for(char v:vertices){
            if(!visited.get(v)){
                System.out.print(v +" ");
                visited.put(v,true);
                this.DFS(v, visited);
            }
        }
        System.out.println();
    }

    public void BFT(char startVertex, Map<Character,Boolean> visited){
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
                BFT(v, visited);
            }
        }
        System.out.println();
    }

    public Stack<Character> topologicalSort(){
        Map<Character,Boolean> visited=this.getVisistedMap();
        Stack<Character> stack=new Stack();
        for(char v:vertices){
            if(!visited.get(v)){
                this.topoligicalSortOfVertex(v, stack, visited);
            }
        }
    return stack;
    }

    public Map<Character,Integer>  shortestPathFromSingleSource(char source){
        HeapMap shortestPathSoFar= new HeapMap();
        initializeHeapMap(shortestPathSoFar);
        Map<Character,Character> parent=new HashMap();
        Map<Character,Integer> shortestPath= new HashMap();

        shortestPathSoFar.decrease(source, 0);
        parent.put(source,'\0');
        dijktras(shortestPathSoFar, shortestPath, parent);

        return shortestPath;
        }

    private void dijktras(HeapMap shortestPathSoFar, Map<Character,Integer> shortestPath, Map<Character,Character> parent){
        //take min from HeapMap
        //add it to shortestPath
        //update parent

        HeapMap.VertexDistance nextShortestVertex = shortestPathSoFar.deleteMin();

        shortestPath.put(nextShortestVertex.vertex,nextShortestVertex.distance);

        //for all neighbours of nextShortestVertex, if exists in shortestPathSoFar, update it if this new path is min
        Node neighbour= edges.get(nextShortestVertex.vertex);
        while(neighbour!=null){
            if(shortestPathSoFar.contains(neighbour.vertex)) {
                int shortestDistanceSoFar= shortestPathSoFar.getShortestDistanceSoFar(neighbour.vertex);
                int newDistance= shortestPath.get(nextShortestVertex.vertex) + neighbour.weight;
                if( newDistance < shortestDistanceSoFar){
                    shortestPathSoFar.decrease(neighbour.vertex, newDistance);
                    parent.put(neighbour.vertex,nextShortestVertex.vertex);
                }
            }
            neighbour=neighbour.next;
        }
        if(!shortestPathSoFar.isEmpty())
            dijktras(shortestPathSoFar, shortestPath, parent);
    }

    private void initializeHeapMap(HeapMap shortestPathSoFar){
        for(char v:vertices){
            shortestPathSoFar.insert(new HeapMap.VertexDistance(v, Integer.MAX_VALUE));
        }
    }

    private void topoligicalSortOfVertex(char vertex,Stack<Character> stack,Map<Character,Boolean> visited){
        Node neighbour=edges.get(vertex);
        while(neighbour!=null){
            if(!visited.get(neighbour.vertex)){
                topoligicalSortOfVertex(neighbour.vertex,stack,visited);
                }
            neighbour=neighbour.next;
        }
        visited.put(vertex,true);
        stack.push(vertex);
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
        MyGraph graph=new MyGraph(new char[]{'A', 'B', 'C', 'D', 'E', 'F'});

        graph.addEdge('A','C',2);
        graph.addEdge('B','C',5);
        graph.addEdge('A','B',4);
        graph.addEdge('B','D',10);
        graph.addEdge('D','F',11);
        graph.addEdge('C','E',3);
        graph.addEdge('E','D',4);

//        graph.DFT();
//        graph.BFT('B',visited);
        Stack<Character> stack=graph.topologicalSort();
//        while(!stack.isEmpty())
//            System.out.println(stack.pop());

        Map<Character,Integer> shortestPaths = graph.shortestPathFromSingleSource('A');

        for( Map.Entry<Character,Integer> path:shortestPaths.entrySet()){
            System.out.println(path.getKey() + " - " + path.getValue());
        }
    }

    public static class Node{
        char vertex;
        int weight;
        Node next;
        Node parent;

        public Node(char vertex, int weight){
            this.vertex=vertex;
            this.weight=weight;
        }

     }



}
