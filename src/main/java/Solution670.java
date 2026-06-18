public class Solution670 {

  public int maximumSwap(int num) {
    char[] digits = Integer.toString(num).toCharArray();
    int n = digits.length;
    int[] lastIndex = new int[10];
    for (int i = 0; i < n; i++) {
      lastIndex[digits[i] - '0'] = i;
    }
    for (int i = 0; i < n; i++) {
      for (int d = 9; d > digits[i] - '0'; d--) {
        if (lastIndex[d] > i) {
          char tmp = digits[i];
          digits[i] = digits[lastIndex[d]];
          digits[lastIndex[d]] = tmp;
          return Integer.parseInt(new String(digits));
        }
      }
    }
    return num;
  }
}
