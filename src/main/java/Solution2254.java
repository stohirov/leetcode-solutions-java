import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution2254 {
  private static class Video {
    String content;
    int likes;
    int dislikes;
    int views;

    Video(String content) {
      this.content = content;
    }
  }

  private final Map<Integer, Video> videos;
  private final PriorityQueue<Integer> freeIds;
  private int nextId;

  public Solution2254() {
    videos = new HashMap<>();
    freeIds = new PriorityQueue<>();
    nextId = 0;
  }

  public int upload(String video) {
    int id;
    if (!freeIds.isEmpty()) {
      id = freeIds.poll();
    } else {
      id = nextId++;
    }
    videos.put(id, new Video(video));
    return id;
  }

  public void remove(int videoId) {
    if (videos.containsKey(videoId)) {
      videos.remove(videoId);
      freeIds.offer(videoId);
    }
  }

  public String watch(int videoId, int startMinute, int endMinute) {
    Video v = videos.get(videoId);
    if (v == null) return "-1";
    v.views++;
    int end = Math.min(endMinute, v.content.length() - 1);
    if (startMinute > end) return "";
    return v.content.substring(startMinute, end + 1);
  }

  public void like(int videoId) {
    Video v = videos.get(videoId);
    if (v != null) v.likes++;
  }

  public void dislike(int videoId) {
    Video v = videos.get(videoId);
    if (v != null) v.dislikes++;
  }

  public int[] getLikesAndDislikes(int videoId) {
    Video v = videos.get(videoId);
    if (v == null) return new int[] {-1};
    return new int[] {v.likes, v.dislikes};
  }

  public int getViews(int videoId) {
    Video v = videos.get(videoId);
    if (v == null) return -1;
    return v.views;
  }
}
