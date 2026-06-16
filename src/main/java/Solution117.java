public class Solution117 {
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
    while (leftmost != null) {
      Node dummy = new Node();
      Node tail = dummy;
      Node head = leftmost;
      while (head != null) {
        if (head.left != null) {
          tail.next = head.left;
          tail = tail.next;
        }
        if (head.right != null) {
          tail.next = head.right;
          tail = tail.next;
        }
        head = head.next;
      }
      leftmost = dummy.next;
    }
    return root;
  }
}
