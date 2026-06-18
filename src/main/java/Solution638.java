import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution638 {

  public int shoppingOffers(List<Integer> price, List<List<Integer>> special, List<Integer> needs) {
    return dfs(price, special, needs, new HashMap<>());
  }

  private int dfs(List<Integer> price, List<List<Integer>> special, List<Integer> needs,
      Map<List<Integer>, Integer> memo) {
    Integer cached = memo.get(needs);
    if (cached != null) {
      return cached;
    }
    int n = price.size();
    int direct = 0;
    for (int i = 0; i < n; i++) {
      direct += price.get(i) * needs.get(i);
    }
    int best = direct;
    for (List<Integer> offer : special) {
      boolean valid = true;
      for (int i = 0; i < n; i++) {
        if (needs.get(i) - offer.get(i) < 0) {
          valid = false;
          break;
        }
      }
      if (!valid) {
        continue;
      }
      List<Integer> remaining = new ArrayList<>(needs);
      for (int i = 0; i < n; i++) {
        remaining.set(i, needs.get(i) - offer.get(i));
      }
      best = Math.min(best, offer.get(n) + dfs(price, special, remaining, memo));
    }
    memo.put(needs, best);
    return best;
  }
}
