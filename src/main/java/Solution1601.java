public class Solution1601 {

  private int[][] requests;
  private int best;

  public int maximumRequests(int n, int[][] requests) {
    this.requests = requests;
    this.best = 0;
    int[] degree = new int[n];
    dfs(0, 0, degree);
    return best;
  }

  private void dfs(int idx, int count, int[] degree) {
    if (idx == requests.length) {
      for (int d : degree) {
        if (d != 0) {
          return;
        }
      }
      best = Math.max(best, count);
      return;
    }

    dfs(idx + 1, count, degree);
    int from = requests[idx][0];
    int to = requests[idx][1];
    degree[from]--;
    degree[to]++;
    dfs(idx + 1, count + 1, degree);
    degree[from]++;
    degree[to]--;
  }
}
