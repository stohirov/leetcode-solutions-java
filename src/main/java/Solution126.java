import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Solution126 {
  public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
    List<List<String>> result = new ArrayList<>();
    Set<String> dict = new HashSet<>(wordList);
    if (!dict.contains(endWord)) {
      return result;
    }
    dict.remove(beginWord);

    Map<String, List<String>> parents = new HashMap<>();
    Set<String> currentLevel = new HashSet<>();
    currentLevel.add(beginWord);
    boolean found = false;

    while (!currentLevel.isEmpty() && !found) {
      dict.removeAll(currentLevel);
      Set<String> nextLevel = new HashSet<>();

      for (String word : currentLevel) {
        char[] chars = word.toCharArray();
        for (int i = 0; i < chars.length; i++) {
          char original = chars[i];
          for (char c = 'a'; c <= 'z'; c++) {
            if (c == original) {
              continue;
            }
            chars[i] = c;
            String next = new String(chars);
            if (dict.contains(next)) {
              if (next.equals(endWord)) {
                found = true;
              }
              nextLevel.add(next);
              parents.computeIfAbsent(next, _ -> new ArrayList<>()).add(word);
            }
          }
          chars[i] = original;
        }
      }

      currentLevel = nextLevel;
    }

    if (found) {
      LinkedList<String> path = new LinkedList<>();
      path.add(endWord);
      backtrack(endWord, beginWord, parents, path, result);
    }
    return result;
  }

  private void backtrack(String word, String beginWord, Map<String, List<String>> parents,
                         LinkedList<String> path, List<List<String>> result) {
    if (word.equals(beginWord)) {
      result.add(new ArrayList<>(path));
      return;
    }
    List<String> preds = parents.get(word);
    if (preds == null) {
      return;
    }
    for (String parent : preds) {
      path.addFirst(parent);
      backtrack(parent, beginWord, parents, path, result);
      path.removeFirst();
    }
  }
}
