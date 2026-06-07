import java.util.ArrayList;
import java.util.List;

public class Solution46 {
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();

    List<Integer> current = new ArrayList<>();
    boolean[] used = new boolean[nums.length];
    backtrack(nums, used, current, result);
    return result;
  }

  private void backtrack(int[] nums, boolean[] used, List<Integer> current,
                         List<List<Integer>> result) {
    if (current.size() == nums.length) {
      result.add(new ArrayList<>(current));
      return;
    }

    for (int i = 0; i < nums.length; i++) {
      if (used[i]) {
        continue;
      }

      used[i] = true;
      current.add(nums[i]);

      backtrack(nums, used, current, result);

      current.removeLast();
      used[i] = false;
    }
  }
}
