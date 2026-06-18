public class Solution1404 {
  public int numSteps(String s) {
    int steps = 0;
    int carry = 0;
    for (int i = s.length() - 1; i > 0; i--) {
      int digit = (s.charAt(i) - '0') + carry;
      if (digit == 1) {
        carry = 1;
        steps += 2;
      } else {
        carry = digit >> 1;
        steps += 1;
      }
    }

    steps += carry;
    return steps;
  }
}
