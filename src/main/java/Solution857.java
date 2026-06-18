import java.util.*;

public class Solution857 {
  public double mincostToHireWorkers(int[] quality, int[] wage, int k) {
    int n = quality.length;
    double[][] workers = new double[n][2];
    for (int i = 0; i < n; i++) {
      workers[i][0] = (double) wage[i] / quality[i];
      workers[i][1] = quality[i];
    }
    Arrays.sort(workers, (a, b) -> Double.compare(a[0], b[0]));
    PriorityQueue<Double> maxHeap = new PriorityQueue<>(Collections.reverseOrder());
    double qualitySum = 0;
    double result = Double.MAX_VALUE;
    for (double[] worker : workers) {
      double ratio = worker[0];
      double q = worker[1];
      maxHeap.offer(q);
      qualitySum += q;
      if (maxHeap.size() > k) {
        qualitySum -= maxHeap.poll();
      }
      if (maxHeap.size() == k) {
        result = Math.min(result, qualitySum * ratio);
      }
    }
    return result;
  }
}
