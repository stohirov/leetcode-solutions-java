public class Solution13 {
  public int romanToInt(String s) {
    int total = 0;
    int prev = 0;

    for (int i = s.length() - 1; i >= 0; i--) {
      int value = valueOf(s.charAt(i));
      if (value < prev) {
        total -= value;
      } else {
        total += value;
      }
      prev = value;
    }

    return total;
  }

  private int valueOf(char c) {
    return switch (c) {
      case 'I' -> 1;
      case 'V' -> 5;
      case 'X' -> 10;
      case 'L' -> 50;
      case 'C' -> 100;
      case 'D' -> 500;
      case 'M' -> 1000;
      default -> 0;
    };
  }
}
