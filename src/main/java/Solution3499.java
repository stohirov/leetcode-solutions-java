public class Solution3499 {
  public int maxActiveSectionsAfterTrade(String s) {
    int n = s.length();
    int ones = 0;
    int best = 0;
    int prevZeros = -1;
    for (int i = 0; i < n; ) {
      int j = i;
      while (j < n && s.charAt(j) == s.charAt(i)) j++;
      int len = j - i;
      if (s.charAt(i) == '1') {
        ones += len;
      } else {
        if (prevZeros > 0) best = Math.max(best, prevZeros + len);
        prevZeros = len;
      }
      i = j;
    }
    return ones + best;
  }
}
