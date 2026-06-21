public class Solution1833 {
  public int maxIceCream(int[] costs, int coins) {
    int max = 0;
    for (int c : costs) max = Math.max(max, c);

    int[] count = new int[max + 1];
    for (int c : costs) count[c]++;

    int bars = 0;
    for (int price = 1; price <= max && coins >= price; price++) {
      if (count[price] == 0) continue;
      int affordable = Math.min(count[price], coins / price);
      bars += affordable;
      coins -= affordable * price;
    }
    return bars;
  }
}
