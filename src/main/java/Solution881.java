import java.util.*;

public class Solution881 {
  public int numRescueBoats(int[] people, int limit) {
    Arrays.sort(people);
    int lo = 0;
    int hi = people.length - 1;
    int boats = 0;
    while (lo <= hi) {
      if (people[lo] + people[hi] <= limit) {
        lo++;
      }
      hi--;
      boats++;
    }
    return boats;
  }
}
