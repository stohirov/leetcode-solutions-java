import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution347 {
  public int[] topKFrequent(int[] nums, int k) {
    Map<Integer, Integer> count = new HashMap<>();
    for (int num : nums) {
      count.merge(num, 1, Integer::sum);
    }
    @SuppressWarnings("unchecked")
    List<Integer>[] buckets = new List[nums.length + 1];
    for (Map.Entry<Integer, Integer> e : count.entrySet()) {
      int freq = e.getValue();
      if (buckets[freq] == null) buckets[freq] = new ArrayList<>();
      buckets[freq].add(e.getKey());
    }
    int[] result = new int[k];
    int idx = 0;
    for (int f = buckets.length - 1; f >= 0 && idx < k; f--) {
      if (buckets[f] == null) continue;
      for (int num : buckets[f]) {
        result[idx++] = num;
        if (idx == k) break;
      }
    }
    return result;
  }
}
