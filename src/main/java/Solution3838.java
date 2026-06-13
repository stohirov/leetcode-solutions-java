public class Solution3838 {
  public String mapWordWeights(String[] words, int[] weights) {
    StringBuilder sb = new StringBuilder();
    for (String word : words) {
      int weight = 0;
      for (int i = 0; i < word.length(); i++) {
        weight += weights[word.charAt(i) - 'a'];
      }
      sb.append((char) ('z' - weight % 26));
    }
    return sb.toString();
  }
}
