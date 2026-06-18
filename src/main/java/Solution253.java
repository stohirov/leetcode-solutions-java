import java.util.*;

public class Solution253 {
  public int minMeetingRooms(int[][] intervals) {
    int n = intervals.length;
    if (n == 0) {
      return 0;
    }
    int[] starts = new int[n];
    int[] ends = new int[n];
    for (int i = 0; i < n; i++) {
      starts[i] = intervals[i][0];
      ends[i] = intervals[i][1];
    }
    Arrays.sort(starts);
    Arrays.sort(ends);
    int rooms = 0, maxRooms = 0, j = 0;
    for (int i = 0; i < n; i++) {
      while (j < n && ends[j] <= starts[i]) {
        rooms--;
        j++;
      }
      rooms++;
      maxRooms = Math.max(maxRooms, rooms);
    }
    return maxRooms;
  }
}
