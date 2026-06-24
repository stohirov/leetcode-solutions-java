import java.util.PriorityQueue;

public class Solution1962 {

  public int minStoneSum(int[] piles, int k) {
    PriorityQueue<Integer> heap = new PriorityQueue<>((a, b) -> b - a);
    long total = 0;
    for (int p : piles) {
      heap.offer(p);
      total += p;
    }
    for (int i = 0; i < k; i++) {
      int top = heap.poll();
      int removed = top / 2;
      total -= removed;
      heap.offer(top - removed);
    }
    return (int) total;
  }
}
