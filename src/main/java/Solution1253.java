import java.util.*;

public class Solution1253 {
  public List<List<Integer>> reconstructMatrix(int upper, int lower, int[] colsum) {
    int n = colsum.length;
    List<Integer> top = new ArrayList<>(Collections.nCopies(n, 0));
    List<Integer> bottom = new ArrayList<>(Collections.nCopies(n, 0));
    for (int i = 0; i < n; i++) {
      if (colsum[i] == 2) {
        top.set(i, 1);
        bottom.set(i, 1);
        upper--;
        lower--;
      }
    }
    for (int i = 0; i < n; i++) {
      if (colsum[i] == 1) {
        if (upper > 0) {
          top.set(i, 1);
          upper--;
        } else {
          bottom.set(i, 1);
          lower--;
        }
      }
    }
    if (upper != 0 || lower != 0) return new ArrayList<>();
    List<List<Integer>> result = new ArrayList<>();
    result.add(top);
    result.add(bottom);
    return result;
  }
}
