public class Solution3518 {

  public String smallestPalindrome(String s, int k) {
    int[] freq = new int[26];
    for (char c : s.toCharArray()) {
      freq[c - 'a']++;
    }

    int[] half = new int[26];
    char mid = 0;
    int m = 0;
    for (int i = 0; i < 26; i++) {
      if ((freq[i] & 1) == 1) {
        mid = (char) ('a' + i);
      }
      half[i] = freq[i] / 2;
      m += half[i];
    }

    long rem = k;
    if (countPerms(half, m, rem) < rem) {
      return "";
    }

    StringBuilder left = new StringBuilder();
    for (int pos = 0; pos < m; pos++) {
      for (int c = 0; c < 26; c++) {
        if (half[c] == 0) {
          continue;
        }
        half[c]--;
        long cnt = countPerms(half, m - pos - 1, rem);
        if (cnt >= rem) {
          left.append((char) ('a' + c));
          break;
        }
        rem -= cnt;
        half[c]++;
      }
    }

    StringBuilder result = new StringBuilder(left);
    if (mid != 0) {
      result.append(mid);
    }
    return result.append(left.reverse()).toString();
  }

  private long countPerms(int[] counts, int n, long limit) {
    long total = 1;
    int remaining = n;
    for (int i = 0; i < 26 && remaining > 0; i++) {
      if (counts[i] > 0) {
        total *= binomial(remaining, counts[i], limit);
        if (total >= limit) {
          return limit;
        }
        remaining -= counts[i];
      }
    }
    return total;
  }

  private long binomial(int n, int r, long limit) {
    r = Math.min(r, n - r);
    long res = 1;
    for (int i = 1; i <= r; i++) {
      res = res * (n - r + i) / i;
      if (res >= limit) {
        return limit;
      }
    }
    return res;
  }
}
