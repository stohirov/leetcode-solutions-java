public class Solution37 {
  private final boolean[][] rows = new boolean[9][10];
  private final boolean[][] cols = new boolean[9][10];
  private final boolean[][] boxes = new boolean[9][10];

  public void solveSudoku(char[][] board) {
    for (int r = 0; r < 9; r++) {
      for (int c = 0; c < 9; c++) {
        if (board[r][c] != '.') {
          int d = board[r][c] - '0';
          rows[r][d] = cols[c][d] = boxes[boxIndex(r, c)][d] = true;
        }
      }
    }
    backtrack(board, 0, 0);
  }

  private boolean backtrack(char[][] board, int r, int c) {
    if (r == 9) {
      return true;
    }

    int nextR = c == 8 ? r + 1 : r;
    int nextC = c == 8 ? 0 : c + 1;

    if (board[r][c] != '.') {
      return backtrack(board, nextR, nextC);
    }

    int box = boxIndex(r, c);
    for (int d = 1; d <= 9; d++) {
      if (rows[r][d] || cols[c][d] || boxes[box][d]) {
        continue;
      }

      board[r][c] = (char) ('0' + d);
      rows[r][d] = cols[c][d] = boxes[box][d] = true;

      if (backtrack(board, nextR, nextC)) {
        return true;
      }

      board[r][c] = '.';
      rows[r][d] = cols[c][d] = boxes[box][d] = false;
    }

    return false;
  }

  private int boxIndex(int r, int c) {
    return (r / 3) * 3 + c / 3;
  }
}
