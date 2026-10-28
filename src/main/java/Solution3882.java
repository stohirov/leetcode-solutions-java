public class Solution3882 {
    public int minCost(int[][] grid) {
        int m = grid.length, n = grid[0].length;
        int size = 1024;

        boolean[][] dp = new boolean[n][size];
        dp[0][grid[0][0]] = true;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (r == 0 && c == 0) continue;

                boolean[] next = new boolean[size];
                int v = grid[r][c];
                for (int x = 0; x < size; x++) {
                    if ((r > 0 && dp[c][x]) || (c > 0 && dp[c - 1][x])) {
                        next[x ^ v] = true;
                    }
                }
                dp[c] = next;
            }
        }

        for (int x = 0; x < size; x++) {
            if (dp[n - 1][x]) return x;
        }
        return -1;
    }
}
