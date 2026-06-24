import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution355 {
  private int timestamp = 0;
  private final Map<Integer, List<int[]>> tweets = new HashMap<>();
  private final Map<Integer, Set<Integer>> following = new HashMap<>();

  public Solution355() {
  }

  public void postTweet(int userId, int tweetId) {
    tweets.computeIfAbsent(userId, x -> new ArrayList<>()).add(new int[]{timestamp++, tweetId});
  }

  public List<Integer> getNewsFeed(int userId) {
    PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[0] - a[0]);
    addTweets(pq, userId);
    Set<Integer> followees = following.get(userId);
    if (followees != null) {
      for (int f : followees) {
        addTweets(pq, f);
      }
    }
    List<Integer> result = new ArrayList<>();
    while (!pq.isEmpty() && result.size() < 10) {
      result.add(pq.poll()[1]);
    }
    return result;
  }

  private void addTweets(PriorityQueue<int[]> pq, int userId) {
    List<int[]> list = tweets.get(userId);
    if (list == null) return;
    int start = Math.max(0, list.size() - 10);
    for (int i = list.size() - 1; i >= start; i--) {
      pq.offer(list.get(i));
    }
  }

  public void follow(int followerId, int followeeId) {
    if (followerId == followeeId) return;
    following.computeIfAbsent(followerId, x -> new HashSet<>()).add(followeeId);
  }

  public void unfollow(int followerId, int followeeId) {
    Set<Integer> set = following.get(followerId);
    if (set != null) set.remove(followeeId);
  }
}
