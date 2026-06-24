import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution1801 {

  private static final int MOD = 1_000_000_007;

  public int getNumberOfBacklogOrders(int[][] orders) {
    PriorityQueue<int[]> buy = new PriorityQueue<>((a, b) -> b[0] - a[0]);
    PriorityQueue<int[]> sell = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

    for (int[] order : orders) {
      int price = order[0], amount = order[1], type = order[2];
      if (type == 0) {
        while (amount > 0 && !sell.isEmpty() && sell.peek()[0] <= price) {
          int[] s = sell.peek();
          int take = Math.min(amount, s[1]);
          amount -= take;
          s[1] -= take;
          if (s[1] == 0) {
            sell.poll();
          }
        }
        if (amount > 0) {
          buy.offer(new int[] {price, amount});
        }
      } else {
        while (amount > 0 && !buy.isEmpty() && buy.peek()[0] >= price) {
          int[] b = buy.peek();
          int take = Math.min(amount, b[1]);
          amount -= take;
          b[1] -= take;
          if (b[1] == 0) {
            buy.poll();
          }
        }
        if (amount > 0) {
          sell.offer(new int[] {price, amount});
        }
      }
    }

    long total = 0;
    for (int[] b : buy) {
      total = (total + b[1]) % MOD;
    }
    for (int[] s : sell) {
      total = (total + s[1]) % MOD;
    }
    return (int) total;
  }
}
