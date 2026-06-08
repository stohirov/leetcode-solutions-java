import java.util.Arrays;

public class Solution2144 {
  public int minimumCost(int[] cost) {
    Arrays.sort(cost);
    int total = 0;

    for (int i = cost.length - 1, count = 0; i >= 0; i--, count++) {
      if (count % 3 != 2) {
        total += cost[i];
      }
    }

    return total;
  }
}
