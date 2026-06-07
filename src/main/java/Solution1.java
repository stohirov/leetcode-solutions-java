import java.util.HashMap;

class Solution1 {
  public int[] twoSum(int[] nums, int target) {
    HashMap<Integer, Integer> integerHashMap = new HashMap<>();
    for (int i = 0; i < nums.length; i++) {
      int diff = target - nums[i];
      if (integerHashMap.containsKey(diff)) {
        return new int[]{integerHashMap.get(diff), i};
      } else {
        integerHashMap.put(nums[i], i);
      }
    }
    return new int[]{};
  }
}