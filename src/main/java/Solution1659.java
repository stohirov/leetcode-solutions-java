public class Solution1659 {
  private int m;
  private int n;
  private int pow;
  private Integer[][][][] memo;

  public int getMaxGridHappiness(int m, int n, int introvertsCount, int extrovertsCount) {
    this.m = m;
    this.n = n;
    this.pow = (int) Math.pow(3, n);
    memo = new Integer[m * n][pow][introvertsCount + 1][extrovertsCount + 1];
    return dfs(0, 0, introvertsCount, extrovertsCount);
  }

  private int dfs(int pos, int mask, int intro, int extro) {
    if (pos == m * n) {
      return 0;
    }
    if (memo[pos][mask][intro][extro] != null) {
      return memo[pos][mask][intro][extro];
    }
    int row = pos / n;
    int col = pos % n;
    int best = dfs(pos + 1, (mask * 3) % pow, intro, extro);
    int up = (mask / (pow / 3)) % 3;
    int left = (col == 0) ? 0 : (mask % 3);

    if (intro > 0) {
      int gain = 120;
      gain += interaction(1, up, row > 0);
      gain += interaction(1, left, col > 0);
      best = Math.max(best, gain + dfs(pos + 1, (mask * 3 + 1) % pow, intro - 1, extro));
    }

    if (extro > 0) {
      int gain = 40;
      gain += interaction(2, up, row > 0);
      gain += interaction(2, left, col > 0);
      best = Math.max(best, gain + dfs(pos + 1, (mask * 3 + 2) % pow, intro, extro - 1));
    }
    memo[pos][mask][intro][extro] = best;
    return best;
  }

  private int interaction(int self, int neighbor, boolean hasNeighbor) {
    if (!hasNeighbor || neighbor == 0) {
      return 0;
    }
    int total = 0;
    total += (self == 1) ? -30 : 20;
    total += (neighbor == 1) ? -30 : 20;
    return total;
  }
}
