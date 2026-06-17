public class Solution3614 {
  public char processStr(String s, long k) {
    int n = s.length();
    long CAP = 2_000_000_000_000_000L;

    long[] lengths = new long[n + 1];
    long len = 0;
    for (int i = 0; i < n; i++) {
      char c = s.charAt(i);
      switch (c) {
        case '*' -> len = Math.max(len - 1, 0);
        case '#' -> len = Math.min(len * 2, CAP);
        case '%' -> {  }
        default -> len = Math.min(len + 1, CAP);
      }
      lengths[i + 1] = len;
    }

    if (k < 0 || k >= lengths[n]) return '.';

    long idx = k;
    for (int i = n; i >= 1; i--) {
      char c = s.charAt(i - 1);
      long before = lengths[i - 1];
      switch (c) {
        case '*' -> {  }
        case '#' -> { if (idx >= before) idx -= before; }
        case '%' -> idx = before - 1 - idx;
        default -> {
          if (idx == before) return c;
        }
      }
    }
    return '.';
  }
}
