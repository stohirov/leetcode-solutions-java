public class Solution2213 {

  private char[] chars;
  private int[] prefix;
  private int[] suffix;
  private int[] best;

  public int[] longestRepeating(String s, String queryCharacters, int[] queryIndices) {
    chars = s.toCharArray();
    int n = chars.length;

    prefix = new int[4 * n];
    suffix = new int[4 * n];
    best = new int[4 * n];

    build(1, 0, n - 1);

    int[] lengths = new int[queryIndices.length];

    for (int i = 0; i < queryIndices.length; i++) {
      chars[queryIndices[i]] = queryCharacters.charAt(i);
      update(1, 0, n - 1, queryIndices[i]);
      lengths[i] = best[1];
    }

    return lengths;
  }

  private void build(int node, int lo, int hi) {
    if (lo == hi) {
      prefix[node] = suffix[node] = best[node] = 1;
      return;
    }

    int mid = (lo + hi) >>> 1;

    build(2 * node, lo, mid);
    build(2 * node + 1, mid + 1, hi);

    pull(node, lo, mid, hi);
  }

  private void update(int node, int lo, int hi, int index) {
    if (lo == hi) {
      return;
    }

    int mid = (lo + hi) >>> 1;

    if (index <= mid) {
      update(2 * node, lo, mid, index);
    } else {
      update(2 * node + 1, mid + 1, hi, index);
    }

    pull(node, lo, mid, hi);
  }

  private void pull(int node, int lo, int mid, int hi) {
    int left = 2 * node;
    int right = 2 * node + 1;

    prefix[node] = prefix[left];
    suffix[node] = suffix[right];
    best[node] = Math.max(best[left], best[right]);

    if (chars[mid] == chars[mid + 1]) {
      best[node] = Math.max(best[node], suffix[left] + prefix[right]);

      if (prefix[left] == mid - lo + 1) {
        prefix[node] = prefix[left] + prefix[right];
      }

      if (suffix[right] == hi - mid) {
        suffix[node] = suffix[right] + suffix[left];
      }
    }
  }

}
