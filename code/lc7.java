package code;

/*
 * 7. Reverse Integer
 * 题意：反转一个整数
 * 难度：Easy
 * 分类：Math
 * 注意：如何判断溢出
 */
public class lc7 {
    public static void main(String[] args) {
        System.out.println(reverse(-987));
    }

    public static int reverse(int x) {
        int ans = 0;
        while (x != 0) {
            int pop = x % 10;
            // 2的31次方-1的个位数 = 7
            if (ans > Integer.MAX_VALUE / 10 || (ans == Integer.MAX_VALUE / 10 && pop > 7)) {
                return 0;
            }
            // -2的31次方的个位数 = 8
            if (ans < Integer.MIN_VALUE / 10 || (ans == Integer.MIN_VALUE / 10 && pop < -8)) {
                return 0;
            }
            ans = ans * 10 + pop;
            x /= 10;
        }
        return ans;
    }
}
