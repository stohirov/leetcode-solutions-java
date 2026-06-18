import java.util.*;

public class Solution517 {
  public int findMinMoves(int[] machines) {
    int total = 0;
    for (int m : machines) total += m;
    int n = machines.length;
    if (total % n != 0) return -1;
    int avg = total / n;
    int moves = 0;
    int runningBalance = 0;
    for (int m : machines) {
      int diff = m - avg;
      runningBalance += diff;
      moves = Math.max(moves, Math.max(Math.abs(runningBalance), diff));
    }
    return moves;
  }
}
