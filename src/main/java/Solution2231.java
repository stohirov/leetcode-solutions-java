public class Solution2231 {
  public int largestInteger(int num) {
    char[] digits = String.valueOf(num).toCharArray();
    int n = digits.length;
    for (int i = 0; i < n; i++) {
      for (int j = i + 1; j < n; j++) {
        if (((digits[i] - '0') % 2) == ((digits[j] - '0') % 2) && digits[j] > digits[i]) {
          char tmp = digits[i];
          digits[i] = digits[j];
          digits[j] = tmp;
        }
      }
    }
    return Integer.parseInt(new String(digits));
  }
}
