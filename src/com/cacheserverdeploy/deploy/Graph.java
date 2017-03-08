package com.cacheserverdeploy.deploy;

/**
 * Created by tuzhenyu on 17-3-8.
 * @author tuzhenyu
 */
public class Graph {
    private int vertexNum;
    private int edgeNum;
    private Bag<Integer>[] adj;

    public Graph(int vertexNum){
        this.vertexNum = vertexNum;
        this.edgeNum = 0;
        this.adj = (Bag<Integer>[]) new Bag[vertexNum];

        for (int i=0;i<vertexNum;i++){
            adj[i] = new Bag<>();
        }
    }

    public int getVertexNum(){
        return vertexNum;
    }

    public int getEdgeNum(){
        return edgeNum;
    }

    public Iterable<Integer> getAdj(int vertex){
        return adj[vertex];
    }

    public void addEdge(int m,int n){
        adj[m].add(n);
        adj[n].add(m);
        edgeNum++;
    }

}
