package com.vaani.ds.graph;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Adjacency list representation of the graph
 *
 * Representation: http://www.geeksforgeeks.org/graph-and-its-representations/
 * DFS: http://www.geeksforgeeks.org/depth-first-traversal-for-a-graph/
 * BFS: http://www.geeksforgeeks.org/breadth-first-traversal-for-a-graph/
 *
 */
public class Graph {
    int n;
    List<Integer>[] adj;

    public Graph(int n){
        this.n = n;
        adj = (List<Integer>[])new List[n];
        for(int i = 0; i < n; i++){
            adj[i] = new ArrayList<Integer>();
        }
    }

    public void addEdge(int v, int w){
        adj[v].add(w);
    }

    /**
     * Depth traverses only the vertices reachable from a given source vertex
     * Time: O(V + E)
     *
     * @param v
     *
     */
    public void dfs(int v){
        boolean[] visited = new boolean[n];
        dfsUtil(v, visited);
    }

    /**
     * Complete DFS traversal
     * Time: O(V + E)
     *
     */
    public void dfs(){
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++){
            if(!visited[i]){
                dfsUtil(i, visited);
            }
        }
    }

    private void dfsUtil(int v, boolean[] visited){
        visited[v] = true;
        System.out.print(v + " ");

        for(int num : adj[v]){
            if(!visited[num]){
                dfsUtil(num, visited);
            }
        }
    }

    /**
     * Breadth traverses only the vertices reachable from a given source vertex
     * Time: O(V + E)
     *
     * @param v
     *
     */
    public void bfs(int v) {
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<Integer>();
        queue.offer(v);
        while(!queue.isEmpty()){
            int n = queue.poll();
            visited[n] = true;
            System.out.print(n + " ");
            for(int num : adj[n]){
                if(!visited[num]){
                    queue.offer(num);
                }
            }
        }
    }

    public static void main(String[] args){
        Graph graph = new Graph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);
        //graph.dfs();
        graph.bfs(2);
    }
}
