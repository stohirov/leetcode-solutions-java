import java.util.List;

public class Solution1506 {

  static class Node {
    public int val;
    public List<Node> children;

    public Node() {
    }

    public Node(int val) {
      this.val = val;
    }

    public Node(int val, List<Node> children) {
      this.val = val;
      this.children = children;
    }
  }

  public Node findRoot(List<Node> tree) {
    int sum = 0;
    for (Node node : tree) {
      sum += node.val;
      if (node.children != null) {
        for (Node child : node.children) {
          sum -= child.val;
        }
      }
    }
    for (Node node : tree) {
      if (node.val == sum) {
        return node;
      }
    }
    return null;
  }
}
