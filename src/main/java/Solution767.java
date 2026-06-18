import java.util.*;

public class Solution767 {

  public String reorganizeString(String s) {
    int[] counts = new int[26];
    for (char c : s.toCharArray()) {
      counts[c - 'a']++;
    }
    PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[1] - a[1]);
    int n = s.length();
    for (int i = 0; i < 26; i++) {
      if (counts[i] > 0) {
        if (counts[i] > (n + 1) / 2) {
          return "";
        }
        maxHeap.offer(new int[] {i, counts[i]});
      }
    }
    StringBuilder sb = new StringBuilder();
    int[] prev = null;
    while (!maxHeap.isEmpty()) {
      int[] cur = maxHeap.poll();
      sb.append((char) ('a' + cur[0]));
      cur[1]--;
      if (prev != null && prev[1] > 0) {
        maxHeap.offer(prev);
      }
      prev = cur;
    }
    return sb.toString();
  }
}
