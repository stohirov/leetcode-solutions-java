import java.util.Arrays;
import java.util.Comparator;

public class Solution1356 {
  public int[] sortByBits(int[] arr) {
    Integer[] boxed = new Integer[arr.length];
    for (int i = 0; i < arr.length; i++) {
      boxed[i] = arr[i];
    }
    Arrays.sort(boxed, Comparator
        .comparingInt(Integer::bitCount)
        .thenComparingInt(x -> x));
    int[] result = new int[arr.length];
    for (int i = 0; i < arr.length; i++) {
      result[i] = boxed[i];
    }
    return result;
  }
}
