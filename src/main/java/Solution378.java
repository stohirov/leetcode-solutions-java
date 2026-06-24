public class Solution378 {
  public int kthSmallest(int[][] matrix, int k) {
    int n = matrix.length;
    int lo = matrix[0][0], hi = matrix[n - 1][n - 1];
    while (lo < hi) {
      int mid = lo + (hi - lo) / 2;
      if (countLessEqual(matrix, mid, n) < k) {
        lo = mid + 1;
      } else {
        hi = mid;
      }
    }
    return lo;
  }

  private int countLessEqual(int[][] matrix, int target, int n) {
    int count = 0;
    int row = n - 1, col = 0;
    while (row >= 0 && col < n) {
      if (matrix[row][col] <= target) {
        count += row + 1;
        col++;
      } else {
        row--;
      }
    }
    return count;
  }
}
