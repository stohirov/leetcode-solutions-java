public class Solution2344 {
  public int minOperations(int[] nums, int[] numsDivide) {
    int g = 0;
    for (int x : numsDivide) g = gcd(g, x);
    int best = Integer.MAX_VALUE;
    for (int x : nums) {
      if (g % x == 0) best = Math.min(best, x);
    }
    if (best == Integer.MAX_VALUE) return -1;
    int count = 0;
    for (int x : nums) {
      if (x < best) count++;
    }
    return count;
  }

  private int gcd(int a, int b) {
    while (b != 0) {
      int t = a % b;
      a = b;
      b = t;
    }
    return a;
  }
}
