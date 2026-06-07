import java.util.ArrayList;
import java.util.List;

public class Solution212 {
  private static class TrieNode {
    TrieNode[] children = new TrieNode[26];
    String word;
  }

  public List<String> findWords(char[][] board, String[] words) {
    List<String> result = new ArrayList<>();
    TrieNode root = buildTrie(words);

    for (int r = 0; r < board.length; r++) {
      for (int c = 0; c < board[0].length; c++) {
        dfs(board, r, c, root, result);
      }
    }
    return result;
  }

  private TrieNode buildTrie(String[] words) {
    TrieNode root = new TrieNode();
    for (String word : words) {
      TrieNode node = root;
      for (char ch : word.toCharArray()) {
        int idx = ch - 'a';
        if (node.children[idx] == null) {
          node.children[idx] = new TrieNode();
        }
        node = node.children[idx];
      }
      node.word = word;
    }
    return root;
  }

  private void dfs(char[][] board, int r, int c, TrieNode parent, List<String> result) {
    char ch = board[r][c];
    if (ch == '#') {
      return;
    }
    TrieNode node = parent.children[ch - 'a'];
    if (node == null) {
      return;
    }

    if (node.word != null) {
      result.add(node.word);
      node.word = null;
    }

    board[r][c] = '#';
    if (r > 0) {
      dfs(board, r - 1, c, node, result);
    }
    if (r < board.length - 1) {
      dfs(board, r + 1, c, node, result);
    }
    if (c > 0) {
      dfs(board, r, c - 1, node, result);
    }
    if (c < board[0].length - 1) {
      dfs(board, r, c + 1, node, result);
    }
    board[r][c] = ch;
  }
}
