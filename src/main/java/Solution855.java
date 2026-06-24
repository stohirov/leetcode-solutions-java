import java.util.TreeSet;

public class Solution855 {

  private final int n;
  private final TreeSet<Integer> seats;

  public Solution855(int n) {
    this.n = n;
    this.seats = new TreeSet<>();
  }

  public int seat() {
    if (seats.isEmpty()) {
      seats.add(0);
      return 0;
    }

    int best = 0;
    int bestDist = seats.first();

    Integer prev = null;
    for (int s : seats) {
      if (prev != null) {
        int d = (s - prev) / 2;
        if (d > bestDist) {
          bestDist = d;
          best = prev + d;
        }
      }
      prev = s;
    }

    int last = seats.last();
    if (n - 1 - last > bestDist) {
      best = n - 1;
    }

    seats.add(best);
    return best;
  }

  public void leave(int p) {
    seats.remove(p);
  }
}
