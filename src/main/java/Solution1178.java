import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution1178 {
  public List<Integer> findNumOfValidWords(String[] words, String[] puzzles) {
    Map<Integer, Integer> wordCount = new HashMap<>();

    for (String word : words) {
      int mask = 0;
      for (char c : word.toCharArray()) {
        mask |= (1 << (c - 'a'));
      }
      wordCount.merge(mask, 1, Integer::sum);
    }

    List<Integer> result = new ArrayList<>();
    for (String puzzle : puzzles) {
      int mask = 0;

      for (char c : puzzle.toCharArray()) {
        mask |= (1 << (c - 'a'));
      }

      int first = 1 << (puzzle.charAt(0) - 'a');
      int count = 0;
      int sub = mask;

      while (sub > 0) {
        if ((sub & first) != 0) {
          count += wordCount.getOrDefault(sub, 0);
        }
        sub = (sub - 1) & mask;
      }
      result.add(count);
    }
    
    return result;
  }
}
