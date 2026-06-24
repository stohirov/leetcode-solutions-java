import java.util.ArrayDeque;
import java.util.Deque;
import java.util.TreeSet;

public class Solution1825 {

  private final int m;
  private final int k;
  private final Deque<Integer> window = new ArrayDeque<>();
  private final TreeSet<long[]> low = new TreeSet<>(Solution1825::cmp);
  private final TreeSet<long[]> mid = new TreeSet<>(Solution1825::cmp);
  private final TreeSet<long[]> high = new TreeSet<>(Solution1825::cmp);
  private final Deque<long[]> nodes = new ArrayDeque<>();
  private long midSum = 0;
  private long id = 0;

  public Solution1825(int m, int k) {
    this.m = m;
    this.k = k;
  }

  private static int cmp(long[] a, long[] b) {
    if (a[0] != b[0]) {
      return Long.compare(a[0], b[0]);
    }
    return Long.compare(a[1], b[1]);
  }

  public void addElement(int num) {
    long[] node = new long[] {num, id++};
    window.addLast(num);
    nodes.addLast(node);
    // Insert into low by default, then rebalance.
    low.add(node);

    if (window.size() > m) {
      window.pollFirst();
      long[] old = nodes.pollFirst();
      remove(old);
    }

    rebalance();
  }

  private void remove(long[] node) {
    if (low.remove(node)) {
      return;
    }
    if (mid.remove(node)) {
      midSum -= node[0];
      return;
    }
    high.remove(node);
  }

  private void rebalance() {
    if (window.size() < m) {
      return;
    }

    while (low.size() > k) {
      long[] x = low.pollLast();
      mid.add(x);
      midSum += x[0];
    }
    while (low.size() < k && !mid.isEmpty()) {
      long[] x = mid.pollFirst();
      midSum -= x[0];
      low.add(x);
    }

    while (high.size() > k) {
      long[] x = high.pollFirst();
      mid.add(x);
      midSum += x[0];
    }
    while (high.size() < k && !mid.isEmpty()) {
      long[] x = mid.pollLast();
      midSum -= x[0];
      high.add(x);
    }

    while (!low.isEmpty() && !mid.isEmpty() && cmp(low.last(), mid.first()) > 0) {
      long[] a = low.pollLast();
      long[] b = mid.pollFirst();
      midSum -= b[0];
      midSum += a[0];
      low.add(b);
      mid.add(a);
    }
    while (!mid.isEmpty() && !high.isEmpty() && cmp(mid.last(), high.first()) > 0) {
      long[] a = mid.pollLast();
      long[] b = high.pollFirst();
      midSum -= a[0];
      midSum += b[0];
      mid.add(b);
      high.add(a);
    }
  }

  public int calculateMKAverage() {
    if (window.size() < m) {
      return -1;
    }
    return (int) (midSum / (m - 2 * k));
  }
}
