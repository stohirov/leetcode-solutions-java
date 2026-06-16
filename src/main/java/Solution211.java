public class Solution211 {
  static class WordDictionary {
    private final WordDictionary[] children;
    private boolean isWord;

    public WordDictionary() {
      children = new WordDictionary[26];
      isWord = false;
    }

    public void addWord(String word) {
      WordDictionary node = this;
      for (char c : word.toCharArray()) {
        int index = c - 'a';
        if (node.children[index] == null) {
          node.children[index] = new WordDictionary();
        }
        node = node.children[index];
      }
      node.isWord = true;
    }

    public boolean search(String word) {
      return search(word, 0, this);
    }

    private boolean search(String word, int start, WordDictionary node) {
      for (int i = start; i < word.length(); i++) {
        char c = word.charAt(i);
        if (c == '.') {
          for (WordDictionary child : node.children) {
            if (child != null && search(word, i + 1, child)) {
              return true;
            }
          }
          return false;
        }
        int index = c - 'a';
        if (node.children[index] == null) {
          return false;
        }
        node = node.children[index];
      }
      return node.isWord;
    }
  }
}
