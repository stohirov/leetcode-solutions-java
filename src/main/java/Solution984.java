public class Solution984 {
  public String strWithout3a3b(int a, int b) {
    StringBuilder sb = new StringBuilder();
    while (a > 0 || b > 0) {
      boolean writeA;
      int len = sb.length();
      if (len >= 2 && sb.charAt(len - 1) == sb.charAt(len - 2)) {
        writeA = sb.charAt(len - 1) == 'b';
      } else {
        writeA = a >= b;
      }
      if (writeA) {
        sb.append('a');
        a--;
      } else {
        sb.append('b');
        b--;
      }
    }
    return sb.toString();
  }
}
