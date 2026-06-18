import java.util.*;

public class Solution768 {
  public int maxChunksToSorted(int[] arr) {
    int n = arr.length;
    int[] minRight = new int[n + 1];
    minRight[n] = Integer.MAX_VALUE;
    for (int i = n - 1; i >= 0; i--) {
      minRight[i] = Math.min(minRight[i + 1], arr[i]);
    }
    int chunks = 0;
    int maxLeft = Integer.MIN_VALUE;
    for (int i = 0; i < n; i++) {
      maxLeft = Math.max(maxLeft, arr[i]);
      if (maxLeft <= minRight[i + 1]) {
        chunks++;
      }
    }
    return chunks;
  }
}
