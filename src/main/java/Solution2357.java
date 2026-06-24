import java.util.HashSet;
import java.util.Set;

public class Solution2357 {
  public int minimumOperations(int[] nums) {
    Set<Integer> distinct = new HashSet<>();
    for (int x : nums) {
      if (x > 0) distinct.add(x);
    }
    return distinct.size();
  }
}
