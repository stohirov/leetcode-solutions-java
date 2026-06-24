import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution451 {
  public String frequencySort(String s) {
    Map<Character, Integer> count = new HashMap<>();
    for (char c : s.toCharArray()) count.merge(c, 1, Integer::sum);
    PriorityQueue<Map.Entry<Character, Integer>> pq =
        new PriorityQueue<>((a, b) -> b.getValue() - a.getValue());
    pq.addAll(count.entrySet());
    StringBuilder sb = new StringBuilder();
    while (!pq.isEmpty()) {
      Map.Entry<Character, Integer> e = pq.poll();
      sb.repeat(String.valueOf(e.getKey()), Math.max(0, e.getValue()));
    }
    return sb.toString();
  }
}
