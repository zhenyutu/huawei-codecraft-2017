package com.cacheserverdeploy.deploy.method;

import com.cacheserverdeploy.deploy.dataStructure.Edge;
import com.cacheserverdeploy.deploy.dataStructure.Graph;
import com.cacheserverdeploy.deploy.dataStructure.Stack;

/**
 * Created by tuzhenyu on 17-3-10.
 * @author tuzhenyu
 */
public class DepthFirstPaths {
    private boolean[] marked;
    private int[] edgeTo;
    private final int s;

    public DepthFirstPaths(Graph G, int s) {
        this.s = s;
        edgeTo = new int[G.getVertexNum()];
        marked = new boolean[G.getVertexNum()];
        dfs(G, s);
    }

    private void dfs(Graph G, int v) {
        marked[v] = true;
        for (Edge w : G.getAdj(v)) {
            if (!marked[w.otherPoint(v)]) {
                edgeTo[w.otherPoint(v)] = v;
                dfs(G, w.otherPoint(v));
            }
        }
    }

    public boolean hasPathTo(int v) {
        return marked[v];
    }

    public Iterable<Integer> pathTo(int v) {
        if (!hasPathTo(v)) return null;
        Stack<Integer> path = new Stack<Integer>();
        for (int x = v; x != s; x = edgeTo[x])
            path.push(x);
        path.push(s);
        return path;
    }

}
