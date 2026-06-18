public class Solution738 {

  public int monotoneIncreasingDigits(int n) {
    char[] digits = Integer.toString(n).toCharArray();
    int len = digits.length;
    int mark = len;
    for (int i = len - 1; i > 0; i--) {
      if (digits[i - 1] > digits[i]) {
        digits[i - 1]--;
        mark = i;
      }
    }
    for (int i = mark; i < len; i++) {
      digits[i] = '9';
    }
    return Integer.parseInt(new String(digits));
  }
}
