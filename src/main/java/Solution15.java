import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution15 {
  public List<List<Integer>> threeSum(int[] nums) {
    Arrays.sort(nums);

    int n = nums.length;
    int sum;
    Set<List<Integer>> result = new HashSet<>();

    for (int i = 0; i < n - 2; i++) {
      int left = i + 1;
      int right = n - 1;

      if (i > 0 && nums[i] == nums[i - 1]) continue;

      while (left < right) {
        sum = nums[i] + nums[left] + nums[right];

        if (sum == 0) {
          result.add(Arrays.asList(nums[i], nums[left], nums[right]));
          left++;
          right--;
        } else if (sum < 0) {
          left++;
        } else {
          right--;
        }

      }
    }

    return result.stream().toList();
  }
}
