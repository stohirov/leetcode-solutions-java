public class Solution3302 {

  public int[] validSequence(String word1, String word2) {
    int n = word1.length();
    int m = word2.length();

    int[] matchable = new int[n + 1];
    for (int i = n - 1; i >= 0; i--) {
      int taken = matchable[i + 1];
      if (taken < m && word1.charAt(i) == word2.charAt(m - 1 - taken)) {
        taken++;
      }
      matchable[i] = taken;
    }

    int[] answer = new int[m];
    int j = 0;
    boolean changed = false;
    for (int i = 0; i < n && j < m; i++) {
      if (word1.charAt(i) == word2.charAt(j)) {
        answer[j++] = i;
      } else if (!changed && matchable[i + 1] >= m - j - 1) {
        answer[j++] = i;
        changed = true;
      }
    }

    return j == m ? answer : new int[0];
  }

}
