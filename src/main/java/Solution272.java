import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;

public class Solution272 {
  public List<Integer> closestKValues(TreeNode root, double target, int k) {
    Deque<Integer> pred = new ArrayDeque<>();
    Deque<Integer> succ = new ArrayDeque<>();
    initPredecessor(root, target, pred);
    initSuccessor(root, target, succ);

    if (!pred.isEmpty() && !succ.isEmpty() && pred.peek() == succ.peek()) {
      succ.pop();
    }

    LinkedList<Integer> result = new LinkedList<>();
    while (result.size() < k) {
      if (succ.isEmpty() || (!pred.isEmpty() && target - pred.peek() <= succ.peek() - target)) {
        result.add(pred.pop());
      } else {
        result.add(succ.pop());
      }
    }
    return result;
  }

  private void initPredecessor(TreeNode node, double target, Deque<Integer> stack) {
    while (node != null) {
      if (node.val <= target) {
        stack.push(node.val);
        node = node.right;
      } else {
        node = node.left;
      }
    }
  }

  private void initSuccessor(TreeNode node, double target, Deque<Integer> stack) {
    while (node != null) {
      if (node.val > target) {
        stack.push(node.val);
        node = node.left;
      } else {
        node = node.right;
      }
    }
  }
}
