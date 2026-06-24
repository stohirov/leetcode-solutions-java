import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution2099 {

  public int[] maxSubsequence(int[] nums, int k) {
    PriorityQueue<int[]> heap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    for (int i = 0; i < nums.length; i++) {
      heap.offer(new int[] {nums[i], i});
      if (heap.size() > k) {
        heap.poll();
      }
    }
    int[][] chosen = new int[k][];
    for (int i = 0; i < k; i++) {
      chosen[i] = heap.poll();
    }

    Arrays.sort(chosen, Comparator.comparingInt(a -> a[1]));
    int[] result = new int[k];
    for (int i = 0; i < k; i++) {
      result[i] = chosen[i][0];
    }
    return result;
  }
}
