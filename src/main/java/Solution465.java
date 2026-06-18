import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution465 {
  public int minTransfers(int[][] transactions) {
    Map<Integer, Integer> balanceMap = new HashMap<>();
    for (int[] t : transactions) {
      balanceMap.merge(t[0], -t[2], Integer::sum);
      balanceMap.merge(t[1], t[2], Integer::sum);
    }
    List<Integer> debts = new ArrayList<>();
    for (int bal : balanceMap.values()) {
      if (bal != 0) debts.add(bal);
    }
    int[] arr = new int[debts.size()];
    for (int i = 0; i < arr.length; i++) arr[i] = debts.get(i);
    return dfs(arr, 0);
  }

  private int dfs(int[] debts, int start) {
    while (start < debts.length && debts[start] == 0) start++;
    if (start == debts.length) return 0;
    int min = Integer.MAX_VALUE;
    for (int i = start + 1; i < debts.length; i++) {
      if (debts[i] * debts[start] < 0) {
        debts[i] += debts[start];
        min = Math.min(min, 1 + dfs(debts, start + 1));
        debts[i] -= debts[start];
        if (debts[i] + debts[start] == 0) break;
      }
    }
    return min;
  }
}
