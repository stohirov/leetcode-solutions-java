import java.util.ArrayList;
import java.util.List;

public class Solution1057 {

  public int[] assignBikes(int[][] workers, int[][] bikes) {
    int n = workers.length, m = bikes.length;
    // buckets indexed by distance (max distance < 2000)
    List<int[]>[] buckets = new List[2001];
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < m; j++) {
        int d = Math.abs(workers[i][0] - bikes[j][0])
            + Math.abs(workers[i][1] - bikes[j][1]);
        if (buckets[d] == null) {
          buckets[d] = new ArrayList<>();
        }
        buckets[d].add(new int[] {i, j});
      }
    }

    int[] result = new int[n];
    java.util.Arrays.fill(result, -1);
    boolean[] bikeUsed = new boolean[m];
    int assigned = 0;

    for (int d = 0; d <= 2000 && assigned < n; d++) {
      if (buckets[d] == null) {
        continue;
      }
      for (int[] pair : buckets[d]) {
        int w = pair[0], b = pair[1];
        if (result[w] == -1 && !bikeUsed[b]) {
          result[w] = b;
          bikeUsed[b] = true;
          assigned++;
        }
      }
    }
    return result;
  }
}
