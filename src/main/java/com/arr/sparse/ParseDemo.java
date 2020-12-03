package com.arr.sparse;

/**
 * @ClassName ParseDemo 稀疏数组
 * @Description TODO
 * @Author fubinD on 2020/11/27
 */
public class ParseDemo {

    public static void main(String[] args) {
        // step1: 创建一个原始的二维数组 11 * 11
        // 0: 没有棋子 1:表示黑子 2:蓝子
        int chessArr1[][] = new int[11][12];
        chessArr1[1][2] = 1;
        chessArr1[2][3] = 2;
        System.out.println("原始数组:");
        printf(chessArr1);




        // step2: 二维数组转稀疏数组
        // 1. 遍历二维数组,得到非零数据个数
        int sum = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    sum++;
                }
            }
        }
        // 2. 创建稀疏数组 (sum+1 是因为稀疏数组的第一行记录的是 行数,列数,非零数据总个数)
        int sparseArr[][] = new int[sum + 1][3];
        sparseArr[0][0] = 11;
        sparseArr[0][1] = 11;
        sparseArr[0][2] = sum;

        // 遍历二维数组,将非0的值存放放到sparseArr中
        int count = 0;
        for (int i = 0; i < 11; i++) {
            for (int j = 0; j < 11; j++) {
                if (chessArr1[i][j] != 0) {
                    count++;
                    sparseArr[count][0] = i;
                    sparseArr[count][1] = j;
                    sparseArr[count][2] = chessArr1[i][j];
                }
            }
        }
        // 输出稀疏数组:
        System.out.println();
        System.out.println("稀疏数组为:");
        for (int i = 0; i < sparseArr.length; i++) {
            System.out.printf("%d\t%d\t%d\n",sparseArr[i][0],sparseArr[i][1],sparseArr[i][2]);
        }
        System.out.println();

        // step3: 还原稀疏数组
        // 1. 先读取稀疏数组的第一行,根据第一行的数据,创建原始的二维数组
        int chessArr2[][] = new int[sparseArr[0][0]][sparseArr[0][1]];
        // 2. 读取稀疏数组后几行数组(从第二行开始),并赋值原始的二维数组即可
        for (int i =1; i < sparseArr.length; i++) {
            chessArr2[sparseArr[i][0]][sparseArr[i][1]] = sparseArr[i][2];
        }
        printf(chessArr2);

    }

    public static void printf(int chessArr[][]) {
        // 行数=数组名.length,
        // 列数=数组名[0].length;
        int row = chessArr.length;
        int column = 0;
        for (int[] rows : chessArr) {
            column = rows.length;
            for (int data : rows) {
                System.out.printf("%d\t",data);
            }
            System.out.println();
        }
        System.out.println("row:"+row);
        System.out.println("column:"+column);
    }


}


