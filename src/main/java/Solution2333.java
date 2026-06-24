import java.util.PriorityQueue;

public class Solution2333 {
  public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
    int n = nums1.length;
    long k = (long) k1 + k2;
    long[] diff = new long[n];
    long sum = 0;
    for (int i = 0; i < n; i++) {
      diff[i] = Math.abs((long) nums1[i] - nums2[i]);
      sum += diff[i];
    }
    if (sum <= k) return 0;

    int maxVal = 0;
    for (long d : diff) maxVal = Math.max(maxVal, (int) d);
    long[] count = new long[maxVal + 1];
    for (long d : diff) count[(int) d]++;

    long remaining = k;
    long ans = 0;
    int v = maxVal;
    while (v > 0 && remaining > 0) {
      long c = count[v];
      if (c == 0) {
        v--;
        continue;
      }

      if (remaining >= c) {
        remaining -= c;
        count[v - 1] += c;
        count[v] = 0;
        v--;
      } else {
        count[v] -= remaining;
        count[v - 1] += remaining;
        remaining = 0;
      }
    }
    for (int i = 0; i <= maxVal; i++) {
      if (count[i] > 0) ans += count[i] * ((long) i * i);
    }
    return ans;
  }
}
