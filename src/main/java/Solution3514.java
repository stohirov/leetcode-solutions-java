public class Solution3514 {
  public int uniqueXorTriplets(int[] nums) {
    int max = 0;
    for (int x : nums) max = Math.max(max, x);
    int m = Integer.highestOneBit(max) << 1;

    long[] f = new long[m];
    boolean[] seen = new boolean[m];
    for (int x : nums)
      if (!seen[x]) { seen[x] = true; f[x] = 1; }

    fwht(f);
    for (int i = 0; i < m; i++) f[i] = f[i] * f[i] * f[i];
    fwht(f);

    int count = 0;
    for (int i = 0; i < m; i++)
      if (f[i] != 0) count++;
    return count;
  }

  private static void fwht(long[] a) {
    int n = a.length;
    for (int len = 1; len < n; len <<= 1)
      for (int i = 0; i < n; i += len << 1)
        for (int j = i; j < i + len; j++) {
          long u = a[j], v = a[j + len];
          a[j] = u + v;
          a[j + len] = u - v;
        }
  }
}
