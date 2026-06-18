import java.util.*;

public class Solution409 {
  public int longestPalindrome(String s) {
    int[] counts = new int[128];
    for (char c : s.toCharArray()) counts[c]++;
    int length = 0;
    boolean hasOdd = false;
    for (int c : counts) {
      length += c / 2 * 2;
      if (c % 2 == 1) hasOdd = true;
    }
    return hasOdd ? length + 1 : length;
  }
}
