import java.util.ArrayList;
import java.util.List;

public class Solution1018 {
  public List<Boolean> prefixesDivBy5(int[] nums) {
    List<Boolean> result = new ArrayList<>();
    int rem = 0;
    for (int num : nums) {
      rem = (rem * 2 + num) % 5;
      result.add(rem == 0);
    }
    return result;
  }
}
