package com.cacheserverdeploy.deploy.dataStructure;

/**
 * Created by tuzhenyu on 17-3-9.
 * @author tuzhenyu
 */

public class KruskalMST {
    private static final double FLOATING_POINT_EPSILON = 1E-12;

    private int weight;
    private Queue<Edge> mst = new Queue<>();

    public KruskalMST(Graph G) {

        MinPQ<Edge> pq = new MinPQ<Edge>();
        for (Edge e : G.getEdges()) {
            pq.insert(e);
        }

        UF uf = new UF(G.getVertexNum());
        while (!pq.isEmpty() && mst.size() < G.getVertexNum() - 1) {
            Edge e = pq.delMin();
            int v = e.getStartPoint();
            int w = e.otherPoint(v);
            if (!uf.connected(v, w)) {
                uf.union(v, w);
                mst.enqueue(e);
                weight += e.getCost();
            }
        }

//        assert check(G);
    }

    public Iterable<Edge> edges() {
        return mst;
    }

    public Integer weight() {
        return weight;
    }

    private boolean check(Graph G) {

        Integer total = 0;
        for (Edge e : edges()) {
            total += e.getCost();
        }
        if (Math.abs(total - weight()) > FLOATING_POINT_EPSILON) {
            System.err.printf("Weight of edges does not equal weight(): %d vs. %d\n", total, weight());
            return false;
        }

        UF uf = new UF(G.getVertexNum());
        for (Edge e : edges()) {
            int v = e.getStartPoint(), w = e.otherPoint(v);
            if (uf.connected(v, w)) {
                System.err.println("Not a forest");
                return false;
            }
            uf.union(v, w);
        }

        for (Edge e : G.getEdges()) {
            int v = e.getStartPoint(), w = e.otherPoint(v);
            if (!uf.connected(v, w)) {
                System.err.println("Not a spanning forest");
                return false;
            }
        }

        for (Edge e : edges()) {

            uf = new UF(G.getVertexNum());
            for (Edge f : mst) {
                int x = f.getStartPoint(), y = f.otherPoint(x);
                if (f != e) uf.union(x, y);
            }

            for (Edge f : G.getEdges()) {
                int x = f.getStartPoint(), y = f.otherPoint(x);
                if (!uf.connected(x, y)) {
                    if (f.getCost() < e.getCost()) {
                        System.err.println("Edge " + f + " violates cut optimality conditions");
                        return false;
                    }
                }
            }

        }

        return true;
    }

}
