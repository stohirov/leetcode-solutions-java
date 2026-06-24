import java.util.PriorityQueue;

public class Solution2233 {
  public int maximumProduct(int[] nums, int k) {
    final int MOD = 1_000_000_007;
    PriorityQueue<Integer> pq = new PriorityQueue<>();
    for (int num : nums) pq.offer(num);
    while (k-- > 0) {
      int smallest = pq.poll();
      pq.offer(smallest + 1);
    }
    long result = 1;
    while (!pq.isEmpty()) {
      result = (result * pq.poll()) % MOD;
    }
    return (int) result;
  }
}
