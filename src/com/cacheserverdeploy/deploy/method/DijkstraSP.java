package com.cacheserverdeploy.deploy.method;

import com.cacheserverdeploy.deploy.dataStructure.*;

/**
 * Created by tuzhenyu on 17-3-10.
 * @author tuzhenyu
 */
public class DijkstraSP {
    private Integer[] distTo;
    private Edge[] edgeTo;
    private IndexMinPQ<Integer> pq;
    private Integer source;

    public DijkstraSP(Graph G, int s) {
        this.source = s;
        distTo = new Integer[G.getVertexNum()];
        edgeTo = new Edge[G.getVertexNum()];
        for (int v = 0; v < G.getVertexNum(); v++)
            distTo[v] = Integer.MAX_VALUE;
        distTo[s] = 0;

        pq = new IndexMinPQ<>(G.getVertexNum());
        pq.insert(s, distTo[s]);
        while (!pq.isEmpty()) {
            int v = pq.delMin();
            for (Edge e : G.getAdj(v))
                relax(e,v);
        }

//        assert check(G, s);
    }

    private void relax(Edge e,Integer start) {
        int v = start, w = e.otherPoint(start);
        if (distTo[w] > distTo[v] + e.getCost()) {
            distTo[w] = distTo[v] + e.getCost();
            edgeTo[w] = e;
            if (pq.contains(w))
                pq.decreaseKey(w, distTo[w]);
            else
                pq.insert(w, distTo[w]);
        }
    }

    public Integer distTo(int v) {
        return distTo[v];
    }

    public Iterable<Integer> pathTo(int v) {
        Stack<Integer> path = new Stack<>();
        int tmp =v;
        path.push(tmp);
        while (tmp != source){
            Edge e = edgeTo[tmp];
            tmp = e.otherPoint(tmp);
            path.push(tmp);
        }

        return path;
    }

    public Iterable<Edge> edgePathTo(int t){
        Stack<Edge> path = new Stack<>();
        int tmp =t;
        while (tmp != source){
            Edge e = edgeTo[tmp];
            if (e.getEndPoint()!=tmp)
                e.exchange();
            path.push(e);
            tmp = e.otherPoint(tmp);
        }
        return path;
    }

    public Edge firstEdgePathTo(int t){
        return  edgeTo[t];
    }

//    private boolean check(EdgeWeightedDigraph G, int s) {
//
//        // check that edge weights are nonnegative
//        for (DirectedEdge e : G.edges()) {
//            if (e.weight() < 0) {
//                System.err.println("negative edge weight detected");
//                return false;
//            }
//        }
//
//        // check that distTo[v] and edgeTo[v] are consistent
//        if (distTo[s] != 0.0 || edgeTo[s] != null) {
//            System.err.println("distTo[s] and edgeTo[s] inconsistent");
//            return false;
//        }
//        for (int v = 0; v < G.V(); v++) {
//            if (v == s) continue;
//            if (edgeTo[v] == null && distTo[v] != Double.POSITIVE_INFINITY) {
//                System.err.println("distTo[] and edgeTo[] inconsistent");
//                return false;
//            }
//        }
//
//        // check that all edges e = v->w satisfy distTo[w] <= distTo[v] + e.weight()
//        for (int v = 0; v < G.V(); v++) {
//            for (DirectedEdge e : G.adj(v)) {
//                int w = e.to();
//                if (distTo[v] + e.weight() < distTo[w]) {
//                    System.err.println("edge " + e + " not relaxed");
//                    return false;
//                }
//            }
//        }
//
//        // check that all edges e = v->w on SPT satisfy distTo[w] == distTo[v] + e.weight()
//        for (int w = 0; w < G.V(); w++) {
//            if (edgeTo[w] == null) continue;
//            DirectedEdge e = edgeTo[w];
//            int v = e.from();
//            if (w != e.to()) return false;
//            if (distTo[v] + e.weight() != distTo[w]) {
//                System.err.println("edge " + e + " on shortest path not tight");
//                return false;
//            }
//        }
//        return true;
//    }
//
}
