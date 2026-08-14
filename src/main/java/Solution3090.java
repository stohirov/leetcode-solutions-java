public class Solution3090 {

  public int maximumLengthSubstring(String s) {
    int[] frequency = new int[26];

    int maxLength = 0;
    int left = 0;

    for (int right = 0; right < s.length(); right++) {
      int current = s.charAt(right) - 'a';
      frequency[current]++;

      while (frequency[current] > 2) {
        frequency[s.charAt(left++) - 'a']--;
      }

      maxLength = Math.max(maxLength, right - left + 1);
    }

    return maxLength;
  }

}
