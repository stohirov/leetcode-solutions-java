public class Solution982 {

  public int countTriplets(int[] nums) {
    int[] pairCount = new int[1 << 16];
    for (int a : nums) {
      for (int b : nums) {
        pairCount[a & b]++;
      }
    }
    
    int result = 0;
    for (int c : nums) {
      int mask = (~c) & 0xFFFF;
      int sub = mask;
      while (true) {
        result += pairCount[sub];
        if (sub == 0) {
          break;
        }
        sub = (sub - 1) & mask;
      }
    }
    return result;
  }
}
