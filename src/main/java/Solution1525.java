public class Solution1525 {

  public int numSplits(String s) {
    int n = s.length();
    int[] suffixDistinct = new int[n + 1];
    boolean[] seen = new boolean[26];
    int distinct = 0;
    for (int i = n - 1; i >= 0; i--) {
      int c = s.charAt(i) - 'a';
      if (!seen[c]) {
        seen[c] = true;
        distinct++;
      }
      suffixDistinct[i] = distinct;
    }

    boolean[] left = new boolean[26];
    int leftDistinct = 0;
    int count = 0;
    for (int i = 0; i < n - 1; i++) {
      int c = s.charAt(i) - 'a';
      if (!left[c]) {
        left[c] = true;
        leftDistinct++;
      }
      if (leftDistinct == suffixDistinct[i + 1]) {
        count++;
      }
    }
    return count;
  }
}
