import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution3731 {

  public List<Integer> findMissing(int[] nums) {
    Set<Integer> present = new HashSet<>();
    int min = Integer.MAX_VALUE, max = Integer.MIN_VALUE;

    for (int num : nums) {
      present.add(num);
      min = Math.min(min, num);
      max = Math.max(max, num);
    }

    List<Integer> missing = new ArrayList<>();
    for (int value = min; value <= max; value++) {
      if (!present.contains(value)) {
        missing.add(value);
      }
    }

    return missing;
  }

}
