package code;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 15. 3Sum
 * 题意：找出数组中所有和为0的三元组合
 * 难度：Medium
 * 分类：Array, Two Pointers
 * 注意：如何避免 List 重复元素
 * Tips：lc15, lc16, lc923
 */
public class lc15 {
    public static void main(String[] args) {
        int[] nums = {-1, 0, 1, 2, -1, -4};
        System.out.println(threeSum(nums).toString());
    }

    /**
     * 特判，对于数组长度n，如果数组为null或者数组长度小于3，返回[]。
     * 对数组进行排序。
     * 遍历排序后数组：
     * 若nums[i]>0：因为已经排序好，所以后面不可能有三个数加和等于0，直接返回结果。
     * 对于重复元素：跳过，避免出现重复解
     * 令左指针L=i+1，右指针R=n−1，当L<R 时，执行循环：
     * 当nums[i]+nums[L]+nums[R]==0，执行循环，判断左界和右界是否和下一位置重复，去除重复解。并同时将
     * L,R 移到下一位置，寻找新的解
     * 若和大于0，说明nums[R] 太大，R 左移
     * 若和小于0，说明nums[L] 太小，L 右移
     * <p>
     * 作者：zhu_shi_fu
     * 链接：https://leetcode-cn.com/problems/3sum/solution/pai-xu-shuang-zhi-zhen-zhu-xing-jie-shi-python3-by/
     * 来源：力扣（LeetCode）
     * 著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。
     *
     * @param nums
     * @return
     */
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return null;
        }
        Arrays.sort(nums);
        for (int i = 0; i < nums.length; i++) {
            // 防止重复添加相同内容List
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            }
            int left = i + 1;
            int right = nums.length - 1;
            while (left < right) {
                if (nums[i] + nums[left] + nums[right] == 0) {
                    result.add(Arrays.asList(nums[i], nums[left], nums[right]));
                    // 防止重复添加相同内容List
                    while (left < right && nums[left] == nums[left + 1]) {
                        left++;
                    }
                    // 防止重复添加相同内容List
                    while (left < right && nums[right] == nums[right - 1]) {
                        right--;
                    }
                    left++;
                    right--;
                } else if (nums[i] + nums[left] + nums[right] < 0) {
                    left++;
                } else {
                    right--;
                }
            }
        }
        return result;
    }

}
