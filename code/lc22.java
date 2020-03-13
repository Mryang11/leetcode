package code;
import java.util.*;

/**
 * 22. Generate Parentheses
 * 题意：正确括号组合
 * 难度：Medium
 * 分类：String, Backtracking
 * 思路：回溯法的典型题目，按选优条件向前搜索，达到目标后就退回一步或返回
 * 注意：递归法别忘了两块的拼接，例如n=4时，可以由2，2拼起来作为答案
 *      lc32, lc22, lc301
 */
public class lc22 {
    public static void main(String[] args) {
        System.out.println(generateParenthesis(4));
        System.out.println(generateParenthesis2(4));
        System.out.println(new lc22().generateParenthesis3(4));
        System.out.println(generateParenthesis4(4));
        System.out.println(generateParenthesis5(4));
    }


    /**
     *
     * 作者：liweiwei1419
     * 链接：https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * 思想: 回溯+剪枝
     * left:    左括号剩余个数
     * right:   右括号剩余个数
     * n = 2
     *                                           '' left=2, right=2
     *                                        ===                  ===
     *                                      ===                      ===
     *                     ( left=1, right=2                             ) left=2, right=1  右括号不够用了剪掉
     *                   ===                ===
     *                 ===                    ===
     * (( left=0, right=2                             () left=1, right=1
     *                   ===                         ===              ===
     *                     ===                     ===                  ===
     *             left=0, right=1 (()    ()( left=0, right=1              ()) left=1, right=0 右括号不够用了剪掉
     *                          ===                ===
     *                            ===               ===
     *                left=0, right=0 (())             ()() left=0, right=0
     *
     *
     * 画图以后，可以分析出的结论：
     *
     * 当前左右括号都有大于0个可以使用的时候，才产生分支；
     *
     * 产生左分支的时候，只看当前是否还有左括号可以使用；
     *
     * 产生右分支的时候，还受到左分支的限制，右边剩余可以使用的括号数量一定得在严格大于左边剩余的数量的时候，才可以产生分支；
     *
     * 在左边和右边剩余的括号数都等于0的时候结算。
     *
     * @param n
     * @return
     */

    /**
     * 做减法
     */
    public static List<String> generateParenthesis(int n) {
        List<String> res = new ArrayList<>();
        // 特判
        if (n == 0) {
            return res;
        }

        // 执行深度优先遍历，搜索可能的结果
        dfs("", n, n, res);
        return res;
    }

    /**
     * @param curStr 当前递归得到的结果
     * @param left   左括号还有几个可以使用
     * @param right  右括号还有几个可以使用
     * @param res    结果集
     */
    private static void dfs(String curStr, int left, int right, List<String> res) {
        // 因为每一次尝试，都使用新的字符串变量，所以无需回溯
        // 在递归终止的时候，直接把它添加到结果集即可，注意与「力扣」第 46 题、第 39 题区分
        if (left == 0 && right == 0) {
            res.add(curStr);
            return;
        }

        // 剪枝（如图，左括号可以使用的个数严格大于右括号可以使用的个数，才剪枝，注意这个细节）
        if (left > right) {
            return;
        }

        if (left > 0) {
            dfs(curStr + "(", left - 1, right, res);
        }

        if (right > 0) {
            dfs(curStr + ")", left, right - 1, res);
        }
    }

    /**
     * 做加法
     * @param n
     * @return
     */
    public static List<String> generateParenthesis2(int n) {
        //回溯法
        ArrayList<String> res = new ArrayList<>();
        backtracking(res, "", 0, 0, n);
        return res;
    }

    public static void backtracking(List<String> list, String str, int left, int right, int max) {
        if (right == max) {
            list.add(str);
        }
        if (left < max) {
            backtracking(list, str + "(", left + 1, right, max);
        }
        if (right < left) {
            backtracking(list, str + ")", left, right + 1, max);
        }
    }

    class Node {
        /**
         * 当前得到的字符串
         */
        private String res;
        /**
         * 剩余左括号数量
         */
        private int left;
        /**
         * 剩余右括号数量
         */
        private int right;

        Node(String str, int left, int right) {
            this.res = str;
            this.left = left;
            this.right = right;
        }
    }

    /**
     * 广度优先遍历
     * @param n
     */
    public List<String> generateParenthesis3(int n) {
        List<String> res = new ArrayList<>();
        if (n == 0) {
            return res;
        }
        Queue<Node> queue = new LinkedList<>();
        queue.offer(new Node("", n, n));

        while (!queue.isEmpty()) {

            Node curNode = queue.poll();
            if (curNode.left == 0 && curNode.right == 0) {
                res.add(curNode.res);
            }
            if (curNode.left > 0) {
                queue.offer(new Node(curNode.res + "(", curNode.left - 1, curNode.right));
            }
            // 右括号足够用
            if (curNode.right > 0 && curNode.left < curNode.right) {
                queue.offer(new Node(curNode.res + ")", curNode.left, curNode.right - 1));
            }
        }
        return res;
    }

    /**
     *
     * 动态规划
     *
     * 第 1 步：定义状态 dp[i]：使用 i 对括号能够生成的组合。
     *
     * 注意：每一个状态都是列表的形式。
     *
     * 第 2 步：状态转移方程：
     *
     * i 对括号的一个组合，在 i - 1 对括号的基础上得到，这是思考 “状态转移方程” 的基础；
     * i 对括号的一个组合，一定以左括号 "(" 开始，不一定以 ")" 结尾。为此，我们可以枚举新的右括号 ")" 可能所处的位置，得到所有的组合；
     * 枚举的方式就是枚举左括号 "(" 和右括号 ")" 中间可能的合法的括号对数，而剩下的合法的括号对数在与第一个左括号 "(" 配对的右括号 ")" 的后面，这就用到了以前的状态。
     * 状态转移方程是：
     *
     * dp[i] = "(" + dp[可能的括号对数] + ")" + dp[剩下的括号对数]
     * “可能的括号对数” 与 “剩下的括号对数” 之和得为 i - 1（感谢 @xuyik 朋友纠正了我的错误），故 “可能的括号对数” j 可以从 0 开始，最多不能超过 i， 即 i - 1；
     * “剩下的括号对数” + j = i - 1，故 “剩下的括号对数” = i - j - 1。
     * 整理得：
     * dp[i] = "(" + dp[j] + ")" + dp[i- j - 1] , j = 0, 1, ..., i - 1
     * 第 3 步： 思考初始状态和输出：
     *
     * 初始状态：因为我们需要 0 对括号这种状态，因此状态数组 dp 从 0 开始，0 个括号当然就是 [""]。
     * 输出：dp[n] 。
     * 这个方法暂且就叫它动态规划，这么用也是很神奇的，它有下面两个特点：
     *
     * 1、自底向上：从小规模问题开始，逐渐得到大规模问题的解集；
     *
     * 2、无后效性：后面的结果的得到，不会影响到前面的结果。
     *
     * 作者：liweiwei1419
     * 链接：https://leetcode-cn.com/problems/generate-parentheses/solution/hui-su-suan-fa-by-liweiwei1419/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     */
    public static List<String> generateParenthesis4(int n) {
        if (n == 0) {
            return new ArrayList<>();
        }
        // 这里 dp 数组我们把它变成列表的样子，方便调用而已
        List<List<String>> dp = new ArrayList<>(n);

        List<String> dp0 = new ArrayList<>();
        dp0.add("");
        dp.add(dp0);

        for (int i = 1; i <= n; i++) {
            List<String> cur = new ArrayList<>();
            for (int j = 0; j < i; j++) {
                List<String> str1 = dp.get(j);
                List<String> str2 = dp.get(i - 1 - j);
                for (String s1 : str1) {
                    for (String s2 : str2) {
                        // 枚举右括号的位置
                        cur.add("(" + s1 + ")" + s2);
                    }
                }
            }
            dp.add(cur);
        }
        return dp.get(n);
    }

    /**
     * 递归法
     * @param n
     * @return
     */
    public static List<String> generateParenthesis5(int n) {
        //递归法
        Set<String> res = new HashSet<>();
        if (n == 1) {
            res.add("()");
            return new ArrayList<>(res);
        }
        List<String> temp = generateParenthesis5(n - 1);
        // 一个括号，和n-1个括号的组合
        for (int i = 0; i < temp.size(); i++) {
            res.add("(" + temp.get(i) + ")");
            res.add("()" + temp.get(i));
            res.add(temp.get(i) + "()");
        }
        //2块拼一起
        for (int j = 2; j <= n / 2; j++) {
            List<String> temp1 = generateParenthesis5(j);
            List<String> temp2 = generateParenthesis5(n - j);
            for (int i = 0; i < temp1.size(); i++) {
                for (int k = 0; k < temp2.size(); k++) {
                    res.add(temp1.get(i) + temp2.get(k));
                    res.add(temp2.get(k) + temp1.get(i));
                }
            }
        }
        return new ArrayList<>(res);
    }


}