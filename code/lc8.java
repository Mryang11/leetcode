package code;

/**
 * 8. String to Integer (atoi)
 * 题意：字符串转数字，按题目要求转换。(题出的不好，没什么意义)
 * 难度：Medium
 * 分类：Math, String
 * 注意：如何判断溢出
 *
 * 正则
 * ^\s*([+-]?\d+)
 */
public class lc8 {
    public static void main(String[] args) {
        System.out.println(myAtoi("     -42"));
    }

    public static int myAtoi(String str) {
        int rev = 0;
        char[] charList = str.toCharArray();
        int length = str.length();
        int zf = 1;
        int i = 0;
        int pop = 0;
        for (; i < length; i++) {
            if (charList[i] == ' ') {
                continue;
            } else {
                if (charList[i] == '-') {
                    i++;
                    zf = -1;
                    break;
                }
                if (charList[i] == '+') {
                    i++;
                    break;
                }
                if (charList[i] < '0' || charList[i] > '9') {
                    return 0;
                } else {
                    break;
                }
            }
        }
        if (i == length) {
            return 0;
        }
        for (; i < length; i++) {
            if (charList[i] < '0' || charList[i] > '9') {
                return rev;
            }
            pop = (charList[i] - 48) * zf;
            // 2的31次方-1的个位数 = 7
            if (rev > Integer.MAX_VALUE / 10 || (rev == Integer.MAX_VALUE / 10 && pop > 7)) {
                return Integer.MAX_VALUE;
            }
            // -2的31次方的个位数 = 8
            if (rev < Integer.MIN_VALUE / 10 || (rev == Integer.MIN_VALUE / 10 && pop < -8)) {
                return Integer.MIN_VALUE;
            }
            rev = rev * 10 + pop;
        }
        return rev;
    }
}