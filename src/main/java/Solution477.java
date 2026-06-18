public class Solution477 {

  public int totalHammingDistance(int[] nums) {
    int n = nums.length;
    int total = 0;
    for (int bit = 0; bit < 32; bit++) {
      int ones = 0;
      for (int num : nums) {
        ones += (num >> bit) & 1;
      }
      total += ones * (n - ones);
    }
    return total;
  }
}
