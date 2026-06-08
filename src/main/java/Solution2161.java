public class Solution2161 {
  public int[] pivotArray(int[] nums, int pivot) {
    int n = nums.length;
    int[] result = new int[n];
    int left = 0;
    int right = n - 1;

    for (int i = 0; i < n; i++) {
      if (nums[i] < pivot) {
        result[left++] = nums[i];
      }
      if (nums[n - 1 - i] > pivot) {
        result[right--] = nums[n - 1 - i];
      }
    }

    while (left <= right) {
      result[left++] = pivot;
    }

    return result;
  }
}