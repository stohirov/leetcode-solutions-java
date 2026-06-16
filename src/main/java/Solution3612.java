public class Solution3612 {
  public String processStr(String s) {
    StringBuilder sb = new StringBuilder();
    for (char c : s.toCharArray()) {
      switch (c) {
        case '*' -> {
          if (!sb.isEmpty()) sb.deleteCharAt(sb.length() - 1);
        }
        case '#' -> sb.append(sb);
        case '%' -> sb.reverse();
        default -> sb.append(c);
      }
    }
    return sb.toString();
  }
}
