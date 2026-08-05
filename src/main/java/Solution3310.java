import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class Solution3310 {

  public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
    List<List<Integer>> graph = new ArrayList<>();
    for (int i = 0; i < n; i++) {
      graph.add(new ArrayList<>());
    }
    for (int[] call : invocations) {
      graph.get(call[0]).add(call[1]);
    }

    boolean[] suspicious = new boolean[n];
    suspicious[k] = true;
    Deque<Integer> stack = new ArrayDeque<>();
    stack.push(k);
    while (!stack.isEmpty()) {
      for (int next : graph.get(stack.pop())) {
        if (!suspicious[next]) {
          suspicious[next] = true;
          stack.push(next);
        }
      }
    }

    List<Integer> remaining = new ArrayList<>();
    for (int[] call : invocations) {
      if (!suspicious[call[0]] && suspicious[call[1]]) {
        for (int i = 0; i < n; i++) {
          remaining.add(i);
        }
        return remaining;
      }
    }

    for (int i = 0; i < n; i++) {
      if (!suspicious[i]) {
        remaining.add(i);
      }
    }
    return remaining;
  }

}
