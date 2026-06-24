import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.TreeMap;

public class Solution1086 {

  public int[][] highFive(int[][] items) {
    TreeMap<Integer, PriorityQueue<Integer>> map = new TreeMap<>();
    for (int[] item : items) {
      int id = item[0], score = item[1];
      PriorityQueue<Integer> pq = map.computeIfAbsent(id, k -> new PriorityQueue<>());
      pq.offer(score);
      if (pq.size() > 5) {
        pq.poll();
      }
    }

    List<int[]> result = new ArrayList<>();
    for (Map.Entry<Integer, PriorityQueue<Integer>> e : map.entrySet()) {
      int sum = 0;
      for (int s : e.getValue()) {
        sum += s;
      }
      result.add(new int[] {e.getKey(), sum / 5});
    }
    return result.toArray(new int[0][]);
  }
}
