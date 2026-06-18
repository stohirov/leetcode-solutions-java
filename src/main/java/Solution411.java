import java.util.ArrayList;
import java.util.List;

public class Solution411 {
  private int bestLen;
  private String bestAbbr;

  public String minAbbreviation(String target, String[] dictionary) {
    int n = target.length();
    List<Integer> masks = new ArrayList<>();
    for (String word : dictionary) {
      if (word.length() != n) continue;
      int mask = 0;
      for (int i = 0; i < n; i++) {
        if (word.charAt(i) != target.charAt(i)) {
          mask |= (1 << i);
        }
      }
      masks.add(mask);
    }
    if (masks.isEmpty()) {
      return n == 0 ? "" : String.valueOf(n);
    }
    bestLen = Integer.MAX_VALUE;
    bestAbbr = target;
    // Enumerate all subsets of positions to keep as literals (abbreviation pattern).
    // A pattern is valid if for every dictionary mask, at least one kept position
    // differs (i.e., kept & mask != 0).
    for (int keep = 0; keep < (1 << n); keep++) {
      boolean valid = true;
      for (int m : masks) {
        if ((keep & m) == 0) {
          valid = false;
          break;
        }
      }
      if (!valid) continue;
      String abbr = buildAbbr(target, keep);
      int len = abbrLength(abbr);
      if (len < bestLen) {
        bestLen = len;
        bestAbbr = abbr;
      }
    }
    return bestAbbr;
  }

  private String buildAbbr(String target, int keep) {
    int n = target.length();
    StringBuilder sb = new StringBuilder();
    int i = 0;
    while (i < n) {
      if ((keep & (1 << i)) != 0) {
        sb.append(target.charAt(i));
        i++;
      } else {
        int j = i;
        while (j < n && (keep & (1 << j)) == 0) j++;
        sb.append(j - i);
        i = j;
      }
    }
    return sb.toString();
  }

  private int abbrLength(String abbr) {
    int len = 0;
    int i = 0;
    while (i < abbr.length()) {
      char c = abbr.charAt(i);
      if (Character.isDigit(c)) {
        while (i < abbr.length() && Character.isDigit(abbr.charAt(i))) i++;
        len++;
      } else {
        len++;
        i++;
      }
    }
    return len;
  }
}
