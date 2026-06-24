import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution2402 {
  public int mostBooked(int n, int[][] meetings) {
    Arrays.sort(meetings, Comparator.comparingInt(a -> a[0]));
    int[] count = new int[n];
    PriorityQueue<Integer> free = new PriorityQueue<>();
    for (int i = 0; i < n; i++) free.offer(i);
    PriorityQueue<long[]> busy = new PriorityQueue<>((a, b) -> {
      if (a[0] != b[0]) return Long.compare(a[0], b[0]);
      return Long.compare(a[1], b[1]);
    });

    for (int[] m : meetings) {
      long start = m[0];
      long end = m[1];
      while (!busy.isEmpty() && busy.peek()[0] <= start) {
        free.offer((int) busy.poll()[1]);
      }
      if (!free.isEmpty()) {
        int room = free.poll();
        count[room]++;
        busy.offer(new long[] {end, room});
      } else {
        long[] earliest = busy.poll();
        long delay = earliest[0] - start;
        int room = (int) earliest[1];
        count[room]++;
        busy.offer(new long[] {end + delay, room});
      }
    }

    int bestRoom = 0;
    for (int i = 1; i < n; i++) {
      if (count[i] > count[bestRoom]) bestRoom = i;
    }
    return bestRoom;
  }
}
