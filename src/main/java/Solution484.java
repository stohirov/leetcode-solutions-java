import java.util.*;

public class Solution484 {
  public int[] findPermutation(String s) {
    int n = s.length() + 1;
    int[] result = new int[n];
    for (int i = 0; i < n; i++) result[i] = i + 1;
    int i = 0;
    while (i < s.length()) {
      if (s.charAt(i) == 'D') {
        int j = i;
        while (j < s.length() && s.charAt(j) == 'D') j++;
        // reverse result[i .. j] inclusive
        reverse(result, i, j);
        i = j;
      } else {
        i++;
      }
    }
    return result;
  }

  private void reverse(int[] arr, int lo, int hi) {
    while (lo < hi) {
      int t = arr[lo];
      arr[lo] = arr[hi];
      arr[hi] = t;
      lo++;
      hi--;
    }
  }
}
