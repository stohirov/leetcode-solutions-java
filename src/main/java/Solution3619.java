public class Solution3619 {
    private static final int[][] DIRS = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public int countIslands(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int[] stack = new int[m * n];

        int count = 0;
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                if (grid[r][c] == 0) {
                    continue;
                }
                long sum = 0;
                int top = 0;
                stack[top++] = r * n + c;
                sum += grid[r][c];
                grid[r][c] = 0;
                while (top > 0) {
                    int cell = stack[--top];
                    int cr = cell / n;
                    int cc = cell % n;
                    for (int[] d : DIRS) {
                        int nr = cr + d[0];
                        int nc = cc + d[1];
                        if (nr >= 0 && nr < m && nc >= 0 && nc < n && grid[nr][nc] > 0) {
                            sum += grid[nr][nc];
                            grid[nr][nc] = 0;
                            stack[top++] = nr * n + nc;
                        }
                    }
                }
                if (sum % k == 0) {
                    count++;
                }
            }
        }
        return count;
    }
}
