import java.util.ArrayList;
import java.util.List;

public class Solution51 {
  public List<List<String>> solveNQueens(int n) {
    List<List<String>> result = new ArrayList<>();

    boolean[] cols = new boolean[n];
    boolean[] diag1 = new boolean[2 * n - 1];
    boolean[] diag2 = new boolean[2 * n - 1];

    int[] queens = new int[n];
    backtrack(0, n, queens, cols, diag1, diag2, result);
    return result;
  }

  private void backtrack(int row, int n, int[] queens, boolean[] cols,
                         boolean[] diag1, boolean[] diag2, List<List<String>> result) {
    if (row == n) {
      result.add(build(queens, n));
      return;
    }

    for (int col = 0; col < n; col++) {
      int d1 = row + col;
      int d2 = row - col + n - 1;
      if (cols[col] || diag1[d1] || diag2[d2]) {
        continue;
      }

      queens[row] = col;
      cols[col] = diag1[d1] = diag2[d2] = true;

      backtrack(row + 1, n, queens, cols, diag1, diag2, result);

      cols[col] = diag1[d1] = diag2[d2] = false;
    }
  }

  private List<String> build(int[] queens, int n) {
    List<String> board = new ArrayList<>(n);
    for (int r = 0; r < n; r++) {
      char[] rowChars = new char[n];
      java.util.Arrays.fill(rowChars, '.');
      rowChars[queens[r]] = 'Q';
      board.add(new String(rowChars));
    }
    return board;
  }
}
