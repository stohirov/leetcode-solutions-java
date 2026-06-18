public class Solution1147 {
  public int longestDecomposition(String text) {
    int n = text.length();
    int count = 0;
    int i = 0, j = n - 1;
    int startI = 0, startJ = n - 1;
    while (i <= j) {
      int len = i - startI + 1;
      if (startJ - len + 1 > i &&
          text.substring(startI, i + 1).equals(text.substring(startJ - len + 1, startJ + 1))) {
        count += 2;
        i++;
        startI = i;
        startJ = startJ - len;
        j = startJ;
      } else {
        i++;
        j--;
      }
    }
    if (startI <= startJ) count++;
    return count;
  }
}
