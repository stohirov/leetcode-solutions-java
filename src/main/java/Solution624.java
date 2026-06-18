import java.util.*;

public class Solution624 {

  public int maxDistance(List<List<Integer>> arrays) {
    int result = 0;
    int curMin = arrays.getFirst().getFirst();
    int curMax = arrays.getFirst().getLast();
    for (int i = 1; i < arrays.size(); i++) {
      List<Integer> arr = arrays.get(i);
      int low = arr.getFirst();
      int high = arr.getLast();
      result = Math.max(result, Math.abs(high - curMin));
      result = Math.max(result, Math.abs(curMax - low));
      curMin = Math.min(curMin, low);
      curMax = Math.max(curMax, high);
    }
    return result;
  }
}
