import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Solution2034 {

  private final Map<Integer, Integer> prices;
  private final TreeMap<Integer, Integer> priceCounts;
  private int latestTime;

  public Solution2034() {
    prices = new HashMap<>();
    priceCounts = new TreeMap<>();
    latestTime = 0;
  }

  public void update(int timestamp, int price) {
    if (prices.containsKey(timestamp)) {
      int old = prices.get(timestamp);
      int c = priceCounts.get(old);
      if (c == 1) {
        priceCounts.remove(old);
      } else {
        priceCounts.put(old, c - 1);
      }
    }
    prices.put(timestamp, price);
    priceCounts.merge(price, 1, Integer::sum);
    latestTime = Math.max(latestTime, timestamp);
  }

  public int current() {
    return prices.get(latestTime);
  }

  public int maximum() {
    return priceCounts.lastKey();
  }

  public int minimum() {
    return priceCounts.firstKey();
  }
}
