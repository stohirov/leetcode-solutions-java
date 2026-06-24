import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution642 {
  static class TrieNode642 {
    Map<Character, TrieNode642> children = new HashMap<>();
    Map<String, Integer> counts = new HashMap<>(); // sentences passing through, with frequency
  }

  private TrieNode642 root;
  private TrieNode642 cur; // node for the current prefix being typed
  private StringBuilder prefix;

  public Solution642(String[] sentences, int[] times) {
    root = new TrieNode642();
    cur = root;
    prefix = new StringBuilder();
    for (int i = 0; i < sentences.length; i++) {
      insert(sentences[i], times[i]);
    }
  }

  private void insert(String sentence, int count) {
    TrieNode642 node = root;
    for (char c : sentence.toCharArray()) {
      node = node.children.computeIfAbsent(c, x -> new TrieNode642());
      node.counts.merge(sentence, count, Integer::sum);
    }
  }

  public List<String> input(char c) {
    if (c == '#') {
      insert(prefix.toString(), 1);
      prefix.setLength(0);
      cur = root;
      return new ArrayList<>();
    }
    prefix.append(c);
    if (cur != null) cur = cur.children.get(c);
    List<String> res = new ArrayList<>();
    if (cur == null) return res;
    // top 3 by count desc, then ASCII asc
    PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>((a, b) -> {
      if (!a.getValue().equals(b.getValue())) return a.getValue() - b.getValue();
      return b.getKey().compareTo(a.getKey());
    });
    for (Map.Entry<String, Integer> e : cur.counts.entrySet()) {
      pq.offer(e);
      if (pq.size() > 3) pq.poll();
    }
    while (!pq.isEmpty()) res.add(0, pq.poll().getKey());
    return res;
  }
}
