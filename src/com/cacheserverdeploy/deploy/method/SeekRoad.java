package com.cacheserverdeploy.deploy.method;

import com.cacheserverdeploy.deploy.dataStructure.Graph;
import com.cacheserverdeploy.deploy.dataStructure.IndexMaxPQ;

/**
 * Created by tuzhenyu on 17-3-10.
 * @author tuzhenyu
 */
public class SeekRoad {
    IndexMaxPQ<Integer> pq;
    public SeekRoad(Graph graph,DijkstraAllSP dijkstraAllSP){
        pq = new IndexMaxPQ<>(graph.getConsumer().length);
        for (int v=0;v<graph.getConsumer().length;v++){
            int startPoint = graph.getConsumer()[v][0];
            pq.insert(startPoint,dijkstraAllSP.cost(startPoint,1));
        }
        while (!pq.isEmpty()){
            int vertex = pq.delMax();
        }
    }
}
