public class Solution955 {
  public int minDeletionSize(String[] strs) {
    int n = strs.length, m = strs[0].length();
    int deletions = 0;
    boolean[] sorted = new boolean[n - 1];
    for (int c = 0; c < m; c++) {
      boolean keep = true;
      for (int i = 0; i < n - 1; i++) {
        if (!sorted[i] && strs[i].charAt(c) > strs[i + 1].charAt(c)) {
          keep = false;
          break;
        }
      }
      if (!keep) {
        deletions++;
      } else {
        for (int i = 0; i < n - 1; i++) {
          if (strs[i].charAt(c) < strs[i + 1].charAt(c)) sorted[i] = true;
        }
      }
    }
    return deletions;
  }
}
