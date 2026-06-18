import java.util.HashMap;
import java.util.Map;

public class Solution464 {
  public boolean canIWin(int maxChoosableInteger, int desiredTotal) {
    if (desiredTotal <= 0) return true;
    int sum = maxChoosableInteger * (maxChoosableInteger + 1) / 2;
    if (sum < desiredTotal) return false;
    Map<Integer, Boolean> memo = new HashMap<>();
    return canWin(maxChoosableInteger, desiredTotal, 0, memo);
  }

  private boolean canWin(int max, int remaining, int used, Map<Integer, Boolean> memo) {
    if (memo.containsKey(used)) return memo.get(used);
    for (int i = 1; i <= max; i++) {
      int bit = 1 << (i - 1);
      if ((used & bit) != 0) continue;
      if (i >= remaining || !canWin(max, remaining - i, used | bit, memo)) {
        memo.put(used, true);
        return true;
      }
    }
    memo.put(used, false);
    return false;
  }
}
