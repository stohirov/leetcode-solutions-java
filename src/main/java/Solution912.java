public class Solution912 {

  public int[] sortArray(int[] nums) {
    mergeSort(nums, 0, nums.length - 1);
    return nums;
  }

  private void mergeSort(int[] nums, int lo, int hi) {
    if (lo >= hi) {
      return;
    }
    int mid = lo + (hi - lo) / 2;
    mergeSort(nums, lo, mid);
    mergeSort(nums, mid + 1, hi);
    merge(nums, lo, mid, hi);
  }

  private void merge(int[] nums, int lo, int mid, int hi) {
    int[] tmp = new int[hi - lo + 1];
    int i = lo, j = mid + 1, idx = 0;
    while (i <= mid && j <= hi) {
      if (nums[i] <= nums[j]) {
        tmp[idx++] = nums[i++];
      } else {
        tmp[idx++] = nums[j++];
      }
    }
    while (i <= mid) {
      tmp[idx++] = nums[i++];
    }
    while (j <= hi) {
      tmp[idx++] = nums[j++];
    }
    System.arraycopy(tmp, 0, nums, lo, tmp.length);
  }
}
