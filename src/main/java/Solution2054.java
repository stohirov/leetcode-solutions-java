import java.util.Arrays;
import java.util.Comparator;

public class Solution2054 {

  public int maxTwoEvents(int[][] events) {
    int n = events.length;
    Arrays.sort(events, Comparator.comparingInt(a -> a[0]));

    int[] suffixMax = new int[n + 1];
    for (int i = n - 1; i >= 0; i--) {
      suffixMax[i] = Math.max(suffixMax[i + 1], events[i][2]);
    }

    int[] starts = new int[n];
    for (int i = 0; i < n; i++) {
      starts[i] = events[i][0];
    }

    int best = 0;
    for (int[] e : events) {
      int endTime = e[1];
      int idx = upperBound(starts, endTime);
      int combined = e[2];
      if (idx < n) {
        combined += suffixMax[idx];
      }
      best = Math.max(best, combined);
    }
    return best;
  }

  private int upperBound(int[] arr, int target) {
    int lo = 0, hi = arr.length;
    while (lo < hi) {
      int mid = (lo + hi) >>> 1;
      if (arr[mid] <= target) {
        lo = mid + 1;
      } else {
        hi = mid;
      }
    }
    return lo;
  }
}
