public class Solution3312 {

  public int[] gcdValues(int[] nums, long[] queries) {
    int max = 0;
    for (int x : nums) {
      max = Math.max(max, x);
    }

    int[] freq = new int[max + 1];
    for (int x : nums) {
      freq[x]++;
    }

    long[] pairsWithGcd = new long[max + 1];
    for (int d = max; d >= 1; d--) {
      long divisible = 0;
      for (int m = d; m <= max; m += d) {
        divisible += freq[m];
      }
      pairsWithGcd[d] = divisible * (divisible - 1) / 2;
      for (int m = 2 * d; m <= max; m += d) {
        pairsWithGcd[d] -= pairsWithGcd[m];
      }
    }

    for (int d = 1; d <= max; d++) {
      pairsWithGcd[d] += pairsWithGcd[d - 1];
    }

    int[] answer = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      answer[i] = firstGcdAbove(pairsWithGcd, queries[i]);
    }
    return answer;
  }

  private int firstGcdAbove(long[] prefix, long index) {
    int lo = 1;
    int hi = prefix.length - 1;
    while (lo < hi) {
      int mid = (lo + hi) >>> 1;
      if (prefix[mid] > index) {
        hi = mid;
      } else {
        lo = mid + 1;
      }
    }
    return lo;
  }
}
