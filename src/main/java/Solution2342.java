public class Solution2342 {
  public int maximumSum(int[] nums) {
    int[] best = new int[100];
    java.util.Arrays.fill(best, -1);
    int ans = -1;
    for (int num : nums) {
      int s = digitSum(num);
      if (best[s] != -1) {
        ans = Math.max(ans, best[s] + num);
      }
      best[s] = Math.max(best[s], num);
    }
    return ans;
  }

  private int digitSum(int num) {
    int sum = 0;
    while (num > 0) {
      sum += num % 10;
      num /= 10;
    }
    return sum;
  }
}
