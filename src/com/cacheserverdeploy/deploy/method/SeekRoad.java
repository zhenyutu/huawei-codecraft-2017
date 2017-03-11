package com.cacheserverdeploy.deploy.method;

import com.cacheserverdeploy.deploy.dataStructure.*;
import org.apache.log4j.Logger;

import java.util.Arrays;

/**
 * Created by tuzhenyu on 17-3-10.
 * @author tuzhenyu
 */
public class SeekRoad {
    private Graph graph;
    private DijkstraAllSP dijkstraAllSP;
    private Queue<Integer> pq;
    private Boolean[] sourcePiont;
    private int vertexNum;

    static Logger logger = Logger.getLogger (SeekRoad.class);


    public SeekRoad(Graph graph,DijkstraAllSP dijkstraAllSP){
        this.graph = graph;
        this.dijkstraAllSP = dijkstraAllSP;
        this.pq = new Queue<>();
        this.sourcePiont = graph.getSource();
        this.vertexNum = graph.getVertexNum();

        for (int v=0;v<graph.getConsumer().length;v++){
            int startPoint = graph.getConsumer()[v][0];
            int targetPoint = getSourcePoint(startPoint);
            logger.info("目标汇入点："+startPoint+":"+targetPoint);
            pq.enqueue(startPoint);
        }
        for (Integer i : pq){
            logger.info("执行消费节点"+i);
        }
        while (!pq.isEmpty()){
            for (Integer i : pq){
                logger.info("执行消费节点"+i+":"+graph.getHunger(i));
            }
            int vertex = pq.dequeue();
            logger.error("当前执行消费点："+vertex);
            int target = getSourcePoint(vertex);
            logger.error("当前消费点的汇入点："+target);
            logger.error("饿数组："+Arrays.toString(graph.getHunger()));
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
        logger.info("进入寻路算法");
        int hunger = graph.getHunger(s);
        logger.info("当前执行消费点饥饿值："+hunger);
        logger.info("当前消费点路径:"+dijkstraAllSP.path(s,t));
        if (hunger>0){
            for (Edge edge : dijkstraAllSP.edgePath(s,t)){
                int edgeStartPoint = edge.getStartPoint();
                int edgeEndPoint = edge.getEndPoint();
                int edgeStartPointHunger = graph.getHunger(edgeStartPoint);
                int edgeLastCapacity = edge.getLastCapacity();
                logger.info("开始计算边("+edgeStartPoint+"-"+edgeEndPoint+")");
                logger.info("当前边的起始点的饥饿值："+edgeStartPointHunger);
                logger.info("当前边的剩余容量："+edgeLastCapacity);

                if (edgeLastCapacity >=edgeStartPointHunger){
                    logger.info("容量大于饥饿值");
                    edge.setLastCapacity(edge.getLastCapacity()-edgeStartPointHunger);
                    graph.setHunger(edgeEndPoint,(edgeStartPointHunger+graph.getHunger(edgeEndPoint)));
                    graph.setHunger(edgeStartPoint,0);
                    logger.info("饿数组："+Arrays.toString(graph.getHunger()));
                }else if(edgeLastCapacity >0 && (edgeLastCapacity < edgeStartPointHunger)){
                    logger.info("容量小于饥饿值");
                    logger.info(edgeStartPoint+"***"+(edgeStartPointHunger-edge.getLastCapacity()));
                    graph.setHunger(edgeStartPoint,(edgeStartPointHunger-edge.getLastCapacity()));
                    graph.setHunger(edgeEndPoint,(edge.getLastCapacity()+graph.getHunger(edgeEndPoint)));
                    logger.info("饿数组："+Arrays.toString(graph.getHunger()));
                    edge.setLastCapacity(0);
                }else {
                    logger.info("容量为０");
                    IndexMinPQ<Integer> mpq = minAdj(edgeStartPoint,t);
                    for (Integer i : mpq){
                        logger.info("当前边容量为0,准备跳转节点"+i+"-"+mpq.keyOf(i));
                    }
                    while (!mpq.isEmpty()){
                        int next = mpq.delMin();
                        logger.info("跳转节点为："+next);
                        Edge e = graph.getEdge(edgeStartPoint,next);
                        int eLastCapacity = e.getLastCapacity();
                        int sp = e.getStartPoint();
                        int spHunger = graph.getHunger()[sp];
                        logger.info("跳转边为："+e.getStartPoint()+"-"+e.getEndPoint());
                        logger.info("跳转边剩余容量："+e.getLastCapacity());
                        if (e.getLastCapacity() != 0){
                            logger.info("跳转边剩余容量不为0");
                            if (eLastCapacity>=spHunger){
                                logger.info("跳转边剩余容量大于饥饿值");
                                e.setLastCapacity(e.getLastCapacity()-e.getStartPoint());
                                logger.info("当前边的剩余容量："+e.getLastCapacity());
                                graph.setHunger(next,(graph.getHunger(e.getStartPoint())+graph.getHunger(next)));
                                graph.setHunger(e.getStartPoint(),0);
                                logger.info("饿数组："+Arrays.toString(graph.getHunger()));
                                logger.info("插入消费节点数组");
                                if (!queueContain(next)){
                                    pq.enqueue(next);
                                }
                                for (Integer i : pq){
                                    logger.info("执行消费节点"+i);
                                }
                                break;
                            }else if(e.getLastCapacity()>0 && e.getLastCapacity()< graph.getHunger(e.getStartPoint())){
                                logger.info("跳转边剩余容量小于饥饿值");
                                graph.setHunger(edgeStartPoint,graph.getHunger(e.getStartPoint())-e.getLastCapacity());
                                logger.info("当前边的剩余容量："+e.getLastCapacity());
                                graph.setHunger(next,(e.getLastCapacity()+graph.getHunger(next)));
                                e.setLastCapacity(0);
                                logger.info("当前边的剩余容量："+e.getLastCapacity());
                                logger.info("饿数组："+Arrays.toString(graph.getHunger()));

                                logger.info("插入消费节点数组");
                                if (!queueContain(next)){
                                    pq.enqueue(next);
                                }
                                for (Integer i : pq){
                                    logger.info("执行消费节点"+i);
                                }
                            }
                        }
                    }
                    break;
                }
            }
            for (Integer vertex : dijkstraAllSP.path(s,t)){
                if (graph.getHunger(vertex)!= 0){
                    logger.info("插入消费节点数组"+vertex);
                    if (!queueContain(vertex)){
                        pq.enqueue(vertex);
                    }
                }
            }
        }
    }

    private IndexMinPQ<Integer> minAdj(int s,int t){
        IndexMinPQ<Integer> mpq = new IndexMinPQ<>(graph.getVertexNum());
        for (Edge edge:graph.getAdj(s)){
            int start = edge.otherPoint(s);
            mpq.insert(start,dijkstraAllSP.cost(start,t)+edge.getCost());
        }
        return mpq;
    }

    public boolean queueContain(int vertex){
        boolean result = false;
        for (int v : pq){
            if (v==vertex){
                result = true;
                break;
            }
        }
        return result;
    }
}
