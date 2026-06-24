import java.util.PriorityQueue;

public class Solution1354 {

  public boolean isPossible(int[] target) {
    long sum = 0;
    PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
    for (int t : target) {
      sum += t;
      pq.offer(t);
    }
    while (true) {
      int max = pq.poll();
      if (max == 1) return true;
      long rest = sum - max;
      if (rest == 0) return false;
      if (rest == 1) return true;
      int prev = (int) (max % rest);
      if (prev == 0 || prev == max) return false;
      sum = rest + prev;
      pq.offer(prev);
    }
  }
}
