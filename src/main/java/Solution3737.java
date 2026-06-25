public class Solution3737 {
  public int countMajoritySubarrays(int[] nums, int target) {
    int n = nums.length;
    int count = 0;
    for (int i = 0; i < n; i++) {
      int c = 0;
      for (int j = i; j < n; j++) {
        if (nums[j] == target) {
          c++;
        }
        if (2 * c > (j - i + 1)) {
          count++;
        }
      }
    }
    return count;
  }
}
