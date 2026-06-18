import java.util.*;

public class Solution632 {

  public int[] smallestRange(List<List<Integer>> nums) {
    int k = nums.size();
    PriorityQueue<int[]> minHeap = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
    int curMax = Integer.MIN_VALUE;
    for (int i = 0; i < k; i++) {
      int val = nums.get(i).getFirst();
      minHeap.offer(new int[] {val, i, 0});
      curMax = Math.max(curMax, val);
    }
    int[] best = new int[] {0, Integer.MAX_VALUE};
    while (minHeap.size() == k) {
      int[] top = minHeap.poll();
      int val = top[0];
      int listIdx = top[1];
      int elemIdx = top[2];
      if (curMax - val < best[1] - best[0]) {
        best[0] = val;
        best[1] = curMax;
      }
      if (elemIdx + 1 < nums.get(listIdx).size()) {
        int next = nums.get(listIdx).get(elemIdx + 1);
        minHeap.offer(new int[] {next, listIdx, elemIdx + 1});
        curMax = Math.max(curMax, next);
      }
    }
    return best;
  }
}
