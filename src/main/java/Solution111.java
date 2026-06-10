import java.util.ArrayDeque;
import java.util.Deque;

public class Solution111 {
  public int minDepth(TreeNode root) {
    if (root == null) {
      return 0;
    }

    Deque<TreeNode> queue = new ArrayDeque<>();
    queue.offer(root);
    int depth = 1;

    while (!queue.isEmpty()) {
      int levelSize = queue.size();
      for (int i = 0; i < levelSize; i++) {
        TreeNode node = queue.poll();
        if (node.left == null && node.right == null) {
          return depth;
        }
        if (node.left != null) {
          queue.offer(node.left);
        }
        if (node.right != null) {
          queue.offer(node.right);
        }
      }
      depth++;
    }

    return depth;
  }
}
