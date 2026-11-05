import java.util.Arrays;

public class Solution1385 {
  public int findTheDistanceValue(int[] arr1, int[] arr2, int d) {
    Arrays.sort(arr2);
    int count = 0;
    for (int value : arr1) {
      int index = lowerBound(arr2, value - d);
      if (index == arr2.length || arr2[index] > value + d) {
        count++;
      }
    }
    return count;
  }

  private int lowerBound(int[] sorted, int target) {
    int low = 0;
    int high = sorted.length;
    while (low < high) {
      int mid = low + (high - low) / 2;
      if (sorted[mid] < target) {
        low = mid + 1;
      } else {
        high = mid;
      }
    }
    return low;
  }
}
