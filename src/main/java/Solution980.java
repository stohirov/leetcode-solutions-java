public class Solution980 {

  private int rows;
  private int cols;
  private int empty;
  private int result;

  public int uniquePathsIII(int[][] grid) {
    rows = grid.length;
    cols = grid[0].length;
    empty = 0;
    result = 0;
    int startR = 0, startC = 0;
    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < cols; j++) {
        if (grid[i][j] == 0) {
          empty++;
        } else if (grid[i][j] == 1) {
          startR = i;
          startC = j;
        }
      }
    }
    dfs(grid, startR, startC, 0);
    return result;
  }

  private void dfs(int[][] grid, int r, int c, int walked) {
    if (r < 0 || r >= rows || c < 0 || c >= cols || grid[r][c] == -1) {
      return;
    }
    if (grid[r][c] == 2) {
      if (walked == empty + 1) {
        result++;
      }
      return;
    }
    int original = grid[r][c];
    grid[r][c] = -1;
    dfs(grid, r + 1, c, walked + 1);
    dfs(grid, r - 1, c, walked + 1);
    dfs(grid, r, c + 1, walked + 1);
    dfs(grid, r, c - 1, walked + 1);
    grid[r][c] = original;
  }
}
