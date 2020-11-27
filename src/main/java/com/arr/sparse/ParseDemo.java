package com.arr.sparse;

/**
 * @ClassName ParseDemo
 * @Description TODO
 * @Author fubinD on 2020/11/27
 */
public class ParseDemo {

    public static void main(String[] args) {
        // 创建一个原始的二维数组 11 * 11
        // 0: 没有棋子 1:表示黑子 2:蓝子
        int chessArr1[][] = new int[11][11];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;

        for (int[] rows : chessArr1) {
            for (int data : rows) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }


    }

}


