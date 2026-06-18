public class Solution868 {

  public int binaryGap(int n) {
    int last = -1;
    int maxGap = 0;
    for (int i = 0; n > 0; i++, n >>= 1) {
      if ((n & 1) == 1) {
        if (last >= 0) {
          maxGap = Math.max(maxGap, i - last);
        }
        last = i;
      }
    }
    return maxGap;
  }
}
