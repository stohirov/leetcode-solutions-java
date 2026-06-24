import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Solution2349 {
  private final Map<Integer, Integer> indexToNumber;
  private final Map<Integer, TreeSet<Integer>> numberToIndices;

  public Solution2349() {
    indexToNumber = new HashMap<>();
    numberToIndices = new HashMap<>();
  }

  public void change(int index, int number) {
    Integer prev = indexToNumber.get(index);
    if (prev != null) {
      if (prev == number) return;
      TreeSet<Integer> set = numberToIndices.get(prev);
      set.remove(index);
      if (set.isEmpty()) numberToIndices.remove(prev);
    }
    indexToNumber.put(index, number);
    numberToIndices.computeIfAbsent(number, k -> new TreeSet<>()).add(index);
  }

  public int find(int number) {
    TreeSet<Integer> set = numberToIndices.get(number);
    if (set == null || set.isEmpty()) return -1;
    return set.first();
  }
}
