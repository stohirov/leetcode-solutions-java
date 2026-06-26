public class Solution3739 {

  public long countMajoritySubarrays(int[] nums, int target) {
    int n = nums.length;
    int offset = n + 1;
    long[] tree = new long[2 * n + 2];

    long ans = 0;
    int prefix = 0;
    update(tree, prefix + offset, 1);

    for (int num : nums) {
      prefix += (num == target) ? 1 : -1;
      ans += query(tree, prefix + offset - 1);
      update(tree, prefix + offset, 1);
    }
    return ans;
  }

  private void update(long[] tree, int i, int delta) {
    for (; i < tree.length; i += i & (-i)) tree[i] += delta;
  }

  private long query(long[] tree, int i) {
    long sum = 0;
    for (; i > 0; i -= i & (-i)) sum += tree[i];
    return sum;
  }
}
