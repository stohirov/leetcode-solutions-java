public class Solution3345 {

  public int smallestNumber(int n, int t) {
    for (int candidate = n; ; candidate++) {
      if (digitProduct(candidate) % t == 0) {
        return candidate;
      }
    }
  }

  private int digitProduct(int value) {
    int product = 1;
    while (value > 0) {
      product *= value % 10;
      value /= 10;
    }
    return product;
  }

}
