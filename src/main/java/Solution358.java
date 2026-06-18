import java.util.*;

public class Solution358 {
  public String rearrangeString(String s, int k) {
    if (k <= 1) {
      return s;
    }
    int[] count = new int[26];
    for (char c : s.toCharArray()) {
      count[c - 'a']++;
    }
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);
    for (int i = 0; i < 26; i++) {
      if (count[i] > 0) {
        pq.offer(new int[] {i, count[i]});
      }
    }
    StringBuilder sb = new StringBuilder();
    Queue<int[]> wait = new LinkedList<>();
    while (!pq.isEmpty()) {
      int[] cur = pq.poll();
      sb.append((char) (cur[0] + 'a'));
      cur[1]--;
      wait.offer(cur);
      if (wait.size() >= k) {
        int[] front = wait.poll();
        if (front[1] > 0) {
          pq.offer(front);
        }
      }
    }
    return sb.length() == s.length() ? sb.toString() : "";
  }
}
