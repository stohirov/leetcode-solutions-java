public class Solution625 {

  public int smallestFactorization(int num) {
    if (num == 1) {
      return 1;
    }
    long result = 0;
    long base = 1;
    for (int factor = 9; factor >= 2; factor--) {
      while (num % factor == 0) {
        num /= factor;
        result += base * factor;
        base *= 10;
        if (result > Integer.MAX_VALUE) {
          return 0;
        }
      }
    }
    return num == 1 ? (int) result : 0;
  }
}
