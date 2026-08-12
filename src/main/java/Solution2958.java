import java.util.HashMap;
import java.util.Map;

public class Solution2958 {

  public int maxSubarrayLength(int[] nums, int k) {
    Map<Integer, Integer> frequency = new HashMap<>();

    int maxLength = 0;
    int left = 0;

    for (int right = 0; right < nums.length; right++) {
      frequency.merge(nums[right], 1, Integer::sum);

      while (frequency.get(nums[right]) > k) {
        frequency.merge(nums[left++], -1, Integer::sum);
      }

      maxLength = Math.max(maxLength, right - left + 1);
    }

    return maxLength;
  }

}
