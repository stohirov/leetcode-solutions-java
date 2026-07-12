import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Solution1331 {
    public int[] arrayRankTransform(int[] arr) {
        int[] sorted = arr.clone();
        Arrays.sort(sorted);

        Map<Integer, Integer> rank = new HashMap<>();
        for (int value : sorted) {
            rank.putIfAbsent(value, rank.size() + 1);
        }

        int[] result = new int[arr.length];
        for (int i = 0; i < arr.length; i++) {
            result[i] = rank.get(arr[i]);
        }
        return result;
    }
}
