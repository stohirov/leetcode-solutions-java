public class Solution3700 {

  private static final int MOD = 1_000_000_007;

  public int zigZagArrays(int n, int l, int r) {
    int m = r - l + 1;
    if (n == 1) {
      return m % MOD;
    }

    long[] v = new long[m];
    for (int i = 0; i < m; i++) {
      v[i] = m - 1 - i;
    }

    long[][] mat = new long[m][m];
    for (int a = 0; a < m; a++) {
      int limit = m - 2 - a;
      for (int b = 0; b <= limit; b++) {
        mat[a][b] = 1;
      }
    }

    long[][] pow = matPow(mat, (long) n - 2, m);
    long[] dn = matVec(pow, v, m);

    long sum = 0;
    for (int i = 0; i < m; i++) {
      sum = (sum + dn[i]) % MOD;
    }
    return (int) (2 * sum % MOD);
  }

  private long[] matVec(long[][] a, long[] v, int m) {
    long[] out = new long[m];
    for (int i = 0; i < m; i++) {
      long s = 0;
      long[] row = a[i];
      for (int j = 0; j < m; j++) {
        if (row[j] != 0) {
          s += row[j] * v[j] % MOD;
        }
      }
      out[i] = s % MOD;
    }
    return out;
  }

  private long[][] matPow(long[][] base, long e, int m) {
    long[][] result = new long[m][m];
    for (int i = 0; i < m; i++) {
      result[i][i] = 1;
    }
    while (e > 0) {
      if ((e & 1) == 1) {
        result = matMul(result, base, m);
      }
      base = matMul(base, base, m);
      e >>= 1;
    }
    return result;
  }

  private long[][] matMul(long[][] a, long[][] b, int m) {
    long[][] c = new long[m][m];
    for (int i = 0; i < m; i++) {
      long[] ai = a[i];
      long[] ci = c[i];
      for (int k = 0; k < m; k++) {
        long aik = ai[k];
        if (aik == 0) {
          continue;
        }
        long[] bk = b[k];
        for (int j = 0; j < m; j++) {
          ci[j] = (ci[j] + aik * bk[j]) % MOD;
        }
      }
    }
    return c;
  }
}
