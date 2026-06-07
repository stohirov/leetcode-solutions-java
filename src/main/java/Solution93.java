import java.util.ArrayList;
import java.util.List;

public class Solution93 {
  public List<String> restoreIpAddresses(String s) {
    List<String> result = new ArrayList<>();
    backtrack(s, 0, 0, new StringBuilder(), result);
    return result;
  }

  private void backtrack(String s, int start, int parts, StringBuilder current,
                         List<String> result) {
    if (parts == 4) {
      if (start == s.length()) {
        result.add(current.substring(0, current.length() - 1));
      }
      return;
    }

    for (int len = 1; len <= 3 && start + len <= s.length(); len++) {
      String segment = s.substring(start, start + len);
      if (!isValid(segment)) {
        continue;
      }
      int mark = current.length();
      current.append(segment).append('.');
      backtrack(s, start + len, parts + 1, current, result);
      current.setLength(mark);
    }
  }

  private boolean isValid(String segment) {
    if (segment.length() > 1 && segment.charAt(0) == '0') {
      return false;
    }
    return Integer.parseInt(segment) <= 255;
  }
}
