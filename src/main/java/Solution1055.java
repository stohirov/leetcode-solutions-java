public class Solution1055 {
  public int shortestWay(String source, String target) {
    boolean[] present = new boolean[26];
    for (char c : source.toCharArray()) present[c - 'a'] = true;
    for (char c : target.toCharArray()) {
      if (!present[c - 'a']) return -1;
    }
    int count = 0, i = 0, n = source.length(), m = target.length();
    while (i < m) {
      int prev = i;
      for (int j = 0; j < n && i < m; j++) {
        if (source.charAt(j) == target.charAt(i)) i++;
      }
      count++;
      if (i == prev) return -1; // safety, should not happen given check above
    }
    return count;
  }
}
