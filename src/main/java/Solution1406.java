public class Solution1406 {

  public String stoneGameIII(int[] stoneValue) {
    int n = stoneValue.length;
    int a = 0, b = 0, c = 0;

    for (int i = n - 1; i >= 0; i--) {
      int best = stoneValue[i] - a;
      if (i + 1 < n) {
        best = Math.max(best, stoneValue[i] + stoneValue[i + 1] - b);
      }
      if (i + 2 < n) {
        best = Math.max(best, stoneValue[i] + stoneValue[i + 1] + stoneValue[i + 2] - c);
      }
      c = b;
      b = a;
      a = best;
    }

    if (a > 0) {
      return "Alice";
    }
    if (a < 0) {
      return "Bob";
    }
    return "Tie";
  }

}
