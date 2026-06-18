import java.util.*;

public class Solution1081 {
  public String smallestSubsequence(String s) {
    int[] lastIndex = new int[26];
    for (int i = 0; i < s.length(); i++) {
      lastIndex[s.charAt(i) - 'a'] = i;
    }
    boolean[] inStack = new boolean[26];
    Deque<Character> stack = new ArrayDeque<>();
    for (int i = 0; i < s.length(); i++) {
      char c = s.charAt(i);
      if (inStack[c - 'a']) continue;
      while (!stack.isEmpty() && stack.peek() > c && lastIndex[stack.peek() - 'a'] > i) {
        inStack[stack.pop() - 'a'] = false;
      }
      stack.push(c);
      inStack[c - 'a'] = true;
    }
    StringBuilder sb = new StringBuilder();
    Iterator<Character> it = stack.descendingIterator();
    while (it.hasNext()) sb.append(it.next());
    return sb.toString();
  }
}
