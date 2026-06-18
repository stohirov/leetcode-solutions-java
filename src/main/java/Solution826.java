import java.util.*;

public class Solution826 {
  public int maxProfitAssignment(int[] difficulty, int[] profit, int[] worker) {
    int n = difficulty.length;
    int[][] jobs = new int[n][2];
    for (int i = 0; i < n; i++) {
      jobs[i][0] = difficulty[i];
      jobs[i][1] = profit[i];
    }
    Arrays.sort(jobs, (a, b) -> a[0] - b[0]);
    Arrays.sort(worker);
    int total = 0;
    int best = 0;
    int j = 0;
    for (int w : worker) {
      while (j < n && jobs[j][0] <= w) {
        best = Math.max(best, jobs[j][1]);
        j++;
      }
      total += best;
    }
    return total;
  }
}
