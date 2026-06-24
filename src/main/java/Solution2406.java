import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution2406 {
  public int minGroups(int[][] intervals) {
    Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
    PriorityQueue<Integer> ends = new PriorityQueue<>();
    for (int[] interval : intervals) {
      if (!ends.isEmpty() && ends.peek() < interval[0]) {
        ends.poll();
      }
      ends.offer(interval[1]);
    }
    return ends.size();
  }
}
