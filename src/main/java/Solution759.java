import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

class Interval759 {
  public int start, end;

  public Interval759() {
  }

  public Interval759(int start, int end) {
    this.start = start;
    this.end = end;
  }
}

public class Solution759 {

  public List<Interval759> employeeFreeTime(List<List<Interval759>> schedule) {
    List<Interval759> all = new ArrayList<>();
    for (List<Interval759> emp : schedule) {
      all.addAll(emp);
    }
    all.sort(Comparator.comparingInt(a -> a.start));

    List<Interval759> result = new ArrayList<>();
    int end = all.get(0).end;
    for (Interval759 iv : all) {
      if (iv.start > end) {
        result.add(new Interval759(end, iv.start));
        end = iv.end;
      } else {
        end = Math.max(end, iv.end);
      }
    }
    return result;
  }
}
