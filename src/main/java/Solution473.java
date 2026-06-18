import java.util.Arrays;

public class Solution473 {
  public boolean makesquare(int[] matchsticks) {
    int total = 0;
    for (int m : matchsticks) total += m;
    if (total == 0 || total % 4 != 0) return false;
    int side = total / 4;
    Arrays.sort(matchsticks);
    for (int i = 0, j = matchsticks.length - 1; i < j; i++, j--) {
      int tmp = matchsticks[i];
      matchsticks[i] = matchsticks[j];
      matchsticks[j] = tmp;
    }
    if (matchsticks[0] > side) return false;
    int[] sides = new int[4];
    return dfs(matchsticks, 0, sides, side);
  }

  private boolean dfs(int[] sticks, int index, int[] sides, int target) {
    if (index == sticks.length) {
      return true;
    }
    for (int i = 0; i < 4; i++) {
      if (sides[i] + sticks[index] > target) continue;
      boolean dup = false;
      for (int k = 0; k < i; k++) {
        if (sides[k] == sides[i]) { dup = true; break; }
      }
      if (dup) continue;
      sides[i] += sticks[index];
      if (dfs(sticks, index + 1, sides, target)) return true;
      sides[i] -= sticks[index];
    }
    return false;
  }
}
