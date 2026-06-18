public class Solution1007 {
  public int minDominoRotations(int[] tops, int[] bottoms) {
    int n = tops.length;
    int res = check(tops[0], tops, bottoms, n);
    if (res != -1) return res;
    return check(bottoms[0], tops, bottoms, n);
  }

  private int check(int target, int[] tops, int[] bottoms, int n) {
    int rotTop = 0, rotBottom = 0;
    for (int i = 0; i < n; i++) {
      if (tops[i] != target && bottoms[i] != target) return -1;
      if (tops[i] != target) rotTop++;
      if (bottoms[i] != target) rotBottom++;
    }
    return Math.min(rotTop, rotBottom);
  }
}
