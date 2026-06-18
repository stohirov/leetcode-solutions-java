public class Solution875 {

  public int minEatingSpeed(int[] piles, int h) {
    int left = 1;
    int right = 0;
    for (int pile : piles) {
      right = Math.max(right, pile);
    }
    while (left < right) {
      int mid = left + (right - left) / 2;
      if (hoursNeeded(piles, mid) <= h) {
        right = mid;
      } else {
        left = mid + 1;
      }
    }
    return left;
  }

  private long hoursNeeded(int[] piles, int speed) {
    long hours = 0;
    for (int pile : piles) {
      hours += (pile + speed - 1) / speed;
    }
    return hours;
  }
}
