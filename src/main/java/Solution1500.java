import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Set;

public class Solution1500 {
  // userID -> set of chunks owned
  private final Map<Integer, Set<Integer>> users;
  private final PriorityQueue<Integer> availableIds; // reusable smallest ids
  private int nextId;

  public Solution1500(int m) {
    this.users = new HashMap<>();
    this.availableIds = new PriorityQueue<>();
    this.nextId = 1;
  }

  public int join(List<Integer> ownedChunks) {
    int id;
    if (!availableIds.isEmpty()) {
      id = availableIds.poll();
    } else {
      id = nextId++;
    }
    users.put(id, new HashSet<>(ownedChunks));
    return id;
  }

  public void leave(int userID) {
    if (users.remove(userID) != null) {
      availableIds.offer(userID);
    }
  }

  public List<Integer> request(int userID, int chunkID) {
    List<Integer> owners = new ArrayList<>();
    if (!users.containsKey(userID)) return owners;
    for (Map.Entry<Integer, Set<Integer>> e : users.entrySet()) {
      if (e.getValue().contains(chunkID)) {
        owners.add(e.getKey());
      }
    }
    owners.sort(null);
    if (!owners.isEmpty()) {
      users.get(userID).add(chunkID);
    }
    return owners;
  }
}
