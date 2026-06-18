import java.util.HashSet;
import java.util.Set;

public class Solution805 {

  public boolean splitArraySameAverage(int[] nums) {
    int n = nums.length;
    if (n == 1) {
      return false;
    }
    int total = 0;
    for (int x : nums) {
      total += x;
    }

    int[] scaled = new int[n];
    for (int i = 0; i < n; i++) {
      scaled[i] = nums[i] * n - total;
    }

    int left = n / 2;
    int right = n - left;
    Set<Integer> leftSums = new HashSet<>();
    for (int mask = 1; mask < (1 << left); mask++) {
      int sum = 0;
      for (int i = 0; i < left; i++) {
        if ((mask & (1 << i)) != 0) {
          sum += scaled[i];
        }
      }
      if (sum == 0) {
        return true;
      }
      leftSums.add(sum);
    }

    for (int mask = 1; mask < (1 << right); mask++) {
      int sum = 0;
      for (int i = 0; i < right; i++) {
        if ((mask & (1 << i)) != 0) {
          sum += scaled[left + i];
        }
      }
      if (sum == 0) {
        return true;
      }

      if (mask != (1 << right) - 1 && leftSums.contains(-sum)) {
        return true;
      }
    }
    return false;
  }
}
