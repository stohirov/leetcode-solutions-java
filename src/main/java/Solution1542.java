import java.util.Arrays;

public class Solution1542 {

  public int longestAwesome(String s) {
    int[] firstSeen = new int[1024];
    Arrays.fill(firstSeen, Integer.MAX_VALUE);
    firstSeen[0] = -1;

    int mask = 0;
    int ans = 0;
    for (int i = 0; i < s.length(); i++) {
      mask ^= (1 << (s.charAt(i) - '0'));
      if (firstSeen[mask] != Integer.MAX_VALUE) {
        ans = Math.max(ans, i - firstSeen[mask]);
      } else {
        firstSeen[mask] = i;
      }

      for (int b = 0; b < 10; b++) {
        int m = mask ^ (1 << b);
        if (firstSeen[m] != Integer.MAX_VALUE) {
          ans = Math.max(ans, i - firstSeen[m]);
        }
      }
    }
    return ans;
  }
}
