public class Solution1482 {

  public int minDays(int[] bloomDay, int m, int k) {
    long need = (long) m * k;
    if (need > bloomDay.length) {
      return -1;
    }
    int left = 1;
    int right = 0;
    for (int day : bloomDay) {
      right = Math.max(right, day);
    }
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (bouquets(bloomDay, mid, k) >= m) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

  private int bouquets(int[] bloomDay, int day, int k) {
    int made = 0;
    int run = 0;
    for (int bloom : bloomDay) {
      if (bloom <= day) {
        run++;
        if (run == k) {
          made++;
          run = 0;
        }
      } else {
        run = 0;
      }
    }
    return made;
  }
}
