import java.util.Arrays;

public class Solution3016 {

  public int minimumPushes(String word) {
    int[] freq = new int[26];
    for (char c : word.toCharArray()) {
      freq[c - 'a']++;
    }
    Arrays.sort(freq);

    int pushes = 0;
    for (int i = 0; i < 26; i++) {
      pushes += freq[25 - i] * (i / 8 + 1);
    }
    return pushes;
  }
}
