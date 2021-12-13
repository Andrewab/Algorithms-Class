/*
 * The class purpose is to make a directed graph and all that which has functionality associated with a directed graph.
 * (Any extra credit)
 * @author Dominic Meconi
 * @version Creation
 * oct 15 2021
 */

import java.util.*;


public class Graph<V> implements GraphIfc<V>{
    private Map<V, ArrayList<V>> map;
    //Uses a hashmap and ArrayList to represent an Adjacency list.
    public Graph(){
        this.map = new HashMap<>();
    }

    /*Since at each index in a hashmap there is the vertex itself followed by a chained list of vertices
    adjacent we can take size */
    public int numVertices() {
        return this.map.size();
    }

    //Counts the number of edges since each key resolves to an arraylist the size of each arraylist all summed up is the
    //number of edges. We have to subtract one per size because the initial vertex is not counted as an edge.
    public int numEdges() {
        int numEdgesCount = 0;
        for(V vert : this.map.keySet()){
            numEdgesCount = numEdgesCount + this.map.get(vert).size();
        }
        return numEdgesCount;
    }

    //clearing the hashmap removes all vertices and edges  Does a malloc of new list Java Garbage collector will
    //throw away the old one.
    public void clear() {
        this.map = new HashMap<>();
    }

    //adds vertex to hashmap by taking in a vertex as a parameter.
    public void addVertex(V vertex) {
        ArrayList<V> edges = new ArrayList<>();
        map.put(vertex,edges);

    }
    /*
    * Takes in two vertices of type v as parameters
    * if the vertices are not contained within the graph asserts error the vertices do not exist
    * otherwise swap out the current array with a new one with the added edge.
     */
    public void addEdge(V u, V v) {
        if(!this.map.containsKey(u) || !this.map.containsKey(v)){
            throw new AssertionError("The vertex or vertices to add a edge to does not exist");
        }
            ArrayList<V> overwriteArr = this.map.get(u);
            overwriteArr.add(v);
            this.map.replace(u,overwriteArr);
    }
    //Vertices are defined to be the keys that link to an array list on this hashmap thus
    //GetVertices returns the set of keys
    public Set getVertices() {
        return this.map.keySet();
    }

    /**
     * Get neighbor takes in a vertex it then checks to see if the map contains the vertex if it doesn't return an error
     * otherwise return the vertex which will contain neighbors.
     */
    public List getNeighbors(V vert) {
        if (!this.map.containsKey(vert)) {
            throw new AssertionError("This vertex does not exist:" + vert + "!");
        }
        return this.map.get(vert);
    }


    //Contains vertex takes in a vertex and checks to see if the hashmap has it thereby saying whether it is a vertex
    public boolean containsVertex(V vert) {
        return this.map.containsKey(vert);
    }

    /*
    * An edge is designed on a directed graph to be going in one direction.
    * This means we will only have to check a one direction from G=(u,v)
    * In this case we count the edge has originating from u and going to v thus the edge has as direction aspect.
    * To convert this to an undirected graph we would just have to add an && this.map.get(v).contains(u)
     */
    public boolean edgeExists(V u, V v) {
        if (!this.map.containsKey(u) && !this.map.containsKey(v)) {
            throw new IllegalArgumentException("Illegal Keys: " + u + " " + v);
        }
        if (this.map.get(u).contains(v)) {
            return true;
        }
        return false;
    }

    /*
    * Degree is defined as the number of nodes connected to a given vertex.
    * The Degree function takes in a vertex then return the size of the associated space meaning the quantity of
    * connected nodes on the graph.
    * */
    public int degree(V vert) {
        if(this.map.containsKey(vert)){
            return this.map.get(vert).size();
        }
        throw new IllegalArgumentException("Does not contain: " + vert);
    }


    /*
    * This method turns the graph into the string. It takes no parameters.
     */
    @Override
    public String toString() {
        return "Graph{" +
                "map=" + map +
                '}';
    }
}
