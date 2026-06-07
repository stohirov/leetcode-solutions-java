public class Solution4 {
  public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    // Always binary search over the smaller array.
    if (nums1.length > nums2.length) {
      return findMedianSortedArrays(nums2, nums1);
    }

    int m = nums1.length, n = nums2.length;
    int total = m + n;
    int half = (total + 1) / 2;   // size of the combined left half

    int lo = 0, hi = m;
    while (lo <= hi) {
      int i = (lo + hi) / 2;    // cut in nums1: i elements on the left
      int j = half - i;         // cut in nums2: rest of the left half

      int left1  = (i == 0) ? Integer.MIN_VALUE : nums1[i - 1];
      int right1 = (i == m) ? Integer.MAX_VALUE : nums1[i];
      int left2  = (j == 0) ? Integer.MIN_VALUE : nums2[j - 1];
      int right2 = (j == n) ? Integer.MAX_VALUE : nums2[j];

      if (left1 <= right2 && left2 <= right1) {
        // Correct partition found.
        if (total % 2 == 1) {
          return Math.max(left1, left2);
        }
        return (Math.max(left1, left2) + Math.min(right1, right2)) / 2.0;
      } else if (left1 > right2) {
        hi = i - 1;           // too many taken from nums1, move left
      } else {
        lo = i + 1;           // too few taken from nums1, move right
      }
    }

    throw new IllegalArgumentException("Input arrays are not sorted.");
  }
}
