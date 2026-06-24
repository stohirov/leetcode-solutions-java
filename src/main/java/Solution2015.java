import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

public class Solution2015 {

  public int[][] averageHeightOfBuildings(int[][] buildings) {
    // sum and count of heights added/removed at each coordinate
    TreeMap<Integer, long[]> events = new TreeMap<>();
    for (int[] b : buildings) {
      int start = b[0], end = b[1], h = b[2];
      events.computeIfAbsent(start, x -> new long[2]);
      events.get(start)[0] += h;
      events.get(start)[1] += 1;
      events.computeIfAbsent(end, x -> new long[2]);
      events.get(end)[0] -= h;
      events.get(end)[1] -= 1;
    }

    List<int[]> result = new ArrayList<>();
    long sum = 0, count = 0;
    int prevX = 0;
    boolean active = false;
    for (var entry : events.entrySet()) {
      int x = entry.getKey();
      if (active && count > 0) {
        int avg = (int) (sum / count);
        if (!result.isEmpty() && result.get(result.size() - 1)[1] == prevX
            && result.get(result.size() - 1)[2] == avg) {
          result.get(result.size() - 1)[1] = x;
        } else {
          result.add(new int[] {prevX, x, avg});
        }
      }
      sum += entry.getValue()[0];
      count += entry.getValue()[1];
      prevX = x;
      active = true;
    }

    return result.toArray(new int[0][]);
  }
}
