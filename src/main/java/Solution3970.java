import java.util.*;

public class Solution3970 {
    public int shortestPath(int n, int[][] edges, String labels, int k) {
        if (k < 1) return -1;

        List<int[]>[] graph = new List[n];
        for (int i = 0; i < n; i++) graph[i] = new ArrayList<>();
        for (int[] e : edges) graph[e[0]].add(new int[]{e[1], e[2]});

        int[] best = new int[n];
        Arrays.fill(best, k + 1);

        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));
        pq.offer(new long[]{0, 0, 1});

        while (!pq.isEmpty()) {
            long[] cur = pq.poll();
            long d = cur[0];
            int u = (int) cur[1], run = (int) cur[2];

            if (run >= best[u]) continue;
            best[u] = run;
            if (u == n - 1) return (int) d;

            for (int[] e : graph[u]) {
                int v = e[0];
                int nextRun = labels.charAt(u) == labels.charAt(v) ? run + 1 : 1;
                if (nextRun > k || nextRun >= best[v]) continue;
                pq.offer(new long[]{d + e[1], v, nextRun});
            }
        }
        return -1;
    }
}
