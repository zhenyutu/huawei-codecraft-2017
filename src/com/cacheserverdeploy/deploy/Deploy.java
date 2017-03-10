package com.cacheserverdeploy.deploy;

import com.cacheserverdeploy.deploy.dataStructure.Graph;
import com.cacheserverdeploy.deploy.dataStructure.KruskalMST;
import com.cacheserverdeploy.deploy.method.DijkstraAllSP;
import com.cacheserverdeploy.deploy.method.DijkstraSP;
import com.cacheserverdeploy.deploy.method.SourcePiont;

import java.util.Arrays;

public class Deploy
{
    /**
     * 你需要完成的入口
     * <功能详细描述>
     * @param graphContent 用例信息文件
     * @return [参数说明] 输出结果信息
     * @see [类、类#方法、类#成员]
     */
    public static String[] deployServer(String[] graphContent)
    {
        Graph graph = new Graph(graphContent);
        Long start = System.currentTimeMillis();
        DijkstraAllSP dijkstraAllSP = new DijkstraAllSP(graph);
        SourcePiont.getSourcePoint(graph);
        System.out.println(graph.toString());
        SourcePiont.getSourcePoint(graph);
        System.out.println(Arrays.toString(graph.getSource()));
//        System.out.println("++++++++++++++++++++++++++++++++++++");
//        KruskalMST kruskalMST = new KruskalMST(graph);
//        System.out.println(kruskalMST.pathTo(6,39));
//        System.out.println("++++++++++++++++++++++++++++++++++++");
//        DijkstraSP dijkstraSP = new DijkstraSP(graph,6);
//        System.out.println(dijkstraSP.pathTo(39).toString());

        return new String[]{"17","\r\n","0 8 0 20"};
    }

}
