import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution1705 {
  public int eatenApples(int[] apples, int[] days) {
    PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    int n = apples.length;
    int eaten = 0;
    int day = 0;

    while (day < n || !pq.isEmpty()) {
      if (day < n && apples[day] > 0) {
        pq.offer(new int[] {day + days[day] - 1, apples[day]});
      }
      while (!pq.isEmpty() && pq.peek()[0] < day) {
        pq.poll();
      }
      if (!pq.isEmpty()) {
        int[] top = pq.peek();
        top[1]--;
        eaten++;
        if (top[1] == 0) pq.poll();
      }
      day++;
    }
    return eaten;
  }
}
