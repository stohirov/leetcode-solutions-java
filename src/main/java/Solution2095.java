public class Solution2095 {
  public ListNode deleteMiddle(ListNode head) {
    if (head == null || head.next == null) {
      return null;
    }

    ListNode dummy = new ListNode(0, head);
    ListNode slow = dummy;
    ListNode fast = head;

    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    slow.next = slow.next.next;

    return dummy.next;
  }
}
