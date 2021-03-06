package com.cacheserverdeploy.deploy.dataStructure;

/**
 * Created by tuzhenyu on 17-3-9.
 * @author tuzhenyu
 */
public class Edge implements Comparable<Edge>{
    private int startPoint;
    private int endPoint;
    private int cost;
    private int capacity;
    private int lastCapacity;

    public Edge(int startPoint,int endPoint,int cost,int capacity,int lastCapacity){
        this.startPoint = startPoint;
        this.endPoint = endPoint;
        this.cost = cost;
        this.capacity = capacity;
        this.lastCapacity = capacity;
    }

    public int getCost(){
        return cost;
    }

    public int getCapacity(){
        return capacity;
    }

    public int getLastCapacity(){
        return lastCapacity;
    }

    public void  setLastCapacity(int value){
        this.lastCapacity = value;
    }

    public int getStartPoint(){
        return startPoint;
    }

    public int getEndPoint(){
        return endPoint;
    }

    public int otherPoint(int vertex){
        if (vertex == startPoint)
            return endPoint;
        else if (vertex == endPoint)
            return startPoint;
        else
            throw new RuntimeException("Inconsistent edge");
    }

    public void exchange(){
        int tmp = this.startPoint;
        this.startPoint = this.endPoint;
        this.endPoint = tmp;

    }

    public int compareTo(Edge edge){
        if (this.cost < edge.cost)
            return -1;
        else if (this.cost>edge.cost)
            return 1;
        else
            return 0;
    }

    public String toString(){
        return String.format("(%d-%d %d %d)",startPoint,endPoint,cost,capacity);
    }
}
