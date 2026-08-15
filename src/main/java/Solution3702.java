public class Solution3702 {

  public int longestSubsequence(int[] nums) {
    int totalXor = 0;

    for (int num : nums) {
      totalXor ^= num;
    }

    if (totalXor != 0) {
      return nums.length;
    }

    for (int num : nums) {
      if (num != 0) {
        return nums.length - 1;
      }
    }

    return 0;
  }

}
