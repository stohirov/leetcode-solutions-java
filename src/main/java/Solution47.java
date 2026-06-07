import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution47 {
  public List<List<Integer>> permuteUnique(int[] nums) {
    List<List<Integer>> result = new ArrayList<>();
    Arrays.sort(nums);

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
      if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
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
