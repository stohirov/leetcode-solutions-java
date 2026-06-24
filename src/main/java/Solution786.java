import java.util.PriorityQueue;

public class Solution786 {

  public int[] kthSmallestPrimeFraction(int[] arr, int k) {
    int n = arr.length;
    PriorityQueue<int[]> pq = new PriorityQueue<>(
        (a, b) -> Integer.compare(arr[a[0]] * arr[b[1]], arr[b[0]] * arr[a[1]]));
    for (int j = 1; j < n; j++) {
      pq.offer(new int[] {0, j});
    }
    for (int step = 0; step < k - 1; step++) {
      int[] cur = pq.poll();
      int i = cur[0], j = cur[1];
      if (i + 1 < j) {
        pq.offer(new int[] {i + 1, j});
      }
    }
    int[] top = pq.poll();
    return new int[] {arr[top[0]], arr[top[1]]};
  }
}
