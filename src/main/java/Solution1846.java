import java.util.Arrays;

public class Solution1846 {

  public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
    Arrays.sort(arr);
    int prev = 0;
    for (int value : arr) {
      prev = Math.min(prev + 1, value);
    }
    return prev;
  }
}
