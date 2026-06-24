import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution2102 {

  private final PriorityQueue<String[]> left;
  private final PriorityQueue<String[]> right;

  private static final Comparator<String[]> BEST_FIRST = (a, b) -> {
    int sa = Integer.parseInt(a[1]);
    int sb = Integer.parseInt(b[1]);
    if (sa != sb) {
      return sb - sa;
    }
    return a[0].compareTo(b[0]);
  };

  public Solution2102() {
    right = new PriorityQueue<>(BEST_FIRST);
    left = new PriorityQueue<>(BEST_FIRST.reversed());
  }

  public void add(String name, int score) {
    right.offer(new String[] {name, Integer.toString(score)});
    if (!left.isEmpty() && BEST_FIRST.compare(left.peek(), right.peek()) > 0) {
      String[] a = left.poll();
      String[] b = right.poll();
      left.offer(b);
      right.offer(a);
    }
  }

  public String get() {
    String[] best = right.poll();
    left.offer(best);
    return best[0];
  }
}
