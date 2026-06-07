import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution140 {
  public List<String> wordBreak(String s, List<String> wordDict) {
    Set<String> dict = new HashSet<>(wordDict);
    Map<Integer, List<String>> memo = new HashMap<>();
    return solve(s, 0, dict, memo);
  }

  private List<String> solve(String s, int start, Set<String> dict, Map<Integer, List<String>> memo) {
    if (memo.containsKey(start)) {
      return memo.get(start);
    }

    List<String> result = new ArrayList<>();

    if (start == s.length()) {
      result.add("");
      return result;
    }

    for (int end = start + 1; end <= s.length(); end++) {
      String word = s.substring(start, end);
      if (!dict.contains(word)) {
        continue;
      }
      
      for (String suffix : solve(s, end, dict, memo)) {
        result.add(suffix.isEmpty() ? word : word + " " + suffix);
      }
    }

    memo.put(start, result);
    return result;
  }
}
