public class Solution351 {

  public int numberOfPatterns(int m, int n) {
    int[][] skip = new int[10][10];
    skip[1][3] = skip[3][1] = 2;
    skip[1][7] = skip[7][1] = 4;
    skip[3][9] = skip[9][3] = 6;
    skip[7][9] = skip[9][7] = 8;
    skip[1][9] = skip[9][1] = skip[2][8] = skip[8][2] = 5;
    skip[3][7] = skip[7][3] = skip[4][6] = skip[6][4] = 5;

    boolean[] visited = new boolean[10];
    int total = 0;
    // Use symmetry: 1,3,7,9 equivalent; 2,4,6,8 equivalent.
    total += dfs(1, m - 1, n - 1, skip, visited) * 4;
    total += dfs(2, m - 1, n - 1, skip, visited) * 4;
    total += dfs(5, m - 1, n - 1, skip, visited);
    return total;
  }

  private int dfs(int cur, int remainMin, int remainMax, int[][] skip, boolean[] visited) {
    if (remainMax < 0) {
      return 0;
    }
    int count = remainMin <= 0 ? 1 : 0;
    visited[cur] = true;
    for (int next = 1; next <= 9; next++) {
      int mid = skip[cur][next];
      if (!visited[next] && (mid == 0 || visited[mid])) {
        count += dfs(next, remainMin - 1, remainMax - 1, skip, visited);
      }
    }
    visited[cur] = false;
    return count;
  }
}
