public class Solution1510 {

  public boolean winnerSquareGame(int n) {
    boolean[] win = new boolean[n + 1];

    for (int i = 1; i <= n; i++) {
      for (int k = 1; k * k <= i; k++) {
        if (!win[i - k * k]) {
          win[i] = true;
          break;
        }
      }
    }

    return win[n];
  }

}
