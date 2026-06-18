public class Solution405 {
  public String toHex(int num) {
    if (num == 0) return "0";
    char[] digits = "0123456789abcdef".toCharArray();
    StringBuilder sb = new StringBuilder();
    long n = num & 0xFFFFFFFFL;
    while (n != 0) {
      sb.append(digits[(int) (n & 0xF)]);
      n >>= 4;
    }
    return sb.reverse().toString();
  }
}
