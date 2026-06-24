import java.util.PriorityQueue;

public class Solution1738 {
  public int kthLargestValue(int[][] matrix, int k) {
    int rows = matrix.length, cols = matrix[0].length;
    int[][] pre = new int[rows + 1][cols + 1];
    PriorityQueue<Integer> heap = new PriorityQueue<>();

    for (int i = 1; i <= rows; i++) {
      for (int j = 1; j <= cols; j++) {
        pre[i][j] = pre[i - 1][j] ^ pre[i][j - 1] ^ pre[i - 1][j - 1] ^ matrix[i - 1][j - 1];
        heap.offer(pre[i][j]);
        if (heap.size() > k) heap.poll();
      }
    }
    return heap.peek();
  }
}
