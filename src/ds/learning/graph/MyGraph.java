package ds.learning.graph;

import java.util.*;

/**
 * Created by hari.gudigundla on 16-10-22.
 * Simple directed weighted acyclic graph representation - Adjacency lists
 * DFS, BFS, Topological Sort, Dijkstra's Algorithm, DAG-Shortest Path, Bellman Ford Algorithm
 * graph- https://upload.wikimedia.org/wikipedia/commons/3/3b/Shortest_path_with_direct_weights.svg
 */
public class MyGraph {

    //Adjacency Lists
    char[] vertices;
    Map<Character,Node> edges;
    final static int INF=99999;

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

    public Map<Character,Integer> DAG_ShortestPath(MyGraph graph, char source){
        Stack<Character> stack=graph.topologicalSort();
        System.out.println(stack);

        Map<Character,Integer> shortestPath= this.initializeShortestPath();
        shortestPath.put(source,0);
        while(!stack.isEmpty()){
            char vertex=stack.pop();
            //update all adjacent vertices of vertex
            Node adjacentVertex = edges.get(vertex);
            while(adjacentVertex!=null){
                if( shortestPath.get(adjacentVertex.vertex) > shortestPath.get(vertex) + adjacentVertex.weight)
                    shortestPath.put(adjacentVertex.vertex, shortestPath.get(vertex) + adjacentVertex.weight);

                adjacentVertex=adjacentVertex.next;
            }
        }
    return shortestPath;
    }

    private Map<Character,Integer> initializeShortestPath(){
        Map<Character,Integer> shortestPath=new HashMap();
        for(char v:this.vertices){
            shortestPath.put(v,INF);
        }
        return shortestPath;
    }

    public Map<Character,Integer> bellmanFord(MyGraph graph, char source){
        Map<Character,Integer> shortestPath=initializeShortestPath();
        Map<Character,Character> parent=new HashMap();
        shortestPath.put(source,0);
        for(int i=1;i<= graph.vertices.length -1 ;i++){
            for(Map.Entry<Character,Node> entry: graph.edges.entrySet()){
                Node edge = entry.getValue();
                while(edge!=null){
                    relax(entry.getKey(), edge.vertex, edge.weight, shortestPath,parent);
                    edge=edge.next;
                }
            }

        }

        this.findIfNegativeCycleExists(graph,shortestPath,parent);

    return shortestPath;
    }

    private void findIfNegativeCycleExists(MyGraph graph, Map<Character,Integer> shortestPath,Map<Character,Character> parent){
        for(Map.Entry<Character,Node> entry: graph.edges.entrySet()){
            Node edge = entry.getValue();
            while(edge!=null){
                char source=entry.getKey();
                char destination =edge.vertex;
                int weight= edge.weight;
                int cost=shortestPath.get(destination);
                if(cost > shortestPath.get(source) + weight){
                    System.out.println("Negative Cycle vertex - " +destination);
                    shortestPath.put(destination, shortestPath.get(source) + weight);
                    parent.put(destination,source);
                }
                edge=edge.next;
            }
        }
    }

    private void relax(char source, char destination, int weight, Map<Character, Integer> shortestPath,Map<Character,Character> parent) {
        int cost=shortestPath.get(destination);
        if(cost > shortestPath.get(source) + weight){
            shortestPath.put(destination, shortestPath.get(source) + weight);
            //we can update parent here, if we want to track the path as well
            //update parent of destination as source
            parent.put(destination,source);
        }
    }

    private int[][] floydWarshall(MyGraph graph) {
        Map<Character,Integer> nVertices=getnVertices(graph);
        int[][] allPairsShortestPaths= initializeAllPairsShortestPaths(nVertices,graph);

        for(int k=0;k<graph.vertices.length;k++){
            for(int i=0;i<graph.vertices.length;i++){
                for(int j=0;j<graph.vertices.length;j++){
                    //relax (i,j) pair
                    if(allPairsShortestPaths[i][j] > allPairsShortestPaths[i][k] + allPairsShortestPaths[k][j]){
                        allPairsShortestPaths[i][j] = allPairsShortestPaths[i][k] + allPairsShortestPaths[k][j];
                    }
                }
            }
        }
    return allPairsShortestPaths;
    }

    private Map<Character, Integer> getnVertices(MyGraph graph) {
        Map<Character, Integer> nVertices=new HashMap();
        for(int i=0;i<graph.vertices.length;i++)
            nVertices.put(graph.vertices[i],i);
    return nVertices;
    }

    private int[][] initializeAllPairsShortestPaths(Map<Character,Integer> nVertices,MyGraph graph) {
        int[][] allPairsShortestPaths= new int[graph.vertices.length][graph.vertices.length];
        for(int i=0;i<allPairsShortestPaths.length;i++){
            int[] array= new int[graph.vertices.length];
            Arrays.fill(array,INF);
            allPairsShortestPaths[i]=array;
        }

        for(int i=0;i<allPairsShortestPaths[0].length;i++){
            allPairsShortestPaths[i][i]=0;
        }

        for(Map.Entry<Character,Node> entry:graph.edges.entrySet()){
            Node edge=entry.getValue();
            while(edge!=null){
                allPairsShortestPaths[nVertices.get(entry.getKey())][nVertices.get(edge.vertex)] = edge.weight;
                edge=edge.next;
            }
        }
        return allPairsShortestPaths;
    }

    public static void main(String[] args){
        //create a graph, add edges using adjacency lists
        //graph- https://upload.wikimedia.org/wikipedia/commons/3/3b/Shortest_path_with_direct_weights.svg
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

        //DAG
        //graph with negative edges
        //http://www2.hawaii.edu/~suthers/courses/ics311s16/Notes/Topic-19/addweight-counterexample-1.jpg
        MyGraph graph2=new MyGraph(new char[]{'S','X','Z','Y','W'});

        graph2.addEdge('S','X',2);
        graph2.addEdge('X','Y',5);
        graph2.addEdge('Y','Z',-10);
        graph2.addEdge('S','Z',2);
        graph2.addEdge('Z','W',2);

        Map<Character,Integer> shortestPath=graph2.DAG_ShortestPath(graph2, 'S');
        for(Map.Entry<Character,Integer> entry:shortestPath.entrySet())
            System.out.println(entry.getKey() +" - " + entry.getValue());

        System.out.println("-------------");

        //graph with negative cycle
        //http://i2.wp.com/techieme.in/wp-content/uploads/bf1.png
        MyGraph graph3=new MyGraph(new char[]{'A','B','C','D','E','F','G','H'});

        graph3.addEdge('A','E',6);
        graph3.addEdge('A','B',8);
        graph3.addEdge('B','C',6);
        graph3.addEdge('D','B',2);
        graph3.addEdge('E','F',3);
        graph3.addEdge('E','G',2);
        graph3.addEdge('F','G',6);
        graph3.addEdge('C','H',4);
        graph3.addEdge('H','G',-7);
        graph3.addEdge('G','D',1);
        graph3.addEdge('G','C',-1);

        Map<Character,Integer> shortestPaths2 = graph3.bellmanFord(graph3,'A');
        System.out.println("-------------");
        for(Map.Entry<Character,Integer> entry: shortestPaths2.entrySet())
            System.out.println(entry.getKey() + " - "+ entry.getValue());
/*
        //another graph with negative cycle
        MyGraph graph4=new MyGraph(new char[]{'A','B','C','D'});

        graph4.addEdge('A','B',1);
        graph4.addEdge('B','C',3);
        graph4.addEdge('C','D',2);
        graph4.addEdge('D','B',-6);

        Map<Character,Integer> shortestPaths3 = graph4.bellmanFord(graph4,'A');
        System.out.println("-------------");
        for(Map.Entry<Character,Integer> entry: shortestPaths3.entrySet())
            System.out.println(entry.getKey() + " - "+ entry.getValue());
*/
        System.out.println("-------------");

        int[][] allPairsShortestPaths= graph3.floydWarshall(graph3);
        for(int i=0;i<allPairsShortestPaths.length;i++){
            for(int j=0;j<allPairsShortestPaths[0].length;j++){
                System.out.print(allPairsShortestPaths[i][j] +" ");
            }
            System.out.println();
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
