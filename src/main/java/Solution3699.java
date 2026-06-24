public class Solution3699 {
    private static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {
        int m = r - l + 1;
        if (n == 1) {
            return m;
        }

        long[] up = new long[m + 2];
        long[] down = new long[m + 2];
        for (int v = 1; v <= m; v++) {
            up[v] = 1;
            down[v] = 1;
        }

        for (int len = 2; len <= n; len++) {
            long[] newUp = new long[m + 2];
            long[] newDown = new long[m + 2];

            long prefix = 0;
            for (int w = 1; w <= m; w++) {
                newUp[w] = prefix;
                prefix = (prefix + down[w]) % MOD;
            }

            long suffix = 0;
            for (int w = m; w >= 1; w--) {
                newDown[w] = suffix;
                suffix = (suffix + up[w]) % MOD;
            }

            up = newUp;
            down = newDown;
        }

        long ans = 0;
        for (int v = 1; v <= m; v++) {
            ans = (ans + up[v] + down[v]) % MOD;
        }
        return (int) ans;
    }
}
