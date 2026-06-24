import java.util.Arrays;

public class Solution1648 {
  public int maxProfit(int[] inventory, int orders) {
    final int MOD = 1_000_000_007;
    Arrays.sort(inventory);
    long total = 0;
    long n = inventory.length;
    long ordersLeft = orders;
    int idx = inventory.length - 1;

    while (ordersLeft > 0 && idx >= 0) {
      long curVal = inventory[idx];
      long nextVal = (idx > 0) ? inventory[idx - 1] : 0;
      long width = n - idx;
      long fullSell = (curVal - nextVal) * width;

      if (fullSell <= ordersLeft) {
        long count = curVal - nextVal;
        long sumPerColumn = (curVal + nextVal + 1) % MOD * (count % MOD) % MOD;
        sumPerColumn = sumPerColumn * inverse2() % MOD;
        total = (total + sumPerColumn * (width % MOD)) % MOD;
        ordersLeft -= fullSell;
        idx--;
      } else {
        long h = ordersLeft / width;
        long bottom = curVal - h;
        long count = curVal - bottom;
        long sumPerColumn = (curVal + bottom + 1) % MOD * (count % MOD) % MOD;
        sumPerColumn = sumPerColumn * inverse2() % MOD;
        total = (total + sumPerColumn * (width % MOD)) % MOD;
        ordersLeft -= h * width;
        total = (total + (bottom % MOD) * (ordersLeft % MOD)) % MOD;
        ordersLeft = 0;
      }
    }
    return (int) (total % MOD);
  }

  private long inverse2() {
    return (1000000007 + 1) / 2;
  }
}
