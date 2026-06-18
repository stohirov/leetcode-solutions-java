import java.util.Arrays;

public class Solution1349 {
  public int maxStudents(char[][] seats) {
    int m = seats.length, n = seats[0].length;
    int[] valid = new int[m];

    for (int i = 0; i < m; i++) {
      int mask = 0;
      for (int j = 0; j < n; j++) {
        if (seats[i][j] == '.') {
          mask |= (1 << j);
        }
      }
      valid[i] = mask;
    }

    int full = 1 << n;
    int[] prev = new int[full];
    for (int i = 0; i < full; i++) prev[i] = -1;
    prev[0] = 0;

    for (int i = 0; i < m; i++) {
      int[] cur = new int[full];
      Arrays.fill(cur, -1);
      for (int s = 0; s < full; s++) {
        if ((s & valid[i]) != s) continue;
        if ((s & (s << 1)) != 0) continue;
        for (int p = 0; p < full; p++) {
          if (prev[p] < 0) continue;
          if ((s & (p << 1)) != 0) continue;
          if ((s & (p >> 1)) != 0) continue;
          int count = prev[p] + Integer.bitCount(s);
          if (count > cur[s]) {
            cur[s] = count;
          }
        }
      }
      prev = cur;
    }

    int ans = 0;
    for (int s = 0; s < full; s++) {
      ans = Math.max(ans, prev[s]);
    }
    return ans;
  }
}
