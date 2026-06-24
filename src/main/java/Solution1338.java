import java.util.HashMap;
import java.util.Map;

public class Solution1338 {

  public int minSetSize(int[] arr) {
    Map<Integer, Integer> count = new HashMap<>();
    for (int x : arr) count.merge(x, 1, Integer::sum);
    int[] freqs = new int[count.size()];
    int idx = 0;
    for (int f : count.values()) freqs[idx++] = f;
    java.util.Arrays.sort(freqs);
    int half = arr.length / 2;
    int removed = 0, chosen = 0;
    for (int i = freqs.length - 1; i >= 0; i--) {
      removed += freqs[i];
      chosen++;
      if (removed >= half) break;
    }
    return chosen;
  }
}
