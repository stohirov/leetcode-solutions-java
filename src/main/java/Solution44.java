public class Solution44 {
  public boolean isMatch(String s, String p) {
    int sLen = s.length(), pLen = p.length();
    boolean[][] dp = new boolean[sLen + 1][pLen + 1];
    dp[0][0] = true;
    for (int j = 1; j <= pLen; j++) {
      if (p.charAt(j - 1) == '*') {
        dp[0][j] = dp[0][j - 1];
      }
    }
    for (int i = 1; i <= sLen; i++) {
      for (int j = 1; j <= pLen; j++) {
        char pc = p.charAt(j - 1);
        if (pc == '*') {
          dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
        } else if (pc == '?' || pc == s.charAt(i - 1)) {
          dp[i][j] = dp[i - 1][j - 1];
        }
      }
    }
    return dp[sLen][pLen];
  }
}
