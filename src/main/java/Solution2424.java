public class Solution2424 {
  private final boolean[] uploaded;
  private int prefix;

  public Solution2424(int n) {
    uploaded = new boolean[n + 2];
    prefix = 0;
  }

  public void upload(int video) {
    uploaded[video] = true;
    if (video == prefix + 1) {
      while (uploaded[prefix + 1]) {
        prefix++;
      }
    }
  }

  public int longest() {
    return prefix;
  }
}
