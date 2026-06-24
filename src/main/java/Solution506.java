import java.util.PriorityQueue;

public class Solution506 {
  public String[] findRelativeRanks(int[] score) {
    int n = score.length;
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
    for (int i = 0; i < n; i++) pq.offer(new int[]{score[i], i});
    String[] res = new String[n];
    int rank = 1;
    while (!pq.isEmpty()) {
      int idx = pq.poll()[1];
      if (rank == 1) res[idx] = "Gold Medal";
      else if (rank == 2) res[idx] = "Silver Medal";
      else if (rank == 3) res[idx] = "Bronze Medal";
      else res[idx] = String.valueOf(rank);
      rank++;
    }
    return res;
  }
}
