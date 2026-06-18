import java.util.*;

public class Solution646 {

  public int findLongestChain(int[][] pairs) {
    Arrays.sort(pairs, Comparator.comparingInt(a -> a[1]));
    int count = 0;
    int curEnd = Integer.MIN_VALUE;
    for (int[] pair : pairs) {
      if (pair[0] > curEnd) {
        count++;
        curEnd = pair[1];
      }
    }
    return count;
  }
}
