import java.util.ArrayList;
import java.util.List;

public class Solution1238 {
  public List<Integer> circularPermutation(int n, int start) {
    List<Integer> result = new ArrayList<>();
    int total = 1 << n;
    for (int i = 0; i < total; i++) {
      result.add(start ^ i ^ (i >> 1));
    }
    return result;
  }
}
