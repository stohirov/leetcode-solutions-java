import java.util.ArrayDeque;
import java.util.Deque;

public class Solution1687 {
  public int boxDelivering(int[][] boxes, int portsCount, int maxBoxes, int maxWeight) {
    int n = boxes.length;
    int[] dp = new int[n + 1];
    long[] prefixWeight = new long[n + 1];
    int[] changes = new int[n + 1];
    for (int i = 0; i < n; i++) {
      prefixWeight[i + 1] = prefixWeight[i] + boxes[i][1];
      changes[i + 1] = changes[i];
      if (i > 0 && boxes[i][0] != boxes[i - 1][0]) changes[i + 1]++;
    }

    Deque<Integer> deque = new ArrayDeque<>();
    deque.offer(0);
    for (int i = 1; i <= n; i++) {
      while (!deque.isEmpty()) {
        int j = deque.peekFirst();
        if (i - j > maxBoxes || prefixWeight[i] - prefixWeight[j] > maxWeight) {
          deque.pollFirst();
        } else {
          break;
        }
      }
      int best = deque.peekFirst();
      dp[i] = dp[best] + 2 + changes[i] - changes[best + 1];

      if (i < n) {
        int gi = dp[i] - changes[i + 1];
        while (!deque.isEmpty()) {
          int back = deque.peekLast();
          int gBack = dp[back] - changes[back + 1];
          if (gBack >= gi) deque.pollLast();
          else break;
        }
        deque.offerLast(i);
      }
    }
    return dp[n];
  }
}
