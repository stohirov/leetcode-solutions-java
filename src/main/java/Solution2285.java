import java.util.Arrays;

public class Solution2285 {
  public long maximumImportance(int n, int[][] roads) {
    long[] degree = new long[n];
    for (int[] road : roads) {
      degree[road[0]]++;
      degree[road[1]]++;
    }
    Arrays.sort(degree);
    long total = 0;
    for (int i = 0; i < n; i++) {
      total += degree[i] * (long) (i + 1);
    }
    return total;
  }
}
