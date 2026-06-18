import java.util.*;

public class Solution781 {
  public int numRabbits(int[] answers) {
    Map<Integer, Integer> count = new HashMap<>();
    for (int a : answers) {
      count.merge(a, 1, Integer::sum);
    }
    int total = 0;
    for (Map.Entry<Integer, Integer> e : count.entrySet()) {
      int answer = e.getKey();
      int num = e.getValue();
      int groupSize = answer + 1;
      int groups = (num + groupSize - 1) / groupSize;
      total += groups * groupSize;
    }
    return total;
  }
}
