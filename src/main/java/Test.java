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
//        if(1 == n) return 1;
//        if(2 == n) return 2;
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

    //优化
    //思路

}
