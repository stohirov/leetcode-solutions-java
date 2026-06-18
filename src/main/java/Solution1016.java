public class Solution1016 {
  public boolean queryString(String s, int n) {
    for (int i = 1; i <= n; i++) {
      String bin = Integer.toBinaryString(i);
      if (!s.contains(bin)) {
        return false;
      }
    }
    return true;
  }
}
