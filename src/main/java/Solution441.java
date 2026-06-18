public class Solution441 {

  public int arrangeCoins(int n) {
    long left = 1;
    long right = n;
    long answer = 0;
    while (left <= right) {
      long mid = left + (right - left) / 2;
      long coins = mid * (mid + 1) / 2;
      if (coins <= n) {
        answer = mid;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return (int) answer;
  }
}
