public class Solution832 {

  public int[][] flipAndInvertImage(int[][] image) {
    int n = image.length;
    for (int[] row : image) {
      int left = 0;
      int right = n - 1;
      while (left < right) {
        int temp = row[left] ^ 1;
        row[left] = row[right] ^ 1;
        row[right] = temp;
        left++;
        right--;
      }
      if (left == right) {
        row[left] ^= 1;
      }
    }
    return image;
  }
}
