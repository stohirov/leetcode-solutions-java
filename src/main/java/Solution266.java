import java.util.HashSet;
import java.util.Set;

public class Solution266 {

  public boolean canPermutePalindrome(String s) {
    Set<Character> odd = new HashSet<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (!odd.add(c)) {
        odd.remove(c);
      }
    }
    return odd.size() <= 1;
  }
}
