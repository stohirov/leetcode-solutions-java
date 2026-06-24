public class Solution683 {
  public int kEmptySlots(int[] bulbs, int k) {
    int n = bulbs.length;
    // days[pos] = day on which bulb at position pos turns on
    int[] days = new int[n];
    for (int i = 0; i < n; i++) days[bulbs[i] - 1] = i + 1;
    // sliding window of width k+2: endpoints must bloom before the k interior bulbs
    int res = Integer.MAX_VALUE;
    // iterate over days in order; use a window approach over positions
    int left = 0, right = k + 1;
    while (right < n) {
      boolean valid = true;
      for (int i = left + 1; i < right; i++) {
        if (days[i] < days[left] || days[i] < days[right]) {
          left = i;
          right = i + k + 1;
          valid = false;
          break;
        }
      }
      if (valid) {
        res = Math.min(res, Math.max(days[left], days[right]));
        left = right;
        right = left + k + 1;
      }
    }
    return res == Integer.MAX_VALUE ? -1 : res;
  }
}
