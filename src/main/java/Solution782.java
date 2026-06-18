public class Solution782 {

  public int movesToChessboard(int[][] board) {
    int n = board.length;
    for (int[] ints : board) {
      for (int j = 0; j < n; j++) {
        if ((board[0][0] ^ ints[0] ^ board[0][j] ^ ints[j]) != 0) {
          return -1;
        }
      }
    }
    int rowSum = 0;
    int colSum = 0;
    int rowSwap = 0;
    int colSwap = 0;
    for (int i = 0; i < n; i++) {
      rowSum += board[0][i];
      colSum += board[i][0];
      if (board[i][0] == (i & 1)) {
        rowSwap++;
      }
      if (board[0][i] == (i & 1)) {
        colSwap++;
      }
    }
    if (rowSum < n / 2 || rowSum > (n + 1) / 2) {
      return -1;
    }
    if (colSum < n / 2 || colSum > (n + 1) / 2) {
      return -1;
    }
    if (n % 2 == 0) {
      rowSwap = Math.min(rowSwap, n - rowSwap);
      colSwap = Math.min(colSwap, n - colSwap);
    } else {
      if (rowSwap % 2 == 1) {
        rowSwap = n - rowSwap;
      }
      if (colSwap % 2 == 1) {
        colSwap = n - colSwap;
      }
    }
    return (rowSwap + colSwap) / 2;
  }
}
