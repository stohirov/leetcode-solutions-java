import java.util.*;

public class Solution1090 {
  public int largestValsFromLabels(int[] values, int[] labels, int numWanted, int useLimit) {
    int n = values.length;
    Integer[] idx = new Integer[n];
    for (int i = 0; i < n; i++) idx[i] = i;
    Arrays.sort(idx, (a, b) -> values[b] - values[a]);
    Map<Integer, Integer> count = new HashMap<>();
    int total = 0, picked = 0;
    for (int i = 0; i < n && picked < numWanted; i++) {
      int j = idx[i];
      int label = labels[j];
      int c = count.getOrDefault(label, 0);
      if (c < useLimit) {
        total += values[j];
        count.put(label, c + 1);
        picked++;
      }
    }
    return total;
  }
}
