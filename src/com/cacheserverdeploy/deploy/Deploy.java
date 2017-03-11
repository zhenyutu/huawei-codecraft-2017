package com.cacheserverdeploy.deploy;

import com.cacheserverdeploy.deploy.dataStructure.Graph;
import com.cacheserverdeploy.deploy.dataStructure.KruskalMST;
import com.cacheserverdeploy.deploy.method.DijkstraAllSP;
import com.cacheserverdeploy.deploy.method.SeekRoad;
import com.cacheserverdeploy.deploy.method.SourcePiont;
import org.apache.log4j.Logger;

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
    static Logger logger = Logger.getLogger (Deploy.class);
    public static String[] deployServer(String[] graphContent)
    {

        Graph graph = new Graph(graphContent);
        logger.info("初始饥饿数组："+Arrays.toString(graph.getHunger()));
        SourcePiont.getSourcePoint(graph);
        logger.info("汇入点："+Arrays.toString(graph.getSource()));
        DijkstraAllSP dijkstraAllSP = new DijkstraAllSP(graph);
//        System.out.println(Arrays.toString(graph.getSource()));
//        System.out.println("++++++++++++++++++++++++++++++++++++");
//        KruskalMST kruskalMST = new KruskalMST(graph);
//        System.out.println(kruskalMST.pathTo(38,1));
//        System.out.println("++++++++++++++++++++++++++++++++++++");
//        System.out.println(dijkstraAllSP.path(38,1)+" - "+dijkstraAllSP.cost(38,1));
//        System.out.println(dijkstraAllSP.edgePath(38,1));
//        System.out.println(dijkstraAllSP.firstEdgePath(38,1));

        SeekRoad seekRoad = new SeekRoad(graph,dijkstraAllSP);
        System.out.println(Arrays.toString(graph.getHunger()));

        return new String[]{"17","\r\n","0 8 0 20"};
    }

}
