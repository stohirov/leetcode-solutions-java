import java.util.ArrayList;
import java.util.List;

public class Solution60 {
  public String getPermutation(int n, int k) {
    int[] factorial = new int[n + 1];
    factorial[0] = 1;
    for (int i = 1; i <= n; i++) {
      factorial[i] = factorial[i - 1] * i;
    }

    List<Integer> digits = new ArrayList<>();
    for (int i = 1; i <= n; i++) {
      digits.add(i);
    }

    k--;
    StringBuilder sb = new StringBuilder();
    for (int i = n; i >= 1; i--) {
      int index = k / factorial[i - 1];
      k %= factorial[i - 1];
      sb.append(digits.remove(index));
    }

    return sb.toString();
  }
}
