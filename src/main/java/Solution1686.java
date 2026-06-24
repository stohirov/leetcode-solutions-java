import java.util.Arrays;

public class Solution1686 {
  public int stoneGameVI(int[] aliceValues, int[] bobValues) {
    int n = aliceValues.length;
    Integer[] idx = new Integer[n];
    for (int i = 0; i < n; i++) idx[i] = i;
    Arrays.sort(idx, (a, b) ->
        (bobValues[b] + aliceValues[b]) - (bobValues[a] + aliceValues[a]));

    long alice = 0, bob = 0;
    for (int turn = 0; turn < n; turn++) {
      int i = idx[turn];
      if (turn % 2 == 0) alice += aliceValues[i];
      else bob += bobValues[i];
    }
    return Long.compare(alice, bob);
  }
}
