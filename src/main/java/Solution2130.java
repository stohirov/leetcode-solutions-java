public class Solution2130 {
  public int pairSum(ListNode head) {
    ListNode slow = head;
    ListNode fast = head;
    while (fast != null && fast.next != null) {
      slow = slow.next;
      fast = fast.next.next;
    }

    ListNode prev = null;
    while (slow != null) {
      ListNode next = slow.next;
      slow.next = prev;
      prev = slow;
      slow = next;
    }

    int max = 0;
    ListNode first = head;
    ListNode second = prev;
    while (second != null) {
      max = Math.max(max, first.val + second.val);
      first = first.next;
      second = second.next;
    }

    return max;
  }
}