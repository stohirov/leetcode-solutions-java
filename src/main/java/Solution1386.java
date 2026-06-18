import java.util.HashMap;
import java.util.Map;

public class Solution1386 {
  public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
    Map<Integer, Integer> rows = new HashMap<>();
    for (int[] seat : reservedSeats) {
      int row = seat[0];
      int col = seat[1];
      if (col >= 2 && col <= 9) {
        rows.merge(row, 1 << (col - 2), (a, b) -> a | b);
      }
    }

    int left = 0b00001111;
    int middle = 0b00111100;
    int right = 0b11110000;
    int total = (n - rows.size()) * 2;
    for (int occupied : rows.values()) {
      boolean leftFree = (occupied & left) == 0;
      boolean rightFree = (occupied & right) == 0;
      boolean middleFree = (occupied & middle) == 0;
      if (leftFree && rightFree) {
        total += 2;
      } else if (leftFree || rightFree || middleFree) {
        total += 1;
      }
    }
    return total;
  }
}
