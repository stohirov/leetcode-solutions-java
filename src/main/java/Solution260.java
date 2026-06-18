public class Solution260 {

  public int[] singleNumber(int[] nums) {
    int xorAll = 0;
    for (int num : nums) {
      xorAll ^= num;
    }
    int diff = xorAll & (-xorAll);
    int a = 0;
    for (int num : nums) {
      if ((num & diff) != 0) {
        a ^= num;
      }
    }
    return new int[] {a, xorAll ^ a};
  }
}
