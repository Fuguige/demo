import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName Test
 * @Description TODO
 * @Author fubinD
 * @Date 2020/4/2
 */
public class Test {

    public static void main(String[] args) {

        System.out.println(sum(5, 0));

    }

    public static int sum(int n, int sum) {
        if (n<2) return 1 + sum;
        sum += n;
        return sum(n - 1, sum);
    }

    public int multiply(int A, int B) {
        if (A < 2) {
            return B;
        }
        int sum = multiply(A - 1, B);
        return sum + B;
    }

    /**
     * -寻找递推关系: f(n) = f(n-1) + f(n-2)
     * -寻找递归基本情况,跳出时返回基本情况的结果: f(1) = 1,f(2) = 2
     * -修改递归函数的参数,递归调用 -> 套入递归关系,当前n台阶跳法为:
     *  count = f(n-1) + f(n-2)
     * -使用递归函数的返回值进行计算并返回最终结果 -> 递归返回跳法数count
     **/
    public int numWays1(int n) {
        if (n <=2) return n;
        int count = numWays1(n - 1) + numWays1(n - 2);
        return count;
    }

    private final Map<Integer, Integer> map = new HashMap<>();

    /**
     * -优化思路
     * -减少执行行数: 2 个 if 可有优化 为 if (n <= 2) return n
     * -递归函数重复计算问题,使用临时变量保存
     **/
    public int numWays(int n) {
        if (n <= 2) return n;
        final Integer integer = map.get(n);
        if (integer != null) return integer;
        int count = 0;
        int count1 = numWays(n - 1);
        int count2 = numWays(n - 1);
        count = count1 + count2;
        map.put(n, count);
        return count;
    }



    /**
     * 由上到下的范式套入实现:
     *
     * -寻找递推关系:
     *  f(n) = f(n-1) + f(n-2) 相当于从n-1的计算过程,先从n找到1,然后在从1累加到n的过程
     *  修改为从1-n的过程, f(i+1) = f(i) + f(i-1) ,i+1==n时计算结束,
     *  累加的过程变量需要我们提取为中间变量参数
     *
     * -创建新函数,将[由下到上-范式]中的最终计算结果依赖的中间变量提取为函数的参数
     *  将f(i),f(n-1)的变量保存,初始调用我们使用f(2) = f(1) + f(0) = 1+1作为初始状态
     *
     * -寻找基本情况:跳出时返回基本情况的结果与中间变量的计算结果 ->
     *  if (i >= n) return a + b;
     *
     * -根据函数参数与中间变量重新计算出新的中间变量
     *  f(i) = f(i-1) + f(i-2) = a + b
     *  f(i+1) = f(i) + f(i-1) = (a+b) + b
     *
     *  - 修改参数 -> i + 1 递进一步
     *  - 递归调用并返回（该处的返回由基本情况条件触发）
     **/
    public int numWayTail(int n) {
        if(n<2) return n;
        return numWayTailHelper(n, 2, 1, 1);
    }

    private int numWayTailHelper(int n, int i, int a, int b) {
        if (i>=n) return a+b;
        return numWayTailHelper(n, i + 1, a + b, a);
    }

    public int numWayFor(int n) {
        if (n < 2) return n;

        int i = 2; int a = 1; int b = 1; // 与尾递归 numWaysTailHelp 一致
        int count = a + b; // 保存次数，将尾递归的返回值提取为变量

        while (i <= n) { // 1-n 的过程
            // 因为 f(i) = f(i-1) + f(i-2) = a + b
            // 下次迭代时 f(i+1) = f(i) + f(i-1) = (a+b) + b
            count = a + b;
            b = a;
            a = count;
            i++;
        }
        return count;
    }


}
