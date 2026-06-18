import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution491 {

  private final List<List<Integer>> result = new ArrayList<>();

  public List<List<Integer>> findSubsequences(int[] nums) {
    backtrack(nums, 0, new ArrayList<>());
    return result;
  }

  private void backtrack(int[] nums, int start, List<Integer> current) {
    if (current.size() >= 2) {
      result.add(new ArrayList<>(current));
    }
    Set<Integer> used = new HashSet<>();
    for (int i = start; i < nums.length; i++) {
      if (!current.isEmpty() && nums[i] < current.getLast()) {
        continue;
      }
      if (used.contains(nums[i])) {
        continue;
      }
      used.add(nums[i]);
      current.add(nums[i]);
      backtrack(nums, i + 1, current);
      current.removeLast();
    }
  }
}
