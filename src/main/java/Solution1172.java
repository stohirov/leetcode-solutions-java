import java.util.ArrayList;
import java.util.List;
import java.util.TreeSet;

public class Solution1172 {

  private final int capacity;
  private final List<List<Integer>> stacks;
  private final TreeSet<Integer> available;

  public Solution1172(int capacity) {
    this.capacity = capacity;
    this.stacks = new ArrayList<>();
    this.available = new TreeSet<>();
  }

  public void push(int val) {
    if (capacity == 0) return;
    if (available.isEmpty()) {
      int idx = stacks.size();
      List<Integer> s = new ArrayList<>();
      s.add(val);
      stacks.add(s);
      if (capacity > 1) available.add(idx);
    } else {
      int idx = available.first();
      List<Integer> s = stacks.get(idx);
      s.add(val);
      if (s.size() == capacity) available.remove(idx);
    }
  }

  public int pop() {
    int idx = stacks.size() - 1;
    while (idx >= 0 && stacks.get(idx).isEmpty()) {
      stacks.remove(idx);
      available.remove(idx);
      idx--;
    }
    if (idx < 0) return -1;
    return popAtStack(idx);
  }

  public int popAtStack(int index) {
    if (index < 0 || index >= stacks.size() || stacks.get(index).isEmpty()) {
      return -1;
    }
    List<Integer> s = stacks.get(index);
    int val = s.remove(s.size() - 1);
    if (capacity > 0) available.add(index);
    return val;
  }
}
