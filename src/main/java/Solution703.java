import java.util.PriorityQueue;

public class Solution703 {
  private final int k;
  private final PriorityQueue<Integer> minHeap;

  public Solution703(int k, int[] nums) {
    this.k = k;
    this.minHeap = new PriorityQueue<>();
    for (int num : nums) add(num);
  }

  public int add(int val) {
    minHeap.offer(val);
    if (minHeap.size() > k) minHeap.poll();
    return minHeap.peek();
  }
}
