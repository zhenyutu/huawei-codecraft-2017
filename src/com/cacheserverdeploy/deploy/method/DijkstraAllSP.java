package com.cacheserverdeploy.deploy.method;

import com.cacheserverdeploy.deploy.dataStructure.Edge;
import com.cacheserverdeploy.deploy.dataStructure.Graph;

/**
 * Created by tuzhenyu on 17-3-10.
 * @author tuzhenyu
 */
public class DijkstraAllSP {
    private DijkstraSP[] all;
    public DijkstraAllSP(Graph graph){
        all = new DijkstraSP[graph.getVertexNum()];
        for (int v=0;v<graph.getVertexNum();v++){
            all[v] = new DijkstraSP(graph,v);
        }
    }
    public Iterable<Integer> path(int s , int t){
        return all[s].pathTo(t);
    }

    public Integer cost(int s,int t){
        return all[s].distTo(t);
    }
}
