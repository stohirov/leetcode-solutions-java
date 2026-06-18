import java.util.*;

public class Solution936 {
  public int[] movesToStamp(String stamp, String target) {
    char[] s = stamp.toCharArray();
    char[] t = target.toCharArray();
    int m = s.length;
    int n = t.length;
    boolean[] stamped = new boolean[n];
    int totalStamped = 0;
    List<Integer> result = new ArrayList<>();
    boolean changed = true;
    while (changed) {
      changed = false;
      for (int i = 0; i <= n - m; i++) {
        if (stamped[i]) {
          continue;
        }
        int replaced = canStamp(s, t, i);
        if (replaced > 0) {
          totalStamped += replaced;
          stamped[i] = true;
          result.add(i);
          changed = true;
          if (totalStamped == n) {
            break;
          }
        }
      }
    }
    if (totalStamped != n) {
      return new int[0];
    }
    int[] ans = new int[result.size()];
    for (int i = 0; i < result.size(); i++) {
      ans[i] = result.get(result.size() - 1 - i);
    }
    return ans;
  }

  // Returns number of newly turned-to-'*' characters, or 0 if can't stamp here.
  private int canStamp(char[] s, char[] t, int pos) {
    int m = s.length;
    boolean matchesAny = false;
    for (int i = 0; i < m; i++) {
      if (t[pos + i] == '*') {
        continue;
      }
      if (t[pos + i] != s[i]) {
        return 0;
      }
      matchesAny = true;
    }
    if (!matchesAny) {
      return 0;
    }
    int replaced = 0;
    for (int i = 0; i < m; i++) {
      if (t[pos + i] != '*') {
        t[pos + i] = '*';
        replaced++;
      }
    }
    return replaced;
  }
}
