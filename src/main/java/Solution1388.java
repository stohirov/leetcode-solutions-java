public class Solution1388 {
  public int maxSizeSlices(int[] slices) {
    int n = slices.length;
    int pick = n / 3;
    int[] a = new int[n - 1];
    int[] b = new int[n - 1];
    System.arraycopy(slices, 1, a, 0, n - 1);
    System.arraycopy(slices, 0, b, 0, n - 1);
    return Math.max(linearMax(a, pick), linearMax(b, pick));
  }

  private int linearMax(int[] arr, int k) {
    int m = arr.length;
    int[][] dp = new int[m + 1][k + 1];
    for (int i = 1; i <= m; i++) {
      for (int j = 1; j <= k; j++) {
        int take = (i >= 2 ? dp[i - 2][j - 1] : 0) + arr[i - 1];
        dp[i][j] = Math.max(dp[i - 1][j], take);
      }
    }
    return dp[m][k];
  }
}
