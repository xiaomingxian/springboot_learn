import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class T1_132Mode_md {

//    给你一个整数数组 nums ，数组中共有 n 个整数。132 模式的子序列 由三个整数 nums[i]、nums[j] 和 nums[k] 组成，并同时满足：i < j < k 和 nums[i] < nums[k] < nums[j] 。
//
//    如果 nums 中存在 132 模式的子序列 ，返回 true ；否则，返回 false 。
//
//    进阶：很容易想到时间复杂度为 O(n^2) 的解决方案，你可以设计一个时间复杂度为 O(n logn) 或 O(n) 的解决方案吗？
//
//    示例 1：
//
//    输入：nums = [1,2,3,4]
//    输出：false
//    解释：序列中不存在 132 模式的子序列。
//    示例 2：
//
//    输入：nums = [3,1,4,2]
//    输出：true
//    解释：序列中有 1 个 132 模式的子序列： [1, 4, 2] 。
//    示例 3：
//
//    输入：nums = [-1,3,2,0]
//    输出：true
//    解释：序列中有 3 个 132 模式的的子序列：[-1, 3, 2]、[-1, 3, 0] 和 [-1, 2, 0] 。
//             
//
//    提示：
//
//    n == nums.length
//1 <= n <= 104
//            -109 <= nums[i] <= 109
public static void main(String[] args) {



}
//    方法一：枚举 33
//    思路与算法
//
//    枚举 33 是容易想到并且也是最容易实现的。由于 33 是模式中的最大值，并且其出现在 11 和 22 的中间，因此我们只需要从左到右枚举 33 的下标 jj，那么：
//
//    由于 11 是模式中的最小值，因此我们在枚举 jj 的同时，维护数组 aa 中左侧元素 a[0..j-1]a[0..j−1] 的最小值，即为 11 对应的元素 a[i]a[i]。需要注意的是，只有 a[i] < a[j]a[i]<a[j] 时，a[i]a[i] 才能作为 11 对应的元素；
//
//    由于 22 是模式中的次小值，因此我们可以使用一个有序集合（例如平衡树）维护数组 aa 中右侧元素 a[j+1..n-1]a[j+1..n−1] 中的所有值。当我们确定了 a[i]a[i] 和 a[j]a[j] 之后，只需要在有序集合中查询严格比 a[i]a[i] 大的那个最小的元素，即为 a[k]a[k]。需要注意的是，只有 a[k] < a[j]a[k]<a[j] 时，a[k]a[k] 才能作为 33 对应的元素。
//    复杂度分析
//
//    时间复杂度：O(n \log n)O(nlogn)。在初始化时，我们需要 O(n \log n)O(nlogn) 的时间将数组元素 a[2..n-1]a[2..n−1] 加入有序集合中。在枚举 jj 时，维护左侧元素最小值的时间复杂度为 O(1)O(1)，将 a[j+1]a[j+1] 从有序集合中删除的时间复杂度为 O(\log n)O(logn)，总共需要枚举的次数为 O(n)O(n)，因此总时间复杂度为 O(n \log n)O(nlogn)。
//
//    空间复杂度：O(n)O(n)，即为有序集合存储右侧所有元素需要使用的空间。

    class Solution1 {
        public boolean find132pattern(int[] nums) {
            int n = nums.length;
            if (n < 3) {
                return false;
            }

            // 左侧最小值
            int leftMin = nums[0];
            // 右侧所有元素
            TreeMap<Integer, Integer> rightAll = new TreeMap<Integer, Integer>();

            for (int k = 2; k < n; ++k) {
                rightAll.put(nums[k], rightAll.getOrDefault(nums[k], 0) + 1);
            }

            for (int j = 1; j < n - 1; ++j) {
                if (leftMin < nums[j]) {
                    Integer next = rightAll.ceilingKey(leftMin + 1);
                    if (next != null && next < nums[j]) {
                        return true;
                    }
                }
                leftMin = Math.min(leftMin, nums[j]);
                rightAll.put(nums[j + 1], rightAll.get(nums[j + 1]) - 1);
                if (rightAll.get(nums[j + 1]) == 0) {
                    rightAll.remove(nums[j + 1]);
                }
            }

            return false;
        }
    }




    class Solution {
        public boolean find132pattern(int[] nums) {
            int n = nums.length;
            List<Integer> candidateI = new ArrayList<Integer>();
            candidateI.add(nums[0]);
            List<Integer> candidateJ = new ArrayList<Integer>();
            candidateJ.add(nums[0]);

            for (int k = 1; k < n; ++k) {
                int idxI = binarySearchFirst(candidateI, nums[k]);
                int idxJ = binarySearchLast(candidateJ, nums[k]);
                if (idxI >= 0 && idxJ >= 0) {
                    if (idxI <= idxJ) {
                        return true;
                    }
                }

                if (nums[k] < candidateI.get(candidateI.size() - 1)) {
                    candidateI.add(nums[k]);
                    candidateJ.add(nums[k]);
                } else if (nums[k] > candidateJ.get(candidateJ.size() - 1)) {
                    int lastI = candidateI.get(candidateI.size() - 1);
                    while (!candidateJ.isEmpty() && nums[k] > candidateJ.get(candidateJ.size() - 1)) {
                        candidateI.remove(candidateI.size() - 1);
                        candidateJ.remove(candidateJ.size() - 1);
                    }
                    candidateI.add(lastI);
                    candidateJ.add(nums[k]);
                }
            }

            return false;
        }

        public int binarySearchFirst(List<Integer> candidate, int target) {
            int low = 0, high = candidate.size() - 1;
            if (candidate.get(high) >= target) {
                return -1;
            }
            while (low < high) {
                int mid = (high - low) / 2 + low;
                int num = candidate.get(mid);
                if (num >= target) {
                    low = mid + 1;
                } else {
                    high = mid;
                }
            }
            return low;
        }

        public int binarySearchLast(List<Integer> candidate, int target) {
            int low = 0, high = candidate.size() - 1;
            if (candidate.get(low) <= target) {
                return -1;
            }
            while (low < high) {
                int mid = (high - low + 1) / 2 + low;
                int num = candidate.get(mid);
                if (num <= target) {
                    high = mid - 1;
                } else {
                    low = mid;
                }
            }
            return low;
        }
    }


}
