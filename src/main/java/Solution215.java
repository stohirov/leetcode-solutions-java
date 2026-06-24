import java.util.Random;

public class Solution215 {
  private final Random rand = new Random();

  public int findKthLargest(int[] nums, int k) {
    int target = nums.length - k;
    int lo = 0, hi = nums.length - 1;
    while (lo <= hi) {
      int p = partition(nums, lo, hi);
      if (p == target) return nums[p];
      else if (p < target) lo = p + 1;
      else hi = p - 1;
    }
    return -1;
  }

  private int partition(int[] nums, int lo, int hi) {
    int pivotIdx = lo + rand.nextInt(hi - lo + 1);
    int pivot = nums[pivotIdx];
    swap(nums, pivotIdx, hi);
    int store = lo;
    for (int i = lo; i < hi; i++) {
      if (nums[i] < pivot) {
        swap(nums, i, store++);
      }
    }
    swap(nums, store, hi);
    return store;
  }

  private void swap(int[] nums, int i, int j) {
    int t = nums[i];
    nums[i] = nums[j];
    nums[j] = t;
  }
}
