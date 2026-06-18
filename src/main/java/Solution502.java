import java.util.*;

public class Solution502 {
  public int findMaximizedCapital(int k, int w, int[] profits, int[] capital) {
    int n = profits.length;
    int[][] projects = new int[n][2];
    for (int i = 0; i < n; i++) {
      projects[i][0] = capital[i];
      projects[i][1] = profits[i];
    }
    Arrays.sort(projects, Comparator.comparingInt(a -> a[0]));
    PriorityQueue<Integer> maxProfit = new PriorityQueue<>(Collections.reverseOrder());
    int idx = 0;
    for (int i = 0; i < k; i++) {
      while (idx < n && projects[idx][0] <= w) {
        maxProfit.offer(projects[idx][1]);
        idx++;
      }
      if (maxProfit.isEmpty()) break;
      w += maxProfit.poll();
    }
    return w;
  }
}
