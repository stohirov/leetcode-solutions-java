import java.util.HashMap;
import java.util.Map;

public class Solution691 {

  public int minStickers(String[] stickers, String target) {
    int n = stickers.length;
    int[][] stickerCounts = new int[n][26];
    for (int i = 0; i < n; i++) {
      for (char c : stickers[i].toCharArray()) {
        stickerCounts[i][c - 'a']++;
      }
    }
    Map<String, Integer> memo = new HashMap<>();
    int result = dfs(target, stickerCounts, memo);
    return result == Integer.MAX_VALUE ? -1 : result;
  }

  private int dfs(String remaining, int[][] stickerCounts, Map<String, Integer> memo) {
    if (remaining.isEmpty()) {
      return 0;
    }
    if (memo.containsKey(remaining)) {
      return memo.get(remaining);
    }
    int[] need = new int[26];
    for (char c : remaining.toCharArray()) {
      need[c - 'a']++;
    }
    int best = Integer.MAX_VALUE;
    for (int[] sticker : stickerCounts) {
      if (sticker[remaining.charAt(0) - 'a'] == 0) {
        continue;
      }
      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < 26; i++) {
        int leftover = need[i] - sticker[i];
        sb.repeat(String.valueOf((char) ('a' + i)), Math.max(0, leftover));
      }
      int sub = dfs(sb.toString(), stickerCounts, memo);
      if (sub != Integer.MAX_VALUE) {
        best = Math.min(best, sub + 1);
      }
    }
    memo.put(remaining, best);
    return best;
  }
}
