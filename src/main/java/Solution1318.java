public class Solution1318 {
  public int minFlips(int a, int b, int c) {
    int flips = 0;
    for (int i = 0; i < 31; i++) {
      int ai = (a >> i) & 1;
      int bi = (b >> i) & 1;
      int ci = (c >> i) & 1;
      if (ci == 0) {
        flips += ai + bi;
      } else {
        if (ai == 0 && bi == 0) {
          flips += 1;
        }
      }
    }
    return flips;
  }
}
