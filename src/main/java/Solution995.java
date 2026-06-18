public class Solution995 {

  public int minKBitFlips(int[] nums, int k) {
    int n = nums.length;
    int[] diff = new int[n + 1];
    int flips = 0;
    int current = 0;

    for (int i = 0; i < n; i++) {
      current += diff[i];
      if (((nums[i] + current) & 1) == 0) {
        if (i + k > n) {
          return -1;
        }
        flips++;
        current++;
        diff[i + k]--;
      }
    }

    return flips;
  }
}
