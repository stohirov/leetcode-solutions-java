public class Solution1371 {
  public int findTheLongestSubstring(String s) {
    int[] firstSeen = new int[32];
    java.util.Arrays.fill(firstSeen, -2);
    firstSeen[0] = -1;
    int mask = 0, longest = 0;
    String vowels = "aeiou";
    for (int i = 0; i < s.length(); i++) {
      int idx = vowels.indexOf(s.charAt(i));
      if (idx >= 0) {
        mask ^= (1 << idx);
      }
      if (firstSeen[mask] != -2) {
        longest = Math.max(longest, i - firstSeen[mask]);
      } else {
        firstSeen[mask] = i;
      }
    }
    return longest;
  }
}
