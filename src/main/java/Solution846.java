import java.util.*;

public class Solution846 {
  public boolean isNStraightHand(int[] hand, int groupSize) {
    if (hand.length % groupSize != 0) {
      return false;
    }
    TreeMap<Integer, Integer> count = new TreeMap<>();
    for (int c : hand) {
      count.merge(c, 1, Integer::sum);
    }
    while (!count.isEmpty()) {
      int start = count.firstKey();
      for (int i = start; i < start + groupSize; i++) {
        Integer cnt = count.get(i);
        if (cnt == null) {
          return false;
        }
        if (cnt == 1) {
          count.remove(i);
        } else {
          count.put(i, cnt - 1);
        }
      }
    }
    return true;
  }
}
