import java.util.HashMap;
import java.util.Map;
import java.util.TreeSet;

public class Solution2353 {
  private static class Food2353 {
    final String name;
    int rating;

    Food2353(String name, int rating) {
      this.name = name;
      this.rating = rating;
    }
  }

  private final Map<String, Food2353> foodMap;
  private final Map<String, String> foodToCuisine;
  private final Map<String, TreeSet<Food2353>> cuisineMap;

  public Solution2353(String[] foods, String[] cuisines, int[] ratings) {
    foodMap = new HashMap<>();
    foodToCuisine = new HashMap<>();
    cuisineMap = new HashMap<>();
    for (int i = 0; i < foods.length; i++) {
      Food2353 f = new Food2353(foods[i], ratings[i]);
      foodMap.put(foods[i], f);
      foodToCuisine.put(foods[i], cuisines[i]);
      cuisineMap.computeIfAbsent(cuisines[i], k -> new TreeSet<>((a, b) -> {
        if (a.rating != b.rating) return b.rating - a.rating;
        return a.name.compareTo(b.name);
      })).add(f);
    }
  }

  public void changeRating(String food, int newRating) {
    Food2353 f = foodMap.get(food);
    TreeSet<Food2353> set = cuisineMap.get(foodToCuisine.get(food));
    set.remove(f);
    f.rating = newRating;
    set.add(f);
  }

  public String highestRated(String cuisine) {
    return cuisineMap.get(cuisine).first().name;
  }
}
