import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution1942 {

  public int smallestChair(int[][] times, int targetFriend) {
    int n = times.length;
    Integer[] order = new Integer[n];
    for (int i = 0; i < n; i++) {
      order[i] = i;
    }
    Arrays.sort(order, Comparator.comparingInt(a -> times[a][0]));

    PriorityQueue<Integer> freeChairs = new PriorityQueue<>();
    for (int i = 0; i < n; i++) {
      freeChairs.offer(i);
    }
    PriorityQueue<int[]> occupied = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

    for (int idx : order) {
      int arrival = times[idx][0];
      int leave = times[idx][1];
      while (!occupied.isEmpty() && occupied.peek()[0] <= arrival) {
        freeChairs.offer(occupied.poll()[1]);
      }
      int chair = freeChairs.poll();
      if (idx == targetFriend) {
        return chair;
      }
      occupied.offer(new int[] {leave, chair});
    }
    return -1;
  }
}
