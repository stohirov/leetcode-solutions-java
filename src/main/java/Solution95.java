import java.util.ArrayList;
import java.util.List;

public class Solution95 {
  public List<TreeNode> generateTrees(int n) {
    if (n == 0) {
      return new ArrayList<>();
    }
    return build(1, n);
  }

  private List<TreeNode> build(int start, int end) {
    List<TreeNode> trees = new ArrayList<>();
    if (start > end) {
      trees.add(null);
      return trees;
    }

    for (int root = start; root <= end; root++) {
      List<TreeNode> leftSubtrees = build(start, root - 1);
      List<TreeNode> rightSubtrees = build(root + 1, end);

      for (TreeNode left : leftSubtrees) {
        for (TreeNode right : rightSubtrees) {
          trees.add(new TreeNode(root, left, right));
        }
      }
    }
    return trees;
  }
}
