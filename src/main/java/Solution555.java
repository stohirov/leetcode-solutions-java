import java.util.*;

public class Solution555 {
  public String splitLoopedString(String[] strs) {
    int n = strs.length;
    // For each string, pick the lexicographically larger orientation as the base
    // contribution when it is NOT the cut string.
    String[] best = new String[n];
    for (int i = 0; i < n; i++) {
      String a = strs[i];
      String b = new StringBuilder(a).reverse().toString();
      best[i] = a.compareTo(b) >= 0 ? a : b;
    }

    String result = "";
    for (int i = 0; i < n; i++) {
      String original = strs[i];
      String reversed = new StringBuilder(original).reverse().toString();
      for (String cur : new String[] {original, reversed}) {
        // Try every cut point within cur. The piece after cut becomes the start.
        // Build the "rest" string from best[] of the other strings.
        StringBuilder rest = new StringBuilder();
        for (int k = i + 1; k < n; k++) rest.append(best[k]);
        for (int k = 0; k < i; k++) rest.append(best[k]);
        String restStr = rest.toString();
        for (int j = 0; j < cur.length(); j++) {
          // candidate: cur[j..] + rest + cur[0..j)
          // compare without building full string repeatedly when possible,
          // but build for correctness/clarity
          StringBuilder cand = new StringBuilder();
          cand.append(cur, j, cur.length());
          cand.append(restStr);
          cand.append(cur, 0, j);
          String c = cand.toString();
          if (c.compareTo(result) > 0) result = c;
        }
      }
    }
    return result;
  }
}
