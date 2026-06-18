import java.util.*;

public class Solution1029 {
  public int twoCitySchedule(int[][] costs) {
    Arrays.sort(costs, Comparator.comparingInt(x -> (x[0] - x[1])));
    int n = costs.length / 2;
    int total = 0;
    for (int i = 0; i < costs.length; i++) {
      total += i < n ? costs[i][0] : costs[i][1];
    }
    return total;
  }
}
