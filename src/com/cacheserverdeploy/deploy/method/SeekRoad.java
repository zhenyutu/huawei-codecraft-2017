package com.cacheserverdeploy.deploy.method;

import com.cacheserverdeploy.deploy.dataStructure.Graph;
import com.cacheserverdeploy.deploy.dataStructure.IndexMaxPQ;

/**
 * Created by tuzhenyu on 17-3-10.
 * @author tuzhenyu
 */
public class SeekRoad {
    private DijkstraAllSP dijkstraAllSP;
    private IndexMaxPQ<Integer> pq;
    private Boolean[] sourcePiont;
    private int vertexNum;

    public SeekRoad(Graph graph,DijkstraAllSP dijkstraAllSP){
        this.dijkstraAllSP = dijkstraAllSP;
        this.pq = new IndexMaxPQ<>(graph.getConsumer().length);
        this.sourcePiont = graph.getSource();
        this.vertexNum = graph.getVertexNum();

        for (int v=0;v<graph.getConsumer().length;v++){
            int startPoint = graph.getConsumer()[v][0];
            pq.insert(startPoint,dijkstraAllSP.cost(startPoint,1));
        }
        while (!pq.isEmpty()){
            int vertex = pq.delMax();

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

    }
}
