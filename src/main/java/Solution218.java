import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Solution218 {
  public List<List<Integer>> getSkyline(int[][] buildings) {
    List<int[]> events = new ArrayList<>();
    for (int[] b : buildings) {
      events.add(new int[]{b[0], -b[2], b[1]});
      events.add(new int[]{b[1], b[2], 0});
    }
    events.sort((a, b) -> a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]);

    List<List<Integer>> result = new ArrayList<>();
    TreeMap<Integer, Integer> heights = new TreeMap<>();
    heights.put(0, 1);
    int prev = 0;
    for (int[] e : events) {
      int h = e[1];
      if (h < 0) {
        heights.merge(-h, 1, Integer::sum);
      } else if (h > 0) {
        int cnt = heights.get(h);
        if (cnt == 1) heights.remove(h);
        else heights.put(h, cnt - 1);
      }
      int cur = heights.lastKey();
      if (cur != prev) {
        result.add(List.of(e[0], cur));
        prev = cur;
      }
    }
    return result;
  }
}
