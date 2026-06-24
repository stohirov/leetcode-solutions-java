import java.util.ArrayList;
import java.util.List;

public class Solution1424 {
  public int[] findDiagonalOrder(List<List<Integer>> nums) {
    List<List<Integer>> diagonals = new ArrayList<>();
    int total = 0;
    for (int i = 0; i < nums.size(); i++) {
      List<Integer> row = nums.get(i);
      for (int j = 0; j < row.size(); j++) {
        int d = i + j;
        while (diagonals.size() <= d) diagonals.add(new ArrayList<>());
        diagonals.get(d).add(row.get(j));
        total++;
      }
    }

    int[] result = new int[total];
    int idx = 0;
    for (List<Integer> diag : diagonals) {
      for (int k = diag.size() - 1; k >= 0; k--) {
        result[idx++] = diag.get(k);
      }
    }
    return result;
  }
}
