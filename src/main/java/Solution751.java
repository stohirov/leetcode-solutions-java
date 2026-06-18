import java.util.ArrayList;
import java.util.List;

public class Solution751 {

  public List<String> ipToCIDR(String ip, int n) {
    long start = ipToLong(ip);
    List<String> result = new ArrayList<>();
    while (n > 0) {
      // Largest block aligned to start: lowest set bit of start.
      long lowBit = start & (-start);
      long maxByAlign = (start == 0) ? (1L << 32) : lowBit;
      // Cap by remaining count (largest power of two <= n).
      long size = maxByAlign;
      while (size > n) {
        size >>= 1;
      }
      int prefix = 32 - Integer.numberOfTrailingZeros((int) size);
      result.add(longToIp(start) + "/" + prefix);
      start += size;
      n -= size;
    }
    return result;
  }

  private long ipToLong(String ip) {
    String[] parts = ip.split("\\.");
    long val = 0;
    for (String part : parts) {
      val = (val << 8) | Integer.parseInt(part);
    }
    return val;
  }

  private String longToIp(long val) {
    return ((val >> 24) & 0xFF) + "." + ((val >> 16) & 0xFF) + "."
        + ((val >> 8) & 0xFF) + "." + (val & 0xFF);
  }
}
