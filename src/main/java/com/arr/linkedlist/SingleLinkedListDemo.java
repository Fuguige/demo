package com.arr.linkedlist;

import java.util.Stack;

import static com.arr.linkedlist.SingleLinkedList.*;

/**
 * @ClassName SingleLinkedListDemo
 * @Description TODO
 * @Author binD :-)
 * @Date 2020/12/3
 */
public class SingleLinkedListDemo {

    public static void main(String[] args) {
        HeroNode hero1 = new HeroNode(1, "a", "A");
        HeroNode hero3 = new HeroNode(3, "c", "C");
        HeroNode hero2 = new HeroNode(2, "b", "B");
        HeroNode hero4 = new HeroNode(4, "d", "D");

        SingleLinkedList linkedList = new SingleLinkedList();
        linkedList.addByOrder(hero1);
        linkedList.addByOrder(hero2);
//        linkedList.addByOrder(hero3);
//        linkedList.addByOrder(hero4);

//        HeroNode newHeroNode = new HeroNode(2,"bb","BB");
//        linkedList.update(newHeroNode);

//        linkedList.del(1);

//        linkedList.list();

//        System.out.println("有效个数:"+getLength(linkedList. getHead()));

//        System.out.println("倒数第N个:"+findLastIndexNode(linkedList. getHead(),3));

        // 测试翻转
//        System.out.println("Original data:");
//        linkedList.list();
//        System.out.println("Reverse order:");
//        reverseList(linkedList.getHead());
//        linkedList.list();

        // 逆序打印 (stack)
        System.out.println("reversePrint:");
        reversePrint(linkedList.getHead());

    }

}

class SingleLinkedList {
    //1. 需要初始化一个头结点,为null的节点
    private HeroNode head = new HeroNode(0, "", "");

    public HeroNode getHead() {
        return head;
    }

    //2. 添加节点到单向链表
    //   找到当前链表的最后节点
    //   将最后节点的next指向新的节点
    public void add(HeroNode node) {
        HeroNode temp = head;
        while (true) {
            if (temp.next == null) {
                break;
            }
            temp = temp.next;
        }
        temp.next = node;
    }

    public void  addByOrder(HeroNode currentNode) {

        HeroNode temp = head;
        boolean flag = false; // 标记编号是否存在
        while (true) {
            if (temp.next ==null) {
                //说明已经在链表的最后
                break;
            }
            if (temp.next.no > currentNode.no) {
                // 当前节点的下一个节点与传入的节点做比较,如果大于,则表示currentNode在temp与temp.next之间插入
                break;
            } else if (temp.next.no ==  currentNode.no) {
                //  当前节点的下一个节点与传入的节点做比较,如果等于,则表示编号已经在链表中存在
                flag = true;
                break;
            }
            // 以上三个条件都不满足,则移动至下一个节点继续寻找编号
            temp = temp.next;
        }
        // 判断flag ,  true : 编号存在
        if (flag) {
            System.out.println("标号存在.");
        } else {
            currentNode.next = temp.next;
            temp.next = currentNode;
        }
    }

    public void update(HeroNode newNode) {
        // 判断链表是否为null
        if (head.next == null) {
            System.out.println("Linked list is null.");
            return;
        }
        HeroNode temp = head.next;
        boolean flag = false;
        while (true) {
            if (temp==null) {
                break;
            }
            if (temp.no == newNode.no) {
                // 找到
                flag = true;
                break;
            }
            temp = temp.next;
        }
        if (flag) {
            temp.name = newNode.name;
            temp.nickName = newNode.nickName;
        } else {
            System.out.println("没有该编号:"+newNode.no);
        }
    }

    /**
     * 思路:
     * 1. 先找到需要删除节点的前一个节点. 例如: temp
     * 2. 指定temp.next = temp.next.next
     **/
    public void del(int no) {
        HeroNode temp = head;
        boolean flag = false; // 标记是否需要删除
        while (true) {
            if (temp.next == null) {
                break;
            }
            if (temp.next.no == no) {
                flag = true;
                break;
            }
            temp = temp.next; // temp后移
        }
        if (flag) {
            temp.next = temp.next.next;
        } else {
            System.out.println("节点不存在.");
        }
    }

    // 遍历链表
    public void list() {
        // 判断链表是否为null
        if (head.next == null) {
            System.out.println("Linked list is null.");
            return;
        }
        HeroNode temp = head.next;
        while (true) {
            if (temp == null) {
                break;
            }
            System.out.println(temp);
            temp = temp.next;
        }
    }

    /**
     * 练习题:
     * 求单链表中有效的节点格式
     **/
    public static int getLength(HeroNode node) {
        if (node.next == null) {
            return 0;
        }
        int length = 0;
        HeroNode temp = node.next;
        while (temp != null) {
            length++;
            temp = temp.next;
        }
        return length;
    }

    /**
     * 练习题:
     * 查找单链表中的倒数第k个结点
     * 思路:
     * 1. 编写一个方法,接收head节点,同事接收一个index
     * 2. index表示倒数
     * 3. 先把链表遍历一次,得到链表的总长度
     * 4. 之后,总长度-index,就可以得到node的位置
     **/
    public static HeroNode findLastIndexNode(HeroNode head, int index) {
        if (head == null) {
            return null;
        }
        // 第一次遍历,需要得到链表的总长度
        int size = getLength(head);
        // 第二次遍历,需要得到size-index的位置
        // 校验index
        if (index <= 0 || index > size) {
            return null;
        }
        // 第一个有效的node ,始终是head节点的next节点.
        HeroNode currentNode = head.next;
        for (int i = 0; i < (size - index); i++) {
            currentNode = currentNode.next;
        }
        return currentNode;
    }

    /**
     * 单链表的反转
     **/
    public static void reverseList(HeroNode head) {
        if (head.next == null || head.next.next == null) {
            return;
        }
        HeroNode cur = head.next;
        HeroNode next = null;  // 指向当前节点[cur]的下一个节点
        HeroNode reverseHead = new HeroNode(0,"","");
        // 遍历原来的链表,每取出一个节点,就放在reverseHead的最前端.
        while (cur != null) {
            next = cur.next; // 暂存当前节点的下一个节点.
            cur.next = reverseHead.next; // 将cur的下一个节点指向新的链表的最前端
            reverseHead.next = cur; // 将cur链接到新的reverHead链表上.
            cur = next;
        }
        // 将head.next 指向reverseHead.next
        head.next = reverseHead.next;
    }

    /**
     * 从尾到头打印单链表(方式1: 反向遍历; 方式2: Stack栈实现)
     **/
    public static void reversePrint(HeroNode head) {
        if (head.next == null) {
            return;
        }
        Stack<HeroNode> stack = new Stack<>();
        HeroNode cur = head.next;
        // 压栈
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        // 出栈操作
        while (stack.size() > 0) {
            System.out.println(stack.pop());
        }
    }

}

class HeroNode {
    public int no;
    public String name;
    public String nickName;
    public HeroNode next;

    public HeroNode(int no, String name, String nickName) {
        this.no = no;
        this.name = name;
        this.nickName = nickName;
    }

    @Override
    public String toString() {
        return "HeroNode{" +
                "no=" + no +
                ", name='" + name + '\'' +
                ", nickName='" + nickName + '\'' +
                '}';
    }
}