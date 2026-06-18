public class Solution410 {

  public int splitArray(int[] nums, int k) {
    int left = 0;
    long right = 0;
    for (int num : nums) {
      left = Math.max(left, num);
      right += num;
    }
    int answer = (int) right;
    long lo = left;
    while (lo <= right) {
      long mid = lo + (right - lo) / 2;
      if (piecesNeeded(nums, mid) <= k) {
        answer = (int) mid;
        right = mid - 1;
      } else {
        lo = mid + 1;
      }
    }
    return answer;
  }

  private int piecesNeeded(int[] nums, long cap) {
    int pieces = 1;
    long sum = 0;
    for (int num : nums) {
      if (sum + num > cap) {
        pieces++;
        sum = 0;
      }
      sum += num;
    }
    return pieces;
  }
}
