import java.util.*;

public class Solution621 {
  public int leastInterval(char[] tasks, int n) {
    int[] counts = new int[26];
    for (char t : tasks) counts[t - 'A']++;
    int maxCount = 0;
    for (int c : counts) maxCount = Math.max(maxCount, c);
    int numMax = 0;
    for (int c : counts) if (c == maxCount) numMax++;
    int frame = (maxCount - 1) * (n + 1) + numMax;
    return Math.max(frame, tasks.length);
  }
}
