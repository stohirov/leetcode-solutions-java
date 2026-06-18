import java.util.List;

public class Solution1434 {
  private static final int MOD = 1_000_000_007;

  public int numberWays(List<List<Integer>> hats) {
    int people = hats.size();
    List<Integer>[] hatToPeople = new List[41];
    for (int h = 1; h <= 40; h++) {
      hatToPeople[h] = new java.util.ArrayList<>();
    }
    for (int p = 0; p < people; p++) {
      for (int hat : hats.get(p)) {
        hatToPeople[hat].add(p);
      }
    }
    int fullMask = (1 << people) - 1;
    long[] dp = new long[1 << people];
    dp[0] = 1;
    for (int hat = 1; hat <= 40; hat++) {
      long[] next = dp.clone();
      for (int mask = 0; mask <= fullMask; mask++) {
        if (dp[mask] == 0) continue;
        for (int p : hatToPeople[hat]) {
          if ((mask & (1 << p)) != 0) continue;
          int nm = mask | (1 << p);
          next[nm] = (next[nm] + dp[mask]) % MOD;
        }
      }
      dp = next;
    }
    return (int) dp[fullMask];
  }
}
