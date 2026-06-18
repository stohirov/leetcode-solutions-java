import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Solution981 {

  private static class Entry {
    final int timestamp;
    final String value;

    Entry(int timestamp, String value) {
      this.timestamp = timestamp;
      this.value = value;
    }
  }

  private final Map<String, List<Entry>> store;

  public Solution981() {
    store = new HashMap<>();
  }

  public void set(String key, String value, int timestamp) {
    store.computeIfAbsent(key, _ -> new ArrayList<>()).add(new Entry(timestamp, value));
  }

  public String get(String key, int timestamp) {
    List<Entry> entries = store.get(key);
    if (entries == null) {
      return "";
    }
    int left = 0;
    int right = entries.size() - 1;
    String result = "";
    while (left <= right) {
      int mid = left + (right - left) / 2;
      if (entries.get(mid).timestamp <= timestamp) {
        result = entries.get(mid).value;
        left = mid + 1;
      } else {
        right = mid - 1;
      }
    }
    return result;
  }
}
