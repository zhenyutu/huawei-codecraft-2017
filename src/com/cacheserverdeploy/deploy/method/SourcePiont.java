package com.cacheserverdeploy.deploy.method;

import com.cacheserverdeploy.deploy.dataStructure.Edge;
import com.cacheserverdeploy.deploy.dataStructure.Graph;
import com.cacheserverdeploy.deploy.dataStructure.IndexMaxPQ;
import org.apache.log4j.Logger;

/**
 * Created by tuzhenyu on 17-3-10.
 * @author tuzhenyu
 */
public class SourcePiont {
    static Logger logger = Logger.getLogger (SourcePiont.class);
    public static void getSourcePoint(Graph graph){
        int sumOfHunger = 0;
        logger.info(graph.getSumOfConsumeValue());
        IndexMaxPQ<Integer> pq = new IndexMaxPQ<>(graph.getVertexNum());
        for (int v=0;v<graph.getVertexNum();v++){
            pq.insert(v,degree(graph,v));
        }
        for (int v=0;v<graph.getVertexNum();v++){
            int vertex = pq.delMax();
            graph.setSource(vertex,true);
            logger.info(pointCapatity(graph,vertex));
            sumOfHunger+=pointCapatity(graph,vertex);
            if (sumOfHunger > graph.getSumOfConsumeValue()){
                break;
            }
        }

    }

    private static int degree(Graph graph,int v){
        int degree = 0;
        for (Edge e : graph.getAdj(v))
            degree++;
        return degree;
    }

    private static int pointCapatity(Graph graph,int v){
        int hunger = 0;
        for (Edge e : graph.getAdj(v))
            hunger+=e.getCapacity();
        return hunger;
    }
}
