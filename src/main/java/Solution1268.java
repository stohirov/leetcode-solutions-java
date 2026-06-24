import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Solution1268 {

  public List<List<String>> suggestedProducts(String[] products, String searchWord) {
    Arrays.sort(products);
    List<List<String>> result = new ArrayList<>();
    int lo = 0, hi = products.length - 1;
    for (int i = 0; i < searchWord.length(); i++) {
      char c = searchWord.charAt(i);
      while (lo <= hi && (products[lo].length() <= i || products[lo].charAt(i) < c)) lo++;
      while (lo <= hi && (products[hi].length() <= i || products[hi].charAt(i) > c)) hi--;
      List<String> top = new ArrayList<>(
          Arrays.asList(products).subList(lo, Math.min(lo + 3, hi + 1)));
      result.add(top);
    }
    return result;
  }
}
