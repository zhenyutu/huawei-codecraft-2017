package com.cacheserverdeploy.deploy.dataStructure;

import java.util.Scanner;

/**
 * Created by tuzhenyu on 17-3-8.
 * @author tuzhenyu
 */
public class Graph {
    private int vertexNum;
    private int edgeNum;
    private Bag<Edge>[] adj;
    private Integer[][] consumer;
    private Boolean[] source;
    private Integer sumOfConsumeValue = 0;

    public Graph(int vertexNum){
        this.vertexNum = vertexNum;
        this.edgeNum = 0;
        this.adj = (Bag<Edge>[]) new Bag[vertexNum];
        this.source = new Boolean[vertexNum];

        for (int i=0;i<vertexNum;i++){
            source[i] = false;
        }

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
        int consumeNum = scanner.nextInt();
        consumer = new Integer[consumeNum][2];
        for(int i=0;i<edgeNum;i++){
            Scanner tempScanner = new Scanner(graphContent[i+4]);
            tempScanner.useDelimiter(" ");
            int startPoint = tempScanner.nextInt();
            int endPoint = tempScanner.nextInt();
            int capacity = tempScanner.nextInt();
            int cost = tempScanner.nextInt();

            addEdge(new Edge(startPoint,endPoint,cost,capacity,capacity));
        }
        for (int i=0;i<consumeNum;i++){
            Scanner tempScanner = new Scanner(graphContent[i+6]);
            tempScanner.useDelimiter(" ");
            int consumePoint = tempScanner.nextInt();
            int consumeNode = tempScanner.nextInt();
            int consumeValue = tempScanner.nextInt();
            consumer[i][0]=consumeNode;
            consumer[i][1] = consumeValue;
            sumOfConsumeValue+=consumeValue;
        }
    }

    public int getVertexNum(){
        return vertexNum;
    }

    public int getEdgeNum(){
        return edgeNum;
    }

    public Integer[][] getConsumer(){
        return consumer;
    }


    public Integer getSumOfConsumeValue(){
       return sumOfConsumeValue;
    }

    public Boolean[] getSource(){
        return source;
    }

    public void setSource(int i,Boolean b){
        source[i] = b;
    }

    public Bag<Edge> getEdges(){
        Bag<Edge> bag = new Bag<>();
        for (int vertex=0;vertex<vertexNum;vertex++){
            for (Edge edge:adj[vertex]){
                if (edge.otherPoint(vertex)>vertex)
                    bag.add(edge);
            }
        }
        return bag;
    }

    public Edge getEdge(int s,int t){
        Edge edge = null;
        for (Edge e : adj[s]){
            if (e.otherPoint(s) == t)
                edge = e;
        }
        return edge;
    }

    public Iterable<Edge> getAdj(int vertex){
        return adj[vertex];
    }

    public void addEdge(Edge edge){
        int startPoint = edge.getStartPoint();
        int endPoint = edge.getEndPoint();
        adj[startPoint].add(edge);
        adj[endPoint].add(edge);
        edgeNum++;
    }

    public String toString(){
        String s = vertexNum + " vertex," + edgeNum + " edge\n";
        for (int i=0;i<vertexNum;i++){
            s+= i+": ";
            for (Edge adj : this.adj[i]){
                s+=adj.toString() + " ";
            }
            s+="\n";
        }
        return s;
    }

}
