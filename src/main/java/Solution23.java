import java.util.Comparator;
import java.util.PriorityQueue;

public class Solution23 {
  public ListNode mergeKLists(ListNode[] lists) {
    PriorityQueue<ListNode> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.val));
    for (ListNode node : lists) {
      if (node != null) pq.offer(node);
    }
    ListNode dummy = new ListNode();
    ListNode tail = dummy;
    while (!pq.isEmpty()) {
      ListNode node = pq.poll();
      tail.next = node;
      tail = node;
      if (node.next != null) pq.offer(node.next);
    }
    return dummy.next;
  }
}
