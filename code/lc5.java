package code;

/*
 * 5. Longest Palindromic Substring
 * 题意：找出给定字符串中最长的回文串
 * 难度：Medium
 * 分类：String, Dynamic Programming
 * Tips：从后往前遍历，保证后续dp时，子情况已计算出
 *       还有一种思路是从中间往两边扩展，中间有两种情况，一种一个字符，一种两个字符
 *       lc5, lc9, lc125, lc131, lc234, lc647
 */
public class lc5 {
    public static void main(String[] args) {
        String s = "cbbd";
        System.out.println(longestPalindrome(s));
    }

    public static String longestPalindrome(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        String res = "";
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if ((j - i) < 3 || dp[i + 1][j - 1]) {
                        dp[i][j] = true;
                    }
                    if (dp[i][j] && j - i + 1 > res.length()) {
                        // 起始索引，终止索引(不包括，所以+1)
                        res = s.substring(i, j + 1);
                    }
                }
            }
        }
        return res;
    }

    /**
     * 事实上，只需使用恒定的空间，我们就可以在
     * O(n*n) 的时间内解决这个问题。
     *
     * 我们观察到回文中心的两侧互为镜像。因此，回文可以从它的中心展开，并且只有
     * 2n−1 个这样的中心。
     *
     * 你可能会问，为什么会是2n−1 个，而不是n个中心？原因在于所含字母数为偶数的回文的中心可以处于两字母之间（例如
     * “abba”
     * “abba” 的中心在两个
     * ‘b’
     * ‘b’ 之间）。
     *
     * 作者：LeetCode
     * 链接：https://leetcode-cn.com/problems/longest-palindromic-substring/solution/zui-chang-hui-wen-zi-chuan-by-leetcode/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     * @param s
     * @return
     */
    public String longestPalindrome1(String s) {
        if (s == null || s.length() < 1) {
            return "";
        }
        int start = 0, end = 0;
        for (int i = 0; i < s.length(); i++) {
            int len1 = expandAroundCenter(s, i, i);
            int len2 = expandAroundCenter(s, i, i + 1);
            int len = Math.max(len1, len2);
            if (len > end - start) {
                start = i - (len - 1) / 2;
                end = i + len / 2;
            }
        }
        return s.substring(start, end + 1);
    }

    private int expandAroundCenter(String s, int left, int right) {
        int L = left, R = right;
        while (L >= 0 && R < s.length() && s.charAt(L) == s.charAt(R)) {
            L--;
            R++;
        }
        return R - L - 1;
    }
}
