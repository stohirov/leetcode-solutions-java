import java.util.Stack;

public class Solution155 {
  private Stack<Integer> stack;
  private Stack<Integer> minStack;

  public Solution155() {
    stack = new Stack<>();
    minStack = new Stack<>();
  }

  public void push(int value) {
    stack.push(value);
    if (minStack.isEmpty() || minStack.peek() >= value) {
      minStack.push(value);
    }
  }

  public void pop() {
    int pop = stack.pop();
    if (!minStack.isEmpty() && minStack.peek() == pop) {
      minStack.pop();
    }
  }

  public int top() {
    return stack.peek();
  }

  public int getMin() {
    return minStack.peek();
  }

}
