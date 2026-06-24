public class Solution264 {
  public int nthUglyNumber(int n) {
    int[] ugly = new int[n];
    ugly[0] = 1;
    int p2 = 0, p3 = 0, p5 = 0;
    for (int i = 1; i < n; i++) {
      int next2 = ugly[p2] * 2;
      int next3 = ugly[p3] * 3;
      int next5 = ugly[p5] * 5;
      int next = Math.min(next2, Math.min(next3, next5));
      ugly[i] = next;
      if (next == next2) p2++;
      if (next == next3) p3++;
      if (next == next5) p5++;
    }
    return ugly[n - 1];
  }
}
