import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution30 {
  public List<Integer> findSubstring(String s, String[] words) {
    List<Integer> result = new ArrayList<>();
    int wordLen = words[0].length();
    int numWords = words.length;
    int windowLen = wordLen * numWords;

    if (s.length() < windowLen) {
      return result;
    }

    Map<String, Integer> need = new HashMap<>();
    for (String word : words) {
      need.merge(word, 1, Integer::sum);
    }

    for (int offset = 0; offset < wordLen; offset++) {
      int left = offset;
      int count = 0;
      Map<String, Integer> window = new HashMap<>();

      for (int right = offset; right + wordLen <= s.length(); right += wordLen) {
        String word = s.substring(right, right + wordLen);

        if (need.containsKey(word)) {
          window.merge(word, 1, Integer::sum);
          count++;

          while (window.get(word) > need.get(word)) {
            String leftWord = s.substring(left, left + wordLen);
            window.merge(leftWord, -1, Integer::sum);
            left += wordLen;
            count--;
          }

          if (count == numWords) {
            result.add(left);
            String leftWord = s.substring(left, left + wordLen);
            window.merge(leftWord, -1, Integer::sum);
            left += wordLen;
            count--;
          }
        } else {
          window.clear();
          count = 0;
          left = right + wordLen;
        }
      }
    }

    return result;
  }
}
