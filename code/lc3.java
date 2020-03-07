package code;
/*
* 3. Longest Substring Without Repeating Characters
* 题意：找出字符串中没有重复字母的最大长度
* 难度：Medium
* 分类：Hash Table, Two Pointers, String
* 算法：两个指针，记录没有重复字母的子串的首和尾
*      lc76
*
*
*
*/

/**
 * 这道题主要用到思路是：滑动窗口

     什么是滑动窗口？

     其实就是一个队列,比如例题中的 abcabcbb，进入这个队列（窗口）为 abc 满足题目要求，当再进入 a，队列变成了 abca，这时候不满足要求。所以，我们要移动这个队列！

     如何移动？

     我们只要把队列的左边的元素移出就行了，直到满足题目要求！

     一直维持这样的队列，找出队列出现最长的长度时候，求出解！

     时间复杂度：
     O(n)

     作者：powcai
     链接：https://leetcode-cn.com/problems/longest-substring-without-repeating-characters/solution/hua-dong-chuang-kou-by-powcai/
     来源：力扣（LeetCode）
     著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
 */

import java.util.HashMap;
import java.util.Map;

public class lc3 {
    public static void main(String[] args) {
        String s = "abba";
        System.out.println(lengthOfLongestSubstring(s));
    }

    public static int lengthOfLongestSubstring(String s) {
        int max = 0;
        if ("".equals(s) || s == null) {
            return max;
        }
        Map<Character, Integer> map = new HashMap<>(16);
        int left = 0;
        for (int i = 0; i < s.length(); i++) {
            if (map.containsKey(s.charAt(i))) {
                // aba
                left = Math.max(left, map.get(s.charAt(i)) + 1);
            }
            map.put(s.charAt(i), i);
            // i - left + 1 is length of window
            max = Math.max(max, i - left + 1);
        }
        return max;
    }

}
