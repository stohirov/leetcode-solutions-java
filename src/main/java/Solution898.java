import java.util.HashSet;
import java.util.Set;

public class Solution898 {

  public int subarrayBitwiseORs(int[] arr) {
    Set<Integer> result = new HashSet<>();
    Set<Integer> current = new HashSet<>();
    for (int x : arr) {
      Set<Integer> next = new HashSet<>();
      next.add(x);
      for (int prev : current) {
        next.add(prev | x);
      }
      current = next;
      result.addAll(current);
    }
    return result.size();
  }
}
