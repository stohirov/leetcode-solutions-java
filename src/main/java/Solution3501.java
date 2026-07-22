import java.util.ArrayList;
import java.util.List;

public class Solution3501 {
  public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
    int n = s.length();
    int totalOnes = 0;
    for (int i = 0; i < n; i++) if (s.charAt(i) == '1') totalOnes++;

    List<int[]> zeroRuns = new ArrayList<>();
    for (int i = 0; i < n; ) {
      int j = i;
      while (j < n && s.charAt(j) == s.charAt(i)) j++;
      if (s.charAt(i) == '0') zeroRuns.add(new int[] {i, j - 1});
      i = j;
    }
    int m = zeroRuns.size();
    int[] zs = new int[m], ze = new int[m], zlen = new int[m];
    for (int i = 0; i < m; i++) {
      zs[i] = zeroRuns.get(i)[0];
      ze[i] = zeroRuns.get(i)[1];
      zlen[i] = ze[i] - zs[i] + 1;
    }

    int pairs = Math.max(m - 1, 0);
    int levels = 1;
    while ((1 << levels) <= pairs) levels++;
    int[][] sparse = new int[levels][pairs];
    for (int i = 0; i < pairs; i++) sparse[0][i] = zlen[i] + zlen[i + 1];
    for (int k = 1; k < levels; k++)
      for (int i = 0; i + (1 << k) <= pairs; i++)
        sparse[k][i] = Math.max(sparse[k - 1][i], sparse[k - 1][i + (1 << (k - 1))]);

    List<Integer> answer = new ArrayList<>(queries.length);
    for (int[] query : queries) {
      int l = query[0], r = query[1];
      int p = firstEndingAtOrAfter(ze, l);
      int q = lastStartingAtOrBefore(zs, r);
      int gain = 0;
      if (p < m && q >= 0 && p < q) {
        int clipP = ze[p] - Math.max(zs[p], l) + 1;
        int clipQ = Math.min(ze[q], r) - zs[q] + 1;
        if (q == p + 1) {
          gain = clipP + clipQ;
        } else {
          gain = Math.max(clipP + zlen[p + 1], clipQ + zlen[q - 1]);
          if (q - 2 >= p + 1) gain = Math.max(gain, rangeMax(sparse, p + 1, q - 2));
        }
      }
      answer.add(totalOnes + gain);
    }
    return answer;
  }

  private int firstEndingAtOrAfter(int[] ze, int l) {
    int lo = 0, hi = ze.length;
    while (lo < hi) {
      int mid = (lo + hi) >>> 1;
      if (ze[mid] >= l) hi = mid;
      else lo = mid + 1;
    }
    return lo;
  }

  private int lastStartingAtOrBefore(int[] zs, int r) {
    int lo = 0, hi = zs.length;
    while (lo < hi) {
      int mid = (lo + hi) >>> 1;
      if (zs[mid] <= r) lo = mid + 1;
      else hi = mid;
    }
    return lo - 1;
  }

  private int rangeMax(int[][] sparse, int lo, int hi) {
    int k = 31 - Integer.numberOfLeadingZeros(hi - lo + 1);
    return Math.max(sparse[k][lo], sparse[k][hi - (1 << k) + 1]);
  }
}
