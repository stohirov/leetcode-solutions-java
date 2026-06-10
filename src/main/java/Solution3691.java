public class Solution3691 {
  public long maxTotalValue(int[] nums, int k) {
    int n = nums.length;
    long total = (long) n * (n + 1) / 2;

    int globalMax = nums[0];
    int globalMin = nums[0];
    for (int v : nums) {
      globalMax = Math.max(globalMax, v);
      globalMin = Math.min(globalMin, v);
    }
    int range = globalMax - globalMin;

    long[] all = computeLE(nums, range);
    long sumValueAll = all[1] - all[2];

    int lo = 0;
    int hi = range;
    while (lo < hi) {
      int mid = lo + (hi - lo + 1) / 2;
      long countLessThanMid = computeLE(nums, mid - 1)[0];
      long countAtLeastMid = total - countLessThanMid;
      if (countAtLeastMid >= k) {
        lo = mid;
      } else {
        hi = mid - 1;
      }
    }
    int threshold = lo;

    long[] le = computeLE(nums, threshold);
    long countStrictlyGreater = total - le[0];
    long sumValueLE = le[1] - le[2];
    long sumStrictlyGreater = sumValueAll - sumValueLE;

    return sumStrictlyGreater + (k - countStrictlyGreater) * (long) threshold;
  }

  private long[] computeLE(int[] nums, long limit) {
    int n = nums.length;

    int[] maxVal = new int[n + 1];
    long[] maxCnt = new long[n + 1];
    int[] minVal = new int[n + 1];
    long[] minCnt = new long[n + 1];
    int maxHead = 0, maxTail = 0;
    int minHead = 0, minTail = 0;
    long maxSum = 0;
    long minSum = 0;

    long count = 0;
    long sumMax = 0;
    long sumMin = 0;
    int left = 0;

    for (int r = 0; r < n; r++) {
      int x = nums[r];

      long mergedMax = 1;
      while (maxTail > maxHead && maxVal[maxTail - 1] <= x) {
        maxTail--;
        mergedMax += maxCnt[maxTail];
        maxSum -= (long) maxVal[maxTail] * maxCnt[maxTail];
      }
      maxVal[maxTail] = x;
      maxCnt[maxTail] = mergedMax;
      maxTail++;
      maxSum += (long) x * mergedMax;

      long mergedMin = 1;
      while (minTail > minHead && minVal[minTail - 1] >= x) {
        minTail--;
        mergedMin += minCnt[minTail];
        minSum -= (long) minVal[minTail] * minCnt[minTail];
      }
      minVal[minTail] = x;
      minCnt[minTail] = mergedMin;
      minTail++;
      minSum += (long) x * mergedMin;

      while (maxVal[maxHead] - minVal[minHead] > limit) {
        maxCnt[maxHead]--;
        maxSum -= maxVal[maxHead];
        if (maxCnt[maxHead] == 0) {
          maxHead++;
        }
        minCnt[minHead]--;
        minSum -= minVal[minHead];
        if (minCnt[minHead] == 0) {
          minHead++;
        }
        left++;
      }

      count += r - left + 1;
      sumMax += maxSum;
      sumMin += minSum;
    }

    return new long[] {count, sumMax, sumMin};
  }
}
