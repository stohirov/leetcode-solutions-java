public class Solution1558 {
  public int minOperations(int[] nums) {
    int doubleOps = 0;
    int addOps = 0;
    for (int num : nums) {
      int bits = 0;
      int highest = 0;
      while (num > 0) {
        if ((num & 1) == 1) {
          bits++;
        }
        num >>= 1;
        if (num > 0) {
          highest++;
        }
      }
      addOps += bits;
      doubleOps = Math.max(doubleOps, highest);
    }
    return addOps + doubleOps;
  }
}
