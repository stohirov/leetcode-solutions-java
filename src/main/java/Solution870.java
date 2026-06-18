import java.util.*;

public class Solution870 {
  public int[] advantageCount(int[] nums1, int[] nums2) {
    int n = nums1.length;
    Arrays.sort(nums1);
    Integer[] idx = new Integer[n];
    for (int i = 0; i < n; i++) {
      idx[i] = i;
    }
    Arrays.sort(idx, (a, b) -> nums2[a] - nums2[b]);
    int[] result = new int[n];
    int lo = 0;
    int hi = n - 1;
    for (int i = n - 1; i >= 0; i--) {
      if (nums1[hi] > nums2[idx[i]]) {
        result[idx[i]] = nums1[hi];
        hi--;
      } else {
        result[idx[i]] = nums1[lo];
        lo++;
      }
    }
    return result;
  }
}
