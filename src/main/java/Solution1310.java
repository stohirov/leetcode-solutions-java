public class Solution1310 {
  public int[] xorQueries(int[] arr, int[][] queries) {
    int n = arr.length;
    int[] prefix = new int[n + 1];
    for (int i = 0; i < n; i++) {
      prefix[i + 1] = prefix[i] ^ arr[i];
    }
    int[] result = new int[queries.length];
    for (int i = 0; i < queries.length; i++) {
      int left = queries[i][0], right = queries[i][1];
      result[i] = prefix[right + 1] ^ prefix[left];
    }
    return result;
  }
}
