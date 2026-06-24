public class Solution1753 {
  public int maximumScore(int a, int b, int c) {
    int sum = a + b + c;
    int max = Math.max(a, Math.max(b, c));
    int rest = sum - max;
    if (max >= rest) return rest;
    return sum / 2;
  }
}
