import java.util.HashSet;
import java.util.Set;

public class Solution672 {

  public int flipLights(int n, int presses) {
    n = Math.min(n, 6);
    Set<Integer> seen = new HashSet<>();
    int all = ((1 << n) - 1);
    int[] masks = new int[4];
    for (int i = 0; i < n; i++) {
      int pos = i + 1;
      masks[0] |= (1 << i);
      if (pos % 2 == 0) {
        masks[1] |= (1 << i);
      }
      if (pos % 2 == 1) {
        masks[2] |= (1 << i);
      }
      if (pos % 3 == 1) {
        masks[3] |= (1 << i);
      }
    }
    masks[0] &= all;
    for (int combo = 0; combo < 16; combo++) {
      int count = Integer.bitCount(combo);
      if (count > presses || (presses - count) % 2 != 0) {
        continue;
      }
      int state = 0;
      for (int b = 0; b < 4; b++) {
        if ((combo & (1 << b)) != 0) {
          state ^= masks[b];
        }
      }
      seen.add(state);
    }
    return seen.size();
  }
}
