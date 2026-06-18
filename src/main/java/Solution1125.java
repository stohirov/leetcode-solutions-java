import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution1125 {
  public int[] smallestSufficientTeam(String[] reqSkills, List<List<String>> people) {
    int n = reqSkills.length;
    Map<String, Integer> skillIndex = new HashMap<>();
    for (int i = 0; i < n; i++) {
      skillIndex.put(reqSkills[i], i);
    }

    int full = 1 << n;

    int[][] dp = new int[full][];
    dp[0] = new int[0];
    for (int p = 0; p < people.size(); p++) {
      int pSkill = 0;
      for (String s : people.get(p)) {
        Integer idx = skillIndex.get(s);
        if (idx != null) {
          pSkill |= (1 << idx);
        }
      }

      if (pSkill == 0) {
        continue;
      }

      for (int mask = 0; mask < full; mask++) {
        if (dp[mask] == null) {
          continue;
        }

        int newMask = mask | pSkill;

        if (newMask == mask) {
          continue;
        }

        if (dp[newMask] == null || dp[newMask].length > dp[mask].length + 1) {
          int[] team = Arrays.copyOf(dp[mask], dp[mask].length + 1);
          team[team.length - 1] = p;
          dp[newMask] = team;
        }
      }
    }
    return dp[full - 1];
  }
}
