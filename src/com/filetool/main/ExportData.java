package com.filetool.main;

import com.cacheserverdeploy.deploy.dataStructure.Edge;
import com.filetool.util.FileUtil;

import java.util.Scanner;

/**
 * Created by tuzhenyu on 17-3-9.
 * @author tuzhenyu
 */
public class ExportData {
    public static void main(String[] args) {
        String graphFilePath = "/home/tuzhenyu/tmp/case_example/case0.txt";
        String[] graphContent = FileUtil.read(graphFilePath, null);


        Scanner scanner = new Scanner(graphContent[0]);
        scanner.useDelimiter(" ");
        int vertexNum = scanner.nextInt();
        int edgeNum = scanner.nextInt();
        int [][] num = new int [vertexNum][vertexNum];

        for(int i=0;i<edgeNum;i++){
            Scanner tempScanner = new Scanner(graphContent[i+4]);
            tempScanner.useDelimiter(" ");
            int startPoint = tempScanner.nextInt();
            int endPoint = tempScanner.nextInt();
            num[startPoint][endPoint] = 1;
            num[endPoint][startPoint] = 1;
        }

        for(int x = 0; x<num.length; x++){
            for(int y = 0; y<num[x].length; y++){
                System.out.print(num[x][y] + " ");
            }
            System.out.println("");
        }
    }
}
