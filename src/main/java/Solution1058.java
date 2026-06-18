import java.util.*;

public class Solution1058 {
  public String minimizeError(String[] prices, int target) {
    double[] vals = new double[prices.length];
    int floorSum = 0;
    int n = prices.length;
    // number of values we can round up while still possibly reaching target
    List<Double> fracCosts = new ArrayList<>();
    double baseError = 0;
    int ceilCandidates = 0;
    for (int i = 0; i < n; i++) {
      double v = Double.parseDouble(prices[i]);
      int f = (int) Math.floor(v);
      floorSum += f;
      double frac = v - f;
      if (frac == 0) {
        // exact integer, must stay
      } else {
        ceilCandidates++;
        fracCosts.add(frac);
      }
      baseError += frac; // if all floored, error contribution is frac per element
    }
    // If we floor everything, sum = floorSum. We need target.
    int need = target - floorSum; // number of values to round up
    if (need < 0 || need > ceilCandidates) return "-1";

    // For each non-integer value with fraction f:
    //   floor error = f, ceil error = 1 - f
    // Rounding up changes error by (1-f) - f = 1 - 2f.
    // Start from all-floored total error = sum of f, then choose 'need' to round up
    // minimizing total: pick those with largest f (smallest 1-f) to round up.
    fracCosts.sort(Collections.reverseOrder());
    double totalError = 0;
    for (int i = 0; i < fracCosts.size(); i++) {
      double f = fracCosts.get(i);
      if (i < need) {
        totalError += 1 - f; // round up
      } else {
        totalError += f; // floor
      }
    }
    return String.format("%.3f", totalError);
  }
}
