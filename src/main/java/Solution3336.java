public class Solution3336 {
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        int max = 0;
        for (int num : nums) {
            max = Math.max(max, num);
        }

        int[][] dp = new int[max + 1][max + 1];
        dp[0][0] = 1;

        for (int num : nums) {
            int[][] next = new int[max + 1][max + 1];
            for (int g1 = 0; g1 <= max; g1++) {
                for (int g2 = 0; g2 <= max; g2++) {
                    int ways = dp[g1][g2];
                    if (ways == 0) {
                        continue;
                    }

                    next[g1][g2] = (next[g1][g2] + ways) % MOD;
                    int n1 = g1 == 0 ? num : gcd(g1, num);
                    next[n1][g2] = (next[n1][g2] + ways) % MOD;
                    int n2 = g2 == 0 ? num : gcd(g2, num);
                    next[g1][n2] = (next[g1][n2] + ways) % MOD;
                }
            }
            dp = next;
        }

        long result = 0;
        for (int g = 1; g <= max; g++) {
            result += dp[g][g];
        }
        return (int) (result % MOD);
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int t = a % b;
            a = b;
            b = t;
        }
        return a;
    }
}
