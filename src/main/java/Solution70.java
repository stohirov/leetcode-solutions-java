public class Solution70 {
  public int climbStairs(int n) {
    int prev = 1;
    int current = 1;

    for (int i = 2; i <= n; i++) {
      int next = prev + current;
      prev = current;
      current = next;
    }

    return current;
  }
}
