import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution692 {
  public List<String> topKFrequent(String[] words, int k) {
    Map<String, Integer> count = new HashMap<>();
    for (String w : words) count.merge(w, 1, Integer::sum);
    PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> {
      if (!a.getValue().equals(b.getValue())) return a.getValue() - b.getValue();
      return b.getKey().compareTo(a.getKey());
    });
    for (Map.Entry<String, Integer> e : count.entrySet()) {
      pq.offer(e);
      if (pq.size() > k) pq.poll();
    }
    List<String> res = new ArrayList<>();
    while (!pq.isEmpty()) res.addFirst(pq.poll().getKey());
    return res;
  }
}
