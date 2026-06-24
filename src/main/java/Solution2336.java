import java.util.TreeSet;

public class Solution2336 {
  private int next;
  private final TreeSet<Integer> addedBack;

  public Solution2336() {
    next = 1;
    addedBack = new TreeSet<>();
  }

  public int popSmallest() {
    if (!addedBack.isEmpty()) {
      return addedBack.pollFirst();
    }
    return next++;
  }

  public void addBack(int num) {
    if (num < next && !addedBack.contains(num)) {
      addedBack.add(num);
    }
  }
}
