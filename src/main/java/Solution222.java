public class Solution222 {

  public int countNodes(TreeNode root) {
    if (root == null) {
      return 0;
    }
    int leftHeight = leftDepth(root);
    int rightHeight = rightDepth(root);
    if (leftHeight == rightHeight) {
      return (1 << leftHeight) - 1;
    }
    return 1 + countNodes(root.left) + countNodes(root.right);
  }

  private int leftDepth(TreeNode node) {
    int depth = 0;
    while (node != null) {
      depth++;
      node = node.left;
    }
    return depth;
  }

  private int rightDepth(TreeNode node) {
    int depth = 0;
    while (node != null) {
      depth++;
      node = node.right;
    }
    return depth;
  }
}
