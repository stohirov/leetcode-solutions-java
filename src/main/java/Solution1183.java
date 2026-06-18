import java.util.*;

public class Solution1183 {
  public int maximumNumberOfOnes(int width, int height, int sideLength, int maxOnes) {
    int n = sideLength;
    // For each cell (i, j) within one tile, count how many tiles cover that residue position.
    int[] counts = new int[n * n];
    int k = 0;
    for (int i = 0; i < n; i++) {
      for (int j = 0; j < n; j++) {
        int rows = (width - i + n - 1) / n;   // number of positions with row residue i (using width as one dimension)
        int cols = (height - j + n - 1) / n;
        counts[k++] = rows * cols;
      }
    }
    Arrays.sort(counts);
    int total = 0;
    for (int t = 0; t < maxOnes && t < counts.length; t++) {
      total += counts[counts.length - 1 - t];
    }
    return total;
  }
}
