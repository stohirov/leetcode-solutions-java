import java.util.ArrayList;
import java.util.List;

public class Solution1239 {
  public int maxLength(List<String> arr) {
    List<Integer> masks = new ArrayList<>();
    masks.add(0);
    int best = 0;
    for (String s : arr) {
      int mask = 0;
      boolean valid = true;
      for (char c : s.toCharArray()) {
        int bit = 1 << (c - 'a');
        if ((mask & bit) != 0) {
          valid = false;
          break;
        }
        mask |= bit;
      }
      if (!valid) {
        continue;
      }
      int size = masks.size();
      for (int i = 0; i < size; i++) {
        int existing = masks.get(i);
        if ((existing & mask) == 0) {
          int combined = existing | mask;
          masks.add(combined);
          best = Math.max(best, Integer.bitCount(combined));
        }
      }
    }
    return best;
  }
}
