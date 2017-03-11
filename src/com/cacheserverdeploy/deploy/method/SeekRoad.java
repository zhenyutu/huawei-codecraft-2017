package com.cacheserverdeploy.deploy.method;

import com.cacheserverdeploy.deploy.dataStructure.*;

/**
 * Created by tuzhenyu on 17-3-10.
 * @author tuzhenyu
 */
public class SeekRoad {
    private Graph graph;
    private DijkstraAllSP dijkstraAllSP;
    private IndexMaxPQ<Integer> pq;
    private Boolean[] sourcePiont;
    private int vertexNum;

    public SeekRoad(Graph graph,DijkstraAllSP dijkstraAllSP){
        this.graph = graph;
        this.dijkstraAllSP = dijkstraAllSP;
        this.pq = new IndexMaxPQ<>(graph.getVertexNum());
        this.sourcePiont = graph.getSource();
        this.vertexNum = graph.getVertexNum();

        for (int v=0;v<graph.getConsumer().length;v++){
            int startPoint = graph.getConsumer()[v][0];
            int targetPoint = getSourcePoint(startPoint);
            pq.insert(startPoint,dijkstraAllSP.cost(startPoint,targetPoint));
        }
        while (!pq.isEmpty()){
            int vertex = pq.delMax();
            int target = getSourcePoint(vertex);
            seek(vertex,target);

        }
    }

    private Integer getSourcePoint(int vertex){
        Integer source = null;
        int maxCost = Integer.MAX_VALUE;
        for (int i=0;i<vertexNum;i++){
            if (sourcePiont[i]){
                int tempCost = dijkstraAllSP.cost(vertex,i);
                if (tempCost<maxCost){
                    source = i;
                    maxCost = tempCost;
                }
            }
        }
        return source;
    }

    private void seek(int s,int t){
        int hunger = graph.getHunger(s);
        if (hunger>0){
            for (Edge edge : dijkstraAllSP.edgePath(s,t)){
                int edgeStartPoint = edge.getStartPoint();
                int edgeEndPoint = edge.getEndPoint();
                int edgeStartPointHunger = graph.getHunger(edgeStartPoint);
                int edgeLastCapacity = edge.getLastCapacity();

                if (edgeLastCapacity >=edgeStartPointHunger){
                    edge.setLastCapacity(edge.getLastCapacity()-edgeStartPointHunger);
                    graph.setHunger(edgeEndPoint,edgeStartPointHunger);
                    graph.setHunger(edgeStartPoint,0);
                }else if(edgeLastCapacity >0 && edgeLastCapacity < edgeStartPointHunger){
                    edge.setLastCapacity(0);
                    graph.setHunger(edgeStartPoint,edgeStartPointHunger-edge.getLastCapacity());
                    graph.setHunger(edgeEndPoint,edge.getLastCapacity());
                }else {
                    IndexMinPQ<Integer> mpq = minAdj(edgeStartPoint,t);
                    while (!mpq.isEmpty()){
                        int next = mpq.delMin();
                        Edge e = graph.getEdge(edgeStartPoint,next);
                        if (e.getLastCapacity() != 0){
                            if (e.getLastCapacity()>=edgeStartPointHunger){
                                e.setLastCapacity(e.getLastCapacity()-edgeStartPointHunger);
                                graph.setHunger(next,edgeStartPointHunger);
                                graph.setHunger(edgeStartPoint,0);
                                pq.insert(next,dijkstraAllSP.cost(next,t));
                                break;
                            }else if(e.getLastCapacity()>0 && e.getLastCapacity()< edgeStartPointHunger){
                                e.setLastCapacity(0);
                                graph.setHunger(edgeStartPoint,edgeStartPointHunger-e.getLastCapacity());
                                graph.setHunger(next,edge.getLastCapacity());
                                pq.insert(next,dijkstraAllSP.cost(next,t));
                            }
                        }
                    }
                    break;
                }
            }
            for (Integer vertex : dijkstraAllSP.path(s,t)){
                if (graph.getHunger(vertex)!= 0){
                    pq.insert(vertex,dijkstraAllSP.cost(vertex,t));
                }
            }
        }else{

        }

    }

    private IndexMinPQ<Integer> minAdj(int s,int t){
        IndexMinPQ<Integer> mpq = new IndexMinPQ<>(graph.getAdj(s).length());
        for (Edge edge:graph.getAdj(s)){
            int start = edge.otherPoint(s);
            mpq.insert(start,dijkstraAllSP.cost(start,t)+edge.getCost());
        }
        return mpq;
    }
}
