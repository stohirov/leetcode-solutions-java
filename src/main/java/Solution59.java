public class Solution59 {
  public int[][] generateMatrix(int n) {
    int[][] ans = new int[n][n];
    int top = 0, left = 0;
    int bottom = n - 1, right = n - 1;

    int count = 1;

    while (count <= n * n) {

      for (int col = left; col <= right; col++) {
        ans[top][col] = count++;
      }
      top++;

      for (int row = top; row <= bottom; row++) {
        ans[row][right] = count++;
      }
      right--;

      if (top <= bottom) {
        for (int col = right; col >= left; col--) {
          ans[bottom][col] = count++;
        }
        bottom--;
      }

      if (left <= right) {
        for (int row = bottom; row >= top; row--) {
          ans[row][left] = count++;
        }
        left++;
      }
    }

    return ans;
  }
}
