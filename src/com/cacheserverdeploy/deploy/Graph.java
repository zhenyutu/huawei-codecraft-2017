package com.cacheserverdeploy.deploy;

import java.util.Scanner;

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

    public Graph(String[] graphContent){
        this(new Scanner(graphContent[0]).useDelimiter(" ").nextInt());
        Scanner scanner = new Scanner(graphContent[0]);
        scanner.useDelimiter(" ");
        int vertexNum = scanner.nextInt();
        int edgeNum = scanner.nextInt();
        for(int i=0;i<edgeNum;i++){
            Scanner tempScanner = new Scanner(graphContent[i+4]);
            tempScanner.useDelimiter(" ");
            int vertex1 = tempScanner.nextInt();
            int vertex2 = tempScanner.nextInt();

            addEdge(vertex1,vertex2);
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

    public String toString(){
        String s = vertexNum + " vertex," + edgeNum + " edge\n";
        for (int i=0;i<vertexNum;i++){
            s+= i+": ";
            for (int adj : this.adj[i]){
                s+=adj + " ";
            }
            s+="\n";
        }
        return s;
    }

}
