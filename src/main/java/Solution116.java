public class Solution116 {
  static class Node {
    int val;
    Node left;
    Node right;
    Node next;

    Node() {
    }

    Node(int val) {
      this.val = val;
    }

    Node(int val, Node left, Node right, Node next) {
      this.val = val;
      this.left = left;
      this.right = right;
      this.next = next;
    }
  }

  public Node connect(Node root) {
    Node leftmost = root;
    while (leftmost != null && leftmost.left != null) {
      Node head = leftmost;
      while (head != null) {
        head.left.next = head.right;
        if (head.next != null) {
          head.right.next = head.next.left;
        }
        head = head.next;
      }
      leftmost = leftmost.left;
    }
    return root;
  }
}
