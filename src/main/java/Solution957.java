import java.util.HashMap;
import java.util.Map;

public class Solution957 {

  public int[] prisonAfterNDays(int[] cells, int n) {
    Map<String, Integer> seen = new HashMap<>();
    boolean cycleFound = false;
    while (n > 0) {
      if (!cycleFound) {
        String key = java.util.Arrays.toString(cells);
        if (seen.containsKey(key)) {
          int cycleLen = seen.get(key) - n;
          n %= cycleLen;
          cycleFound = true;
          if (n == 0) {
            break;
          }
        } else {
          seen.put(key, n);
        }
      }
      cells = nextDay(cells);
      n--;
    }
    return cells;
  }

  private int[] nextDay(int[] cells) {
    int[] next = new int[cells.length];
    for (int i = 1; i < cells.length - 1; i++) {
      next[i] = cells[i - 1] == cells[i + 1] ? 1 : 0;
    }
    return next;
  }
}
