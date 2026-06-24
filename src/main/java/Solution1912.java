import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeSet;

public class Solution1912 {

  private static final Comparator<int[]> CMP =
      (a, b) -> {
        if (a[2] != b[2]) {
          return a[2] - b[2];
        }
        if (a[0] != b[0]) {
          return a[0] - b[0];
        }
        return a[1] - b[1];
      };

  private final Map<Long, Integer> price = new HashMap<>();
  private final Map<Integer, TreeSet<int[]>> unrented = new HashMap<>();
  private final TreeSet<int[]> rented = new TreeSet<>(CMP);

  public Solution1912(int n, int[][] entries) {
    for (int[] e : entries) {
      int shop = e[0], movie = e[1], p = e[2];
      price.put(key(shop, movie), p);
      unrented
          .computeIfAbsent(movie, _ -> new TreeSet<>(CMP))
          .add(new int[] {shop, movie, p});
    }
  }

  private long key(int shop, int movie) {
    return (long) shop * 1_000_000L + movie;
  }

  public List<Integer> search(int movie) {
    List<Integer> res = new ArrayList<>();
    TreeSet<int[]> set = unrented.get(movie);
    if (set == null) {
      return res;
    }
    int count = 0;
    for (int[] copy : set) {
      res.add(copy[0]);
      if (++count == 5) {
        break;
      }
    }
    return res;
  }

  public void rent(int shop, int movie) {
    int p = price.get(key(shop, movie));
    int[] copy = new int[] {shop, movie, p};
    unrented.get(movie).remove(copy);
    rented.add(copy);
  }

  public void drop(int shop, int movie) {
    int p = price.get(key(shop, movie));
    int[] copy = new int[] {shop, movie, p};
    rented.remove(copy);
    unrented.computeIfAbsent(movie, _ -> new TreeSet<>(CMP)).add(copy);
  }

  public List<List<Integer>> report() {
    List<List<Integer>> res = new ArrayList<>();
    int count = 0;
    for (int[] copy : rented) {
      res.add(List.of(copy[0], copy[1]));
      if (++count == 5) {
        break;
      }
    }
    return res;
  }
}
