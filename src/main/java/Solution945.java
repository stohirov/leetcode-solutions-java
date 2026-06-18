import java.util.*;

public class Solution945 {
  public int minIncrementForUnique(int[] nums) {
    Arrays.sort(nums);
    int moves = 0;
    int next = 0;
    for (int num : nums) {
      if (num < next) {
        moves += next - num;
        next++;
      } else {
        next = num + 1;
      }
    }
    return moves;
  }
}
