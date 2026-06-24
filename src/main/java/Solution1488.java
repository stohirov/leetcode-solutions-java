import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Solution1488 {
  public int[] avoidFlood(int[] rains) {
    int n = rains.length;
    int[] result = new int[n];
    Map<Integer, Integer> fullLakes = new HashMap<>();
    TreeSet<Integer> dryDays = new TreeSet<>();

    for (int i = 0; i < n; i++) {
      if (rains[i] == 0) {
        dryDays.add(i);
        result[i] = 1;
      } else {
        int lake = rains[i];
        result[i] = -1;
        if (fullLakes.containsKey(lake)) {
          Integer dry = dryDays.higher(fullLakes.get(lake));
          if (dry == null) {
            return new int[0];
          }
          result[dry] = lake;
          dryDays.remove(dry);
        }
        fullLakes.put(lake, i);
      }
    }
    return result;
  }
}
