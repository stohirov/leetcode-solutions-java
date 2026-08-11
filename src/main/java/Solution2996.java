import java.util.HashSet;
import java.util.Set;

public class Solution2996 {

  public int missingInteger(int[] nums) {
    int sum = nums[0];
    for (int i = 1; i < nums.length && nums[i] == nums[i - 1] + 1; i++) {
      sum += nums[i];
    }

    Set<Integer> seen = new HashSet<>();
    for (int num : nums) {
      seen.add(num);
    }

    int x = sum;
    while (seen.contains(x)) {
      x++;
    }

    return x;
  }

}
