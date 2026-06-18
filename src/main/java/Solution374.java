public class Solution374 extends GuessGame374 {

  public int guessNumber(int n) {
    int left = 1;
    int right = n;
    while (left <= right) {
      int mid = left + (right - left) / 2;
      int result = guess(mid);
      if (result == 0) {
        return mid;
      } else if (result < 0) {
        right = mid - 1;
      } else {
        left = mid + 1;
      }
    }
    return -1;
  }
}

class GuessGame374 {
  private int pick = 1;

  void setPick(int pick) {
    this.pick = pick;
  }

  protected int guess(int num) {
    return Integer.compare(pick, num);
  }
}
