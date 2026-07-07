public class Solution3754 {

  public long sumAndMultiply(int n) {
    String numStr = String.valueOf(n);
    long x = 0, sum = 0;
    while (!numStr.isEmpty()) {
      long digit = numStr.charAt(0) - '0';
      if (digit != 0) {
        x = x * 10 + digit;
        sum += digit;
      }

      numStr = numStr.substring(1);
    }

    return sum * x;
  }
}
