import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution133 {
  static class Node {
    int val;
    List<Node> neighbors;

    Node() {
      this.val = 0;
      this.neighbors = new ArrayList<>();
    }

    Node(int val) {
      this.val = val;
      this.neighbors = new ArrayList<>();
    }

    Node(int val, ArrayList<Node> neighbors) {
      this.val = val;
      this.neighbors = neighbors;
    }
  }

  private final Map<Node, Node> visited = new HashMap<>();

  public Node cloneGraph(Node node) {
    if (node == null) {
      return null;
    }
    if (visited.containsKey(node)) {
      return visited.get(node);
    }
    Node clone = new Node(node.val);
    visited.put(node, clone);
    for (Node neighbor : node.neighbors) {
      clone.neighbors.add(cloneGraph(neighbor));
    }
    return clone;
  }
}
