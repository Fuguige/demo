package com.arr.sparse;

import java.util.Scanner;

/**
 * @ClassName CircleArrayQueueDemo
 * @Description TODO
 * @Author binD :-)
 * @Date 2020/11/29
 */
public class CircleArrayQueueDemo {
    public static void main(String[] args) {
        CircleArray queue = new CircleArray(4);
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

class CircleArray {
    private int maxSize;        // 数组的最大容量
    private int front;          // 队列头 默认为0
    private int rear;           // 队列尾 默认为0
    private int[] arr;          // 模拟队列

    public CircleArray(int maxSize) {
        this.maxSize = maxSize;
        arr = new int[maxSize];
    }

    public boolean isFull() {
        return (rear + 1) % maxSize == front;
    }

    public boolean isEmpty() {
        return rear == front;
    }

    public void addQueue(int n) {
        // 判断是否已满
        if (isFull()) {
            System.out.println("duilie yiman");
            return;
        }
        // 直接将数据加入即可,因为rear已经指向最后一位数组的后一位了
        arr[rear] = n;
        // 将rear后移,并考虑取模
        rear = (rear + 1) % maxSize;
    }

    public int getQueue() {
        // 判断队列是否为空
        if (isEmpty()) {
            throw new RuntimeException("队列为空,不能取数据.");
        }
        // front指向队列的第一个元素
        // 1. 先保存front的值,保存到临时变量
        // 2. front后移,考虑取模
        // 3. 将临时变量返回
        int v = arr[front];
        front = (front + 1) % maxSize;
        return v;
    }

    public void showQueue() {
        if (isEmpty()) {
            System.out.println("队列为空,么有取数据.");
            return;
        }
        // 从front开始遍历,那么需要遍历多少个元素
        for (int i = front; i < front+size(); i++) {
            System.out.printf("arr[%d]=%d\n", (i % maxSize), arr[(i % maxSize)]);
        }
    }

    /**
     * 取得当前队列的有效数据个数
     **/
    public int size() {
        // rear = 2
        // front = 1
        // maxSize = 3
        return (rear + maxSize - front) % maxSize;
    }

    // 显示队列的头数据
    public int headQueue() {
        if (isEmpty()) {
            throw new RuntimeException("队列为空,没取数据.");
        }
        return arr[front];
    }
}
