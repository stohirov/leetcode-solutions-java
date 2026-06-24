import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class Solution332 {
  public List<String> findItinerary(List<List<String>> tickets) {
    Map<String, PriorityQueue<String>> graph = new HashMap<>();
    for (List<String> t : tickets) {
      graph.computeIfAbsent(t.get(0), _ -> new PriorityQueue<>()).offer(t.get(1));
    }
    LinkedList<String> route = new LinkedList<>();
    dfs("JFK", graph, route);
    return route;
  }

  private void dfs(String airport, Map<String, PriorityQueue<String>> graph, LinkedList<String> route) {
    PriorityQueue<String> dests = graph.get(airport);
    while (dests != null && !dests.isEmpty()) {
      dfs(dests.poll(), graph, route);
    }
    route.addFirst(airport);
  }
}
