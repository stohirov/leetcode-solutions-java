import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Solution187 {

  public List<String> findRepeatedDnaSequences(String s) {
    List<String> result = new ArrayList<>();
    if (s.length() < 10) {
      return result;
    }
    Set<String> seen = new HashSet<>();
    Set<String> added = new HashSet<>();
    for (int i = 0; i + 10 <= s.length(); i++) {
      String sub = s.substring(i, i + 10);
      if (!seen.add(sub) && added.add(sub)) {
        result.add(sub);
      }
    }
    return result;
  }
}
