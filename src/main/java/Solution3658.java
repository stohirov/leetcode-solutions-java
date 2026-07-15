public class Solution3658 {
  public int gcdOfOddEvenSums(int n) {
    int odd = 1;
    int even = 2;
    int evenSum = 0;
    int oddSum = 0;

    for (int count = 0; count < n; count++) {
      oddSum += odd;
      evenSum += even;

      even+=2;
      odd+=2;
    }

    int gcd = 0;

    for (int i = 1; i <= oddSum; i++) {
      if (oddSum % i == 0 && evenSum % i == 0) {
        gcd = i;
      }
    }

    return gcd;
  }
}
