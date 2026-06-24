import java.util.Comparator;
import java.util.TreeSet;

public class Solution480 {
  public double[] medianSlidingWindow(int[] nums, int k) {
    Comparator<Integer> cmp =
        (a, b) -> nums[a] != nums[b] ? Integer.compare(nums[a], nums[b]) : Integer.compare(a, b);
    TreeSet<Integer> set = new TreeSet<>(cmp);

    double[] res = new double[nums.length - k + 1];

    for (int i = 0; i < k; i++) set.add(i);
    Integer mid = set.first();
    for (int i = 0; i < (k - 1) / 2; i++) mid = set.higher(mid);
    res[0] = median(nums, set, mid, k);

    for (int i = k; i < nums.length; i++) {
      int out = i - k;

      set.add(i);
      if (cmp.compare(i, mid) < 0) mid = set.lower(mid);

      if (cmp.compare(out, mid) <= 0) mid = set.higher(mid);
      set.remove(out);

      res[i - k + 1] = median(nums, set, mid, k);
    }
    return res;
  }

  private double median(int[] nums, TreeSet<Integer> set, Integer mid, int k) {
    if (k % 2 == 1) return nums[mid];
    return ((double) nums[mid] + nums[set.higher(mid)]) / 2.0;
  }
}
