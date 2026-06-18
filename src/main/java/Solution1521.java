import java.util.HashSet;
import java.util.Set;

public class Solution1521 {

  public int closestToTarget(int[] arr, int target) {
    int ans = Integer.MAX_VALUE;
    Set<Integer> prev = new HashSet<>();
    for (int x : arr) {
      Set<Integer> cur = new HashSet<>();
      cur.add(x);
      for (int v : prev) {
        cur.add(v & x);
      }
      for (int v : cur) {
        ans = Math.min(ans, Math.abs(v - target));
      }
      prev = cur;
    }
    return ans;
  }
}
