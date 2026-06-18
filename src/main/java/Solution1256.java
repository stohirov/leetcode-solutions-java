public class Solution1256 {
  public String encode(int num) {
    // The mapping is: index i (1-based in level order of a complete binary tree
    // of "1"/"2"... actually it is num+1 in binary with leading 1 removed)
    String bin = Integer.toBinaryString(num + 1);
    return bin.substring(1);
  }
}
