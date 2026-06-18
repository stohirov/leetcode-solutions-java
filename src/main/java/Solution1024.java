public class Solution1024 {
  public int videoStitching(int[][] clips, int time) {
    int[] maxReach = new int[time + 1];
    for (int[] c : clips) {
      if (c[0] <= time) {
        maxReach[c[0]] = Math.max(maxReach[c[0]], c[1]);
      }
    }
    int clipsUsed = 0, curEnd = 0, nextEnd = 0;
    for (int i = 0; i < time; i++) {
      nextEnd = Math.max(nextEnd, maxReach[i]);
      if (i == curEnd) {
        if (nextEnd <= i) return -1;
        clipsUsed++;
        curEnd = nextEnd;
      }
    }
    return clipsUsed;
  }
}
