import java.util.*;

public class Solution1130 {
  public int mctFromLeafValues(int[] arr) {
    Deque<Integer> stack = new ArrayDeque<>();
    stack.push(Integer.MAX_VALUE);
    int result = 0;
    for (int a : arr) {
      while (stack.peek() <= a) {
        int mid = stack.pop();
        result += mid * Math.min(stack.peek(), a);
      }
      stack.push(a);
    }
    while (stack.size() > 2) {
      result += stack.pop() * stack.peek();
    }
    return result;
  }
}
