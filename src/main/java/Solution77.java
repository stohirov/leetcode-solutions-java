import java.util.ArrayList;
import java.util.List;

public class Solution77 {
  public List<List<Integer>> combine(int n, int k) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(n, k, 1, new ArrayList<>(), result);
    return result;
  }

  private void backtrack(int n, int k, int start, List<Integer> current,
                         List<List<Integer>> result) {
    if (current.size() == k) {
      result.add(new ArrayList<>(current));
      return;
    }

    int need = k - current.size();
    for (int i = start; i <= n - need + 1; i++) {
      current.add(i);
      backtrack(n, k, i + 1, current, result);
      current.removeLast();
    }
  }
}
