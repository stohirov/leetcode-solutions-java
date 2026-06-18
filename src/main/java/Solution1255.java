public class Solution1255 {
  public int maxScoreWords(String[] words, char[] letters, int[] score) {
    int[] available = new int[26];
    for (char c : letters) {
      available[c - 'a']++;
    }
    return dfs(words, 0, available, score);
  }

  private int dfs(String[] words, int index, int[] available, int[] score) {
    if (index == words.length) {
      return 0;
    }

    int best = dfs(words, index + 1, available, score);
    int[] need = new int[26];
    int gain = 0;
    boolean canTake = true;

    for (char c : words[index].toCharArray()) {
      need[c - 'a']++;
      if (need[c - 'a'] > available[c - 'a']) {
        canTake = false;
        break;
      }
      gain += score[c - 'a'];
    }

    if (canTake) {
      for (int i = 0; i < 26; i++) {
        available[i] -= need[i];
      }

      best = Math.max(best, gain + dfs(words, index + 1, available, score));

      for (int i = 0; i < 26; i++) {
        available[i] += need[i];
      }
    }

    return best;
  }
}
