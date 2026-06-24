import java.util.ArrayDeque;
import java.util.Deque;

public class Solution1776 {

  public double[] getCollisionTimes(int[][] cars) {
    int n = cars.length;
    double[] ans = new double[n];
    Deque<Integer> stack = new ArrayDeque<>();
    for (int i = n - 1; i >= 0; i--) {
      ans[i] = -1.0;
      int pos = cars[i][0], speed = cars[i][1];
      while (!stack.isEmpty()) {
        int j = stack.peek();
        if (cars[j][1] >= speed) {
          stack.pop();
          continue;
        }
        double time = (double) (cars[j][0] - pos) / (speed - cars[j][1]);
        if (ans[j] < 0 || time <= ans[j]) {
          ans[i] = time;
          break;
        }
        stack.pop();
      }
      stack.push(i);
    }
    return ans;
  }
}
