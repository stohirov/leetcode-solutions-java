import java.util.PriorityQueue;

public class Solution1845 {

  private final PriorityQueue<Integer> available = new PriorityQueue<>();
  private int next = 1;

  public Solution1845(int n) {
  }

  public int reserve() {
    if (!available.isEmpty()) {
      return available.poll();
    }
    return next++;
  }

  public void unreserve(int seatNumber) {
    available.offer(seatNumber);
  }
}
