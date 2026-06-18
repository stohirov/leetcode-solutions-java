public class Solution942 {
  public int[] diStringMatch(String s) {
    int n = s.length();
    int[] result = new int[n + 1];
    int lo = 0;
    int hi = n;
    for (int i = 0; i < n; i++) {
      if (s.charAt(i) == 'I') {
        result[i] = lo++;
      } else {
        result[i] = hi--;
      }
    }
    result[n] = lo;
    return result;
  }
}
