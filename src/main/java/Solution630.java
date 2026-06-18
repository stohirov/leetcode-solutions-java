import java.util.*;

public class Solution630 {

  public int scheduleCourse(int[][] courses) {
    Arrays.sort(courses, Comparator.comparingInt(a -> a[1]));
    PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a, b) -> b - a);
    int time = 0;
    for (int[] course : courses) {
      int duration = course[0];
      int deadline = course[1];
      if (time + duration <= deadline) {
        time += duration;
        maxHeap.offer(duration);
      } else if (!maxHeap.isEmpty() && maxHeap.peek() > duration) {
        time += duration - maxHeap.poll();
        maxHeap.offer(duration);
      }
    }
    return maxHeap.size();
  }
}
