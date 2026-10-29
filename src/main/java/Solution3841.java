import java.util.ArrayList;
import java.util.List;

public class Solution3841 {
    private int[] fenwick;
    private int size;

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        List<Integer>[] adj = new List[n];
        for (int i = 0; i < n; i++) adj[i] = new ArrayList<>();
        for (int[] e : edges) {
            adj[e[0]].add(e[1]);
            adj[e[1]].add(e[0]);
        }

        int log = 1;
        while ((1 << log) < n) log++;
        int[][] up = new int[log][n];
        int[] depth = new int[n];
        int[] tin = new int[n], tout = new int[n];

        int[] stack = new int[n];
        int[] iter = new int[n];
        int top = 0, timer = 0;
        stack[top++] = 0;
        up[0][0] = 0;
        tin[0] = timer++;
        while (top > 0) {
            int u = stack[top - 1];
            if (iter[u] < adj[u].size()) {
                int v = adj[u].get(iter[u]++);
                if (u != 0 && v == up[0][u]) continue;
                up[0][v] = u;
                depth[v] = depth[u] + 1;
                tin[v] = timer++;
                stack[top++] = v;
            } else {
                tout[u] = timer - 1;
                top--;
            }
        }
        for (int k = 1; k < log; k++) {
            for (int v = 0; v < n; v++) up[k][v] = up[k - 1][up[k - 1][v]];
        }

        size = n;
        fenwick = new int[n + 1];
        char[] chars = s.toCharArray();
        for (int v = 0; v < n; v++) rangeXor(tin[v], tout[v], 1 << (chars[v] - 'a'));

        List<Boolean> answer = new ArrayList<>();
        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char c = parts[2].charAt(0);
                int diff = (1 << (chars[u] - 'a')) ^ (1 << (c - 'a'));
                if (diff != 0) rangeXor(tin[u], tout[u], diff);
                chars[u] = c;
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int lca = lca(u, v, up, depth, log);
                int mask = pointQuery(tin[u]) ^ pointQuery(tin[v]) ^ (1 << (chars[lca] - 'a'));
                answer.add(Integer.bitCount(mask) <= 1);
            }
        }
        return answer;
    }

    private int lca(int u, int v, int[][] up, int[] depth, int log) {
        if (depth[u] < depth[v]) {
            int t = u;
            u = v;
            v = t;
        }
        int diff = depth[u] - depth[v];
        for (int k = 0; k < log; k++) {
            if ((diff >> k & 1) == 1) u = up[k][u];
        }
        if (u == v) return u;
        for (int k = log - 1; k >= 0; k--) {
            if (up[k][u] != up[k][v]) {
                u = up[k][u];
                v = up[k][v];
            }
        }
        return up[0][u];
    }

    private void rangeXor(int l, int r, int val) {
        update(l, val);
        update(r + 1, val);
    }

    private void update(int i, int val) {
        for (i++; i <= size; i += i & -i) fenwick[i] ^= val;
    }

    private int pointQuery(int i) {
        int res = 0;
        for (i++; i > 0; i -= i & -i) res ^= fenwick[i];
        return res;
    }
}
