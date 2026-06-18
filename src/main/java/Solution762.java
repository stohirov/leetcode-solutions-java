public class Solution762 {

  public int countPrimeSetBits(int left, int right) {
    int count = 0;
    for (int n = left; n <= right; n++) {
      if (isPrime(Integer.bitCount(n))) {
        count++;
      }
    }
    return count;
  }

  private boolean isPrime(int x) {
    if (x < 2) {
      return false;
    }
    for (int i = 2; i * i <= x; i++) {
      if (x % i == 0) {
        return false;
      }
    }
    return true;
  }
}
