public class Solution779 {

  public int kthGrammar(int n, int k) {
    return Integer.bitCount(k - 1) & 1;
  }
}
