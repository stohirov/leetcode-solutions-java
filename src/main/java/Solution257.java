import java.util.ArrayList;
import java.util.List;

public class Solution257 {
  public List<String> binaryTreePaths(TreeNode root) {
    List<String> result = new ArrayList<>();
    if (root == null) {
      return result;
    }
    dfs(root, new StringBuilder(), result);
    return result;
  }

  private void dfs(TreeNode node, StringBuilder path, List<String> result) {
    int length = path.length();
    path.append(node.val);

    if (node.left == null && node.right == null) {
      result.add(path.toString());
    } else {
      path.append("->");
      if (node.left != null) {
        dfs(node.left, path, result);
      }
      if (node.right != null) {
        dfs(node.right, path, result);
      }
    }

    path.setLength(length);
  }
}
