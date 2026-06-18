import java.util.*;

public class Solution954 {
  public boolean canReorderDoubled(int[] arr) {
    Map<Integer, Integer> count = new HashMap<>();
    for (int x : arr) count.merge(x, 1, Integer::sum);

    Integer[] keys = count.keySet().toArray(new Integer[0]);
    Arrays.sort(keys, Comparator.comparingInt(Math::abs));

    for (int x : keys) {
      int c = count.get(x);
      if (c == 0) continue;
      int twice = x * 2;
      if (count.getOrDefault(twice, 0) < c) return false;
      count.put(twice, count.get(twice) - c);
      count.put(x, 0);
    }
    return true;
  }
}
