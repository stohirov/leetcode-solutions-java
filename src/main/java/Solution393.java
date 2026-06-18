public class Solution393 {

  public boolean validUtf8(int[] data) {
    int remaining = 0;
    for (int b : data) {
      int octet = b & 0xFF;
      if (remaining == 0) {
        if ((octet >> 7) == 0b0) {
          remaining = 0;
        } else if ((octet >> 5) == 0b110) {
          remaining = 1;
        } else if ((octet >> 4) == 0b1110) {
          remaining = 2;
        } else if ((octet >> 3) == 0b11110) {
          remaining = 3;
        } else {
          return false;
        }
      } else {
        if ((octet >> 6) != 0b10) {
          return false;
        }
        remaining--;
      }
    }
    return remaining == 0;
  }
}
