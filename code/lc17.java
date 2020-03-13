package code;

import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

/**
 * 17. Letter Combinations of a Phone Number
 * 题意：手机键盘字母输入
 * 难度：Medium
 * 思路：深度优先DFS
 * 分类：String, Backtracking
 */
public class lc17 {
    public static void main(String[] args) {
        System.out.println(letterCombinations("23").toString());
    }

    public static List<String> letterCombinations(String digits) {
        List<String> list = new ArrayList<>();
        if (digits == null || "".equals(digits)) {
            return list;
        }
        String nowStr = "";
        addCharacter(digits, 0, nowStr, list);
        return list;
    }

    private static void addCharacter(String digits, int key, String nowStr, List<String> list) {
        if (digits.length() == key) {
            list.add(nowStr);
            return;
        }
        char[] chars = getChars(digits.charAt(key));
        if (chars != null) {
            for (int i = 0; i < chars.length; i++) {
                addCharacter(digits, key + 1, nowStr + chars[i], list);
            }
        }
    }

    private static char[] getChars(char charAt) {
        if ('2' == charAt) {
            return new char[]{'a', 'b', 'c'};
        } else if ('3' == charAt) {
            return new char[]{'d', 'e', 'f'};
        } else if ('4' == charAt) {
            return new char[]{'g', 'h', 'i'};
        } else if ('5' == charAt) {
            return new char[]{'j', 'k', 'l'};
        } else if ('6' == charAt) {
            return new char[]{'m', 'n', 'o'};
        } else if ('7' == charAt) {
            return new char[]{'p', 'q', 'r', 's'};
        } else if ('8' == charAt) {
            return new char[]{'t', 'u', 'v'};
        } else if ('9' == charAt) {
            return new char[]{'w', 'x', 'y', 'z'};
        }
        return null;
    }
}
