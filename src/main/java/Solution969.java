import java.util.*;

public class Solution969 {
  public List<Integer> pancakeSort(int[] arr) {
    List<Integer> res = new ArrayList<>();
    int n = arr.length;
    for (int size = n; size > 1; size--) {
      int maxIdx = 0;
      for (int i = 1; i < size; i++) {
        if (arr[i] > arr[maxIdx]) maxIdx = i;
      }
      if (maxIdx == size - 1) continue;
      if (maxIdx != 0) {
        flip(arr, maxIdx);
        res.add(maxIdx + 1);
      }
      flip(arr, size - 1);
      res.add(size);
    }
    return res;
  }

  private void flip(int[] arr, int k) {
    int lo = 0, hi = k;
    while (lo < hi) {
      int t = arr[lo];
      arr[lo] = arr[hi];
      arr[hi] = t;
      lo++;
      hi--;
    }
  }
}
