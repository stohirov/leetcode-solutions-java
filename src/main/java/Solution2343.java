import java.util.Arrays;

public class Solution2343 {
  public int[] smallestTrimmedNumbers(String[] nums, int[][] queries) {
    int n = nums.length;
    int len = nums[0].length();
    int[] ans = new int[queries.length];

    for (int q = 0; q < queries.length; q++) {
      int k = queries[q][0];
      int start = len - queries[q][1];

      int[] idx = new int[n];
      for (int i = 0; i < n; i++) idx[i] = i;
      int[] buffer = new int[n];
      int[] count = new int[11];

      for (int pos = len - 1; pos >= start; pos--) {
        Arrays.fill(count, 0);
        for (int i = 0; i < n; i++) count[nums[idx[i]].charAt(pos) - '0' + 1]++;
        for (int d = 1; d < 11; d++) count[d] += count[d - 1];
        for (int i = 0; i < n; i++) {
          int digit = nums[idx[i]].charAt(pos) - '0';
          buffer[count[digit]++] = idx[i];
        }
        int[] tmp = idx;
        idx = buffer;
        buffer = tmp;
      }

      ans[q] = idx[k - 1];
    }
    return ans;
  }
}
