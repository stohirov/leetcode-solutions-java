import java.util.*;

public class Solution527 {
  public List<String> wordsAbbreviation(List<String> words) {
    int n = words.size();
    String[] result = new String[n];
    int[] prefix = new int[n]; // number of leading chars kept

    for (int i = 0; i < n; i++) {
      prefix[i] = 1;
      result[i] = makeAbbr(words.get(i), 1);
    }

    for (int i = 0; i < n; i++) {
      while (true) {
        // find all indices sharing the same abbreviation as i
        List<Integer> group = new ArrayList<>();
        for (int j = i; j < n; j++) {
          if (result[j].equals(result[i])) group.add(j);
        }
        if (group.size() == 1) break;
        // increase prefix for all in group
        for (int idx : group) {
          prefix[idx]++;
          result[idx] = makeAbbr(words.get(idx), prefix[idx]);
        }
      }
    }
    return Arrays.asList(result);
  }

  private String makeAbbr(String word, int prefix) {
    // abbreviation only if it shortens the word
    if (word.length() - prefix <= 3) {
      return word;
    }
    int num = word.length() - prefix - 1;
    return word.substring(0, prefix) + num + word.charAt(word.length() - 1);
  }
}
