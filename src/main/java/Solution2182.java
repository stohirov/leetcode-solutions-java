public class Solution2182 {

  public String repeatLimitedString(String s, int repeatLimit) {
    int[] count = new int[26];
    for (char c : s.toCharArray()) {
      count[c - 'a']++;
    }

    StringBuilder sb = new StringBuilder();
    int i = 25;
    while (i >= 0) {
      if (count[i] == 0) {
        i--;
        continue;
      }
      int use = Math.min(count[i], repeatLimit);
      for (int j = 0; j < use; j++) {
        sb.append((char) ('a' + i));
      }
      count[i] -= use;

      if (count[i] > 0) {
        int j = i - 1;
        while (j >= 0 && count[j] == 0) {
          j--;
        }
        if (j < 0) {
          break;
        }
        sb.append((char) ('a' + j));
        count[j]--;
      } else {
        i--;
      }
    }
    return sb.toString();
  }
}
