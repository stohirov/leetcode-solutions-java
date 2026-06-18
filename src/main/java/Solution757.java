import java.util.*;

public class Solution757 {

  public int intersectionSizeTwo(int[][] intervals) {
    Arrays.sort(intervals, (a, b) -> a[1] != b[1] ? a[1] - b[1] : b[0] - a[0]);
    int count = 0;
    int p1 = -1;
    int p2 = -1;
    for (int[] interval : intervals) {
      int start = interval[0];
      int end = interval[1];
      boolean has1 = start <= p1;
      boolean has2 = start <= p2;
      if (has1 && has2) {
        continue;
      } else if (has2) {
        count++;
        p1 = p2;
        p2 = end;
      } else {
        count += 2;
        p1 = end - 1;
        p2 = end;
      }
    }
    return count;
  }
}
