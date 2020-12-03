package com.arr.sparse;

import java.util.Scanner;

/**
 * @ClassName ArrayQueueDemo
 * @Description 数组实现队列
 * @Author binD :-)
 * @Date 2020/11/28
 */
public class ArrayQueueDemo {

    public static void main(String[] args) {
        ArrayQueue queue = new ArrayQueue(3);
        char key = ' ';
        Scanner scanner = new Scanner(System.in);
        boolean loop = true;
        while (loop) {
            System.out.println("s(show):显示队列.");
            System.out.println("e(exit):退出队列.");
            System.out.println("a(add):添加数据.");
            System.out.println("g(get):取出数据.");
            System.out.println("h(shead):查看头部数据.");
            key = scanner.next().charAt(0);
            switch (key) {
                case 's':
                    queue.showQueue();
                    break;
                case 'a':
                    System.out.println("输入一个数:");
                    int v = scanner.nextInt();
                    queue.addQueue(v);
                    break;
                case 'g':
                    try {
                        int res = queue.getQueue();
                        System.out.println("取出的值:" + res);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 'h':
                    try {
                        int res = queue.headQueue();
                        System.out.println("取出的头部值:" + res);
                    } catch (Exception e) {
                        System.out.println(e);
                    }
                    break;
                case 'e':
                    scanner.close();
                    loop = false;
                    break;
                default:
                    break;
            }
        }
        System.out.println("exit");
    }

}

class ArrayQueue {

    private int maxSize;        // 数组的最大容量
    private int front;          // 队列头
    private int rear;           // 队列尾
    private int[] arr;          // 模拟队列

    /**
     * 创建队列构造器
     **/
    public ArrayQueue(int maxSize) {
        this.maxSize = maxSize;
        this.arr = new int[maxSize];
        this.rear = -1;          // 指向队列头部,分析出front是指向队列头的前一个位置.
        this.front = -1;          // 指向队列尾部,指向队列尾部的数据(即最后一个数据)
    }

    public boolean isFull() {
        return rear == maxSize - 1;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        // 判断是否已满
        if (isFull()) {
            System.out.println("队列已满");
            return;
        }
        rear++; // rear 后移
        arr[rear] = n;
//        arr[++rear] = n; // rear后移
    }

    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取数据.");
        }
        front++; // front 后移
        return arr[front];
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空,么有取数据.");
            return;
        }
        for (int i = 0; i < arr.length; i++) {
            System.out.printf("arr[%d]=%d\n", i, arr[i]);
        }
    }

    // 显示队列的头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,没取数据.");
        }
        return arr[front + 1];
    }

}