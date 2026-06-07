import java.util.ArrayList;
import java.util.List;

public class Solution113 {
  public List<List<Integer>> pathSum(TreeNode root, int targetSum) {
    List<List<Integer>> result = new ArrayList<>();
    backtrack(root, targetSum, new ArrayList<>(), result);
    return result;
  }

  private void backtrack(TreeNode node, int remaining, List<Integer> current,
                         List<List<Integer>> result) {
    if (node == null) {
      return;
    }

    current.add(node.val);
    remaining -= node.val;

    if (node.left == null && node.right == null && remaining == 0) {
      result.add(new ArrayList<>(current));
    } else {
      backtrack(node.left, remaining, current, result);
      backtrack(node.right, remaining, current, result);
    }

    current.removeLast();
  }
}
