import java.util.Arrays;

public class Solution996 {

  private int count;

  public int numSquarefulPerms(int[] nums) {
    Arrays.sort(nums);
    count = 0;
    boolean[] used = new boolean[nums.length];
    int[] path = new int[nums.length];
    dfs(nums, used, path, 0);
    return count;
  }

  private void dfs(int[] nums, boolean[] used, int[] path, int depth) {
    if (depth == nums.length) {
      count++;
      return;
    }
    for (int i = 0; i < nums.length; i++) {
      if (used[i]) {
        continue;
      }
      if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
        continue;
      }
      if (depth > 0 && !isSquare(path[depth - 1] + nums[i])) {
        continue;
      }
      used[i] = true;
      path[depth] = nums[i];
      dfs(nums, used, path, depth + 1);
      used[i] = false;
    }
  }

  private boolean isSquare(int x) {
    int s = (int) Math.sqrt(x);
    return s * s == x || (s + 1) * (s + 1) == x;
  }
}
