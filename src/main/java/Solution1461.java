import java.util.HashSet;
import java.util.Set;

public class Solution1461 {

  public boolean hasAllCodes(String s, int k) {
    int need = 1 << k;
    if (s.length() < need + k - 1) {
      return false;
    }
    Set<Integer> seen = new HashSet<>();
    int mask = (1 << k) - 1;
    int cur = 0;
    for (int i = 0; i < s.length(); i++) {
      cur = ((cur << 1) | (s.charAt(i) - '0')) & mask;
      if (i >= k - 1) {
        if (seen.add(cur) && seen.size() == need) {
          return true;
        }
      }
    }
    return seen.size() == need;
  }
}
