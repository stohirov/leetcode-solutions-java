public class Solution1011 {

  public int shipWithinDays(int[] weights, int days) {
    int left = 0;
    int right = 0;
    for (int weight : weights) {
      left = Math.max(left, weight);
      right += weight;
    }
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (daysNeeded(weights, mid) <= days) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

  private int daysNeeded(int[] weights, int capacity) {
    int days = 1;
    int load = 0;
    for (int weight : weights) {
      if (load + weight > capacity) {
        days++;
        load = 0;
      }
      load += weight;
    }
    return days;
  }
}
