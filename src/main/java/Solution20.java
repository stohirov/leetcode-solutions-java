import java.util.ArrayDeque;
import java.util.Deque;

public class Solution20 {
  public boolean isValid(String s) {
    Deque<Character> stack = new ArrayDeque<>();

    for (char c : s.toCharArray()) {
      switch (c) {
        case '(' -> stack.push(')');
        case '[' -> stack.push(']');
        case '{' -> stack.push('}');
        default -> {
          if (stack.isEmpty() || stack.pop() != c) return false;
        }
      }
    }

    return stack.isEmpty();
  }
}
