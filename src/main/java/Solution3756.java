public class Solution3756 {

  private static final int MOD = 1_000_000_007;

  public int[] processQueries(String s, int[][] queries) {
    int m = s.length();

    long[] pow10 = new long[m + 1];
    long[] invPow10 = new long[m + 1];
    pow10[0] = 1;
    for (int k = 1; k <= m; k++) {
      pow10[k] = pow10[k - 1] * 10 % MOD;
    }
    long inv10 = modPow(10, MOD - 2);
    invPow10[0] = 1;
    for (int k = 1; k <= m; k++) {
      invPow10[k] = invPow10[k - 1] * inv10 % MOD;
    }

    int[] cnt = new int[m + 1];
    long[] digitSum = new long[m + 1];
    long[] w = new long[m + 1];
    for (int i = 0; i < m; i++) {
      int d = s.charAt(i) - '0';
      cnt[i + 1] = cnt[i];
      digitSum[i + 1] = digitSum[i];
      w[i + 1] = w[i];
      if (d != 0) {
        cnt[i + 1]++;
        digitSum[i + 1] = (digitSum[i + 1] + d) % MOD;
        w[i + 1] = (w[i + 1] + (long) d * invPow10[cnt[i + 1]]) % MOD;
      }
    }

    int q = queries.length;
    int[] answer = new int[q];
    for (int i = 0; i < q; i++) {
      int l = queries[i][0];
      int r = queries[i][1];
      long wRange = (w[r + 1] - w[l] + MOD) % MOD;
      long x = pow10[cnt[r + 1]] * wRange % MOD;
      long sum = (digitSum[r + 1] - digitSum[l] + MOD) % MOD;
      answer[i] = (int) (x * sum % MOD);
    }
    return answer;
  }

  private long modPow(long base, long exp) {
    long result = 1;
    base %= MOD;
    while (exp > 0) {
      if ((exp & 1) == 1) {
        result = result * base % MOD;
      }
      base = base * base % MOD;
      exp >>= 1;
    }
    return result;
  }
}
