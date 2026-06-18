public class Solution280 {
  public void wiggleSort(int[] nums) {
    for (int i = 1; i < nums.length; i++) {
      boolean shouldSwap = (i % 2 == 1) ? nums[i] < nums[i - 1] : nums[i] > nums[i - 1];
      if (shouldSwap) {
        int tmp = nums[i];
        nums[i] = nums[i - 1];
        nums[i - 1] = tmp;
      }
    }
  }
}
