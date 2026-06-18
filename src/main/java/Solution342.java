public class Solution342 {

  public boolean isPowerOfFour(int n) {
    // Power of two, and the single set bit is in an even position.
    return n > 0 && (n & (n - 1)) == 0 && (n & 0x55555555) != 0;
  }
}
