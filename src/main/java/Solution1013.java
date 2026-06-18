public class Solution1013 {
  public boolean canThreePartsEqualSum(int[] arr) {
    int total = 0;
    for (int x : arr) total += x;
    if (total % 3 != 0) return false;
    int target = total / 3;
    int sum = 0, parts = 0;
    for (int i = 0; i < arr.length; i++) {
      sum += arr[i];
      if (sum == target) {
        parts++;
        sum = 0;
        if (parts == 2 && i < arr.length - 1) {
          return true;
        }
      }
    }
    return false;
  }
}
