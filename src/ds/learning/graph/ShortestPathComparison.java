package ds.learning.graph;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by hari.gudigundla on 16-10-22.
 * Simple directed weighted acyclic graph representation - Adjacency lists
 * DFS, BFS, Topological Sort, Dijkstra's Algorithm, DAG-Shortest Path, Bellman Ford Algorithm
 * graph- https://upload.wikimedia.org/wikipedia/commons/3/3b/Shortest_path_with_direct_weights.svg
 */

public class ShortestPathComparison {

        //Adjacency Lists
        int[] vertices;
        Map<Integer,Node> edges;
        final static double INF=99999;

        public ShortestPathComparison(){
            edges= new HashMap();
        }

        private void setVertices(int[] vertices){
            this.vertices=vertices;
        }

        public void addEdge(int source, int destination, double weight){
            if(edges.containsKey(source)){
                Node node = edges.get(source);
                Node newNode=new ShortestPathComparison.Node(destination,weight);
                newNode.next=node;
                edges.put(source,newNode);
            }else{
                edges.put(source,new ShortestPathComparison.Node(destination,weight));
            }
        }

        public Map<Integer,Double>  shortestPathFromSingleSource(int source){
            HeapMap shortestPathSoFar= new HeapMap();
            initializeHeapMap(shortestPathSoFar);
            Map<Integer,Integer> parent=new HashMap();
            Map<Integer,Double> shortestPath= new HashMap();

            shortestPathSoFar.decrease(source, 0);
            parent.put(source, 0);
            dijktras(shortestPathSoFar, shortestPath, parent);

            return shortestPath;
        }

        private void dijktras(HeapMap shortestPathSoFar, Map<Integer,Double> shortestPath, Map<Integer,Integer> parent){
            //take min from HeapMap
            //add it to shortestPath
            //update parent

            HeapMap.VertexDistance nextShortestVertex = shortestPathSoFar.deleteMin();

            shortestPath.put(nextShortestVertex.vertex,nextShortestVertex.distance);

            //for all neighbours of nextShortestVertex, if exists in shortestPathSoFar, update it if this new path is min
            Node neighbour= edges.get(nextShortestVertex.vertex);
            while(neighbour!=null){
                if(shortestPathSoFar.contains(neighbour.vertex)) {
                    double shortestDistanceSoFar= shortestPathSoFar.getShortestDistanceSoFar(neighbour.vertex);
                    double newDistance= shortestPath.get(nextShortestVertex.vertex) + neighbour.weight;
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
            for(int v:vertices){
                //System.out.println(v);
                shortestPathSoFar.insert(new HeapMap.VertexDistance(v, Integer.MAX_VALUE));
            }
        }

        private Map<Integer,Boolean> getVisistedMap(){
            Map<Integer,Boolean> visited=new HashMap();
            for(int c:vertices){
                visited.put(c,false);
            }
            return visited;
        }

        private Map<Integer,Double> initializeShortestPath(ShortestPathComparison graph){
            Map<Integer,Double> shortestPath=new HashMap();
            for(int v:graph.vertices){
                shortestPath.put(v,INF);
            }
            return shortestPath;
        }

        public Map<Integer,Double> bellmanFord(ShortestPathComparison graph, int source){
            Map<Integer,Double> shortestPath=initializeShortestPath(graph);
            Map<Integer,Integer> parent=new HashMap();
            shortestPath.put(source,0d);
            for(int i=1;i<= graph.vertices.length -1 ;i++){
                for(Map.Entry<Integer,Node> entry: graph.edges.entrySet()){
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

        private void findIfNegativeCycleExists(ShortestPathComparison graph, Map<Integer,Double> shortestPath,Map<Integer,Integer> parent){
            for(Map.Entry<Integer,Node> entry: graph.edges.entrySet()){
                Node edge = entry.getValue();
                while(edge!=null){
                    int source=entry.getKey();
                    int destination =edge.vertex;
                    double weight= edge.weight;
                    double cost=shortestPath.get(destination);
                    if(cost > shortestPath.get(source) + weight){
                        System.out.println("Negative Cycle vertex - " +destination);
                        shortestPath.put(destination, shortestPath.get(source) + weight);
                        parent.put(destination,source);
                    }
                    edge=edge.next;
                }
            }
        }

        private void relax(int source, int destination, double weight, Map<Integer, Double> shortestPath,Map<Integer,Integer> parent) {
            //System.out.println(destination);
            double cost=shortestPath.get(destination);
            if(cost > shortestPath.get(source) + weight){
                shortestPath.put(destination, shortestPath.get(source) + weight);
                //we can update parent here, if we want to track the path as well
                //update parent of destination as source
                parent.put(destination,source);
            }
        }

        private double[][] floydWarshall(ShortestPathComparison graph) {
            Map<Integer,Integer> nVertices=getnVertices(graph);
            double[][] allPairsShortestPaths= initializeAllPairsShortestPaths(nVertices,graph);

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

        private Map<Integer, Integer> getnVertices(ShortestPathComparison graph) {
            Map<Integer, Integer> nVertices=new HashMap();
            for(int i=0;i<graph.vertices.length;i++)
                nVertices.put(graph.vertices[i],i);
            return nVertices;
        }

        private double[][] initializeAllPairsShortestPaths(Map<Integer,Integer> nVertices,ShortestPathComparison graph) {
            double[][] allPairsShortestPaths= new double[graph.vertices.length][graph.vertices.length];
            for(int i=0;i<allPairsShortestPaths.length;i++){
                double[] array= new double[graph.vertices.length];
                Arrays.fill(array,INF);
                allPairsShortestPaths[i]=array;
            }

            for(int i=0;i<allPairsShortestPaths[0].length;i++){
                allPairsShortestPaths[i][i]=0;
            }

            for(Map.Entry<Integer,Node> entry:graph.edges.entrySet()){
                Node edge=entry.getValue();
                while(edge!=null){
                    allPairsShortestPaths[nVertices.get(entry.getKey())][nVertices.get(edge.vertex)] = edge.weight;
                    edge=edge.next;
                }
            }
            return allPairsShortestPaths;
        }

        public static void main(String[] args){

            //create graph from file

            ShortestPathComparison roadGraph=new ShortestPathComparison();
            Set<Integer> nodes = new HashSet();

            String placesDistance = "/Users/hari.gudigundla/Downloads/distances.csv";

            try (BufferedReader br = new BufferedReader(new FileReader(placesDistance))) {

                String line;
                while ((line = br.readLine()) != null) {
                    String[] tokens = line.split(",");
                    //System.out.println(Integer.parseInt(tokens[1]) + " - " + tokens[2] + " - " + tokens[4] );
                    if(!line.isEmpty()) {
                        int v1=Integer.parseInt(tokens[1]);
                        int v2=Integer.parseInt(tokens[4]);
                        if (!nodes.contains(v1))
                            nodes.add(v1);
                        if (!nodes.contains(v2))
                            nodes.add(v2);

                            roadGraph.addEdge(v1, v2, Double.parseDouble(tokens[2]));
                    }

                }

            } catch (IOException e) {
                e.printStackTrace();
            }

            int[] vertices = new int[nodes.size()];
            int index=0;
            //set vertices
            for(int v:nodes){
                vertices[index]=v;
                index++;
            }

            roadGraph.setVertices(vertices);

            System.out.println(roadGraph.vertices.length);


            //Dijkstras's
            long start_time = System.currentTimeMillis();

            Map<Integer,Double> shortestPaths = roadGraph.shortestPathFromSingleSource(100);

/*            for( Map.Entry<Integer,Double> path:shortestPaths.entrySet()){
                System.out.println(path.getKey() + " - " + path.getValue());
            }
*/
            long end_time = System.currentTimeMillis();
            long difference = end_time-start_time;
            System.out.println("Dijkstra's - "+difference);

            start_time = System.currentTimeMillis();

            Map<Integer,Double> shortestPaths3 = roadGraph.bellmanFord(roadGraph, 100);
            System.out.println("-------------");

/*            for(Map.Entry<Integer,Double> entry: shortestPaths3.entrySet())
                System.out.println(entry.getKey() + " - "+ entry.getValue());
*/
            end_time = System.currentTimeMillis();
            difference = end_time-start_time;
            System.out.println("Bellman - "+difference);

            System.out.println("-------------");

            start_time = System.currentTimeMillis();

            double[][] allPairsShortestPaths= roadGraph.floydWarshall(roadGraph);
/*            for(int i=0;i<allPairsShortestPaths.length;i++){
                for(int j=0;j<allPairsShortestPaths[0].length;j++){
                    System.out.print(allPairsShortestPaths[i][j] +" ");
                }
                System.out.println();
            }
*/
            end_time = System.currentTimeMillis();
            difference = end_time-start_time;
            System.out.println("FloydWarshall - "+difference);

        }

        public static class Node{
            int vertex;
            double weight;
            Node next;
            Node parent;

            public Node(int vertex, double weight){
                this.vertex=vertex;
                this.weight=weight;
            }

        }



}
