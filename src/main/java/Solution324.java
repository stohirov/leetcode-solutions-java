import java.util.*;

public class Solution324 {
  public void wiggleSort(int[] nums) {
    int n = nums.length;
    int[] sorted = nums.clone();
    Arrays.sort(sorted);
    int mid = (n + 1) / 2;
    int left = mid - 1, right = n - 1;
    for (int i = 0; i < n; i++) {
      if (i % 2 == 0) {
        nums[i] = sorted[left--];
      } else {
        nums[i] = sorted[right--];
      }
    }
  }
}
