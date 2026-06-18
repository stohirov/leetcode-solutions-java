import java.util.*;

public class Solution1196 {
  public int maxNumberOfApples(int[] weight) {
    Arrays.sort(weight);
    int total = 0, count = 0;
    for (int w : weight) {
      if (total + w <= 5000) {
        total += w;
        count++;
      } else {
        break;
      }
    }
    return count;
  }
}
