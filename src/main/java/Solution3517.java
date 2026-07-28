public class Solution3517 {
  public String smallestPalindrome(String s) {
    int[] count = new int[26];
    for (char c : s.toCharArray()) count[c - 'a']++;

    StringBuilder half = new StringBuilder();
    char mid = 0;
    for (int i = 0; i < 26; i++) {
      half.repeat(String.valueOf((char) ('a' + i)), Math.max(0, count[i] / 2));
      if (count[i] % 2 == 1) mid = (char) ('a' + i);
    }

    StringBuilder result = new StringBuilder(half);
    if (mid != 0) result.append(mid);
    result.append(half.reverse());
    return result.toString();
  }
}
