import java.util.*;

public class Solution948 {
  public int bagOfTokensScore(int[] tokens, int power) {
    Arrays.sort(tokens);
    int lo = 0, hi = tokens.length - 1;
    int score = 0, best = 0;
    while (lo <= hi) {
      if (power >= tokens[lo]) {
        power -= tokens[lo];
        lo++;
        score++;
        best = Math.max(best, score);
      } else if (score > 0) {
        power += tokens[hi];
        hi--;
        score--;
      } else {
        break;
      }
    }
    return best;
  }
}
