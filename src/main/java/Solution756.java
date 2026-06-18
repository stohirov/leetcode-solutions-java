import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution756 {

  public boolean pyramidTransition(String bottom, List<String> allowed) {
    Map<String, Set<Character>> map = new HashMap<>();
    for (String a : allowed) {
      map.computeIfAbsent(a.substring(0, 2), _ -> new HashSet<>()).add(a.charAt(2));
    }
    return build(bottom, new StringBuilder(), map);
  }

  private boolean build(String row, StringBuilder next, Map<String, Set<Character>> map) {
    if (row.length() == 1) {
      return true;
    }
    if (next.length() == row.length() - 1) {
      return build(next.toString(), new StringBuilder(), map);
    }
    int i = next.length();
    String key = row.substring(i, i + 2);
    Set<Character> options = map.get(key);
    if (options == null) {
      return false;
    }
    for (char c : options) {
      next.append(c);
      if (build(row, next, map)) {
        return true;
      }
      next.deleteCharAt(next.length() - 1);
    }
    return false;
  }
}
