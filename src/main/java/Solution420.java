import java.util.*;

public class Solution420 {
  public int strongPasswordChecker(String password) {
    int n = password.length();
    boolean hasLower = false, hasUpper = false, hasDigit = false;
    for (char c : password.toCharArray()) {
      if (Character.isLowerCase(c)) hasLower = true;
      else if (Character.isUpperCase(c)) hasUpper = true;
      else if (Character.isDigit(c)) hasDigit = true;
    }
    int missingTypes = 0;
    if (!hasLower) missingTypes++;
    if (!hasUpper) missingTypes++;
    if (!hasDigit) missingTypes++;

    List<Integer> repeats = new ArrayList<>();
    int i = 0;
    while (i < n) {
      int j = i;
      while (j < n && password.charAt(j) == password.charAt(i)) j++;
      int len = j - i;
      if (len >= 3) repeats.add(len);
      i = j;
    }

    if (n < 6) {
      return Math.max(6 - n, missingTypes);
    } else if (n <= 20) {
      int replace = 0;
      for (int len : repeats) replace += len / 3;
      return Math.max(replace, missingTypes);
    } else {
      int over = n - 20;
      int delete = over;
      int totalReplace = 0;
      for (int len : repeats) totalReplace += len / 3;

      int[] runs = new int[repeats.size()];
      for (int k = 0; k < runs.length; k++) runs[k] = repeats.get(k);

      for (int mod = 0; mod < 3 && over > 0; mod++) {
        for (int k = 0; k < runs.length && over > 0; k++) {
          int len = runs[k];
          if (len < 3) continue;
          if (len % 3 != mod) continue;
          int need = mod + 1;
          int use = Math.min(over, need);
          runs[k] -= use;
          over -= use;
          if (use == need) {
            totalReplace--;
          }
        }
      }

      for (int k = 0; k < runs.length && over > 0; k++) {
        int len = runs[k];
        if (len < 3) continue;
        int canSave = len / 3;
        int use = Math.min(over, canSave * 3);
        totalReplace -= use / 3;
        over -= use;
      }
      return delete + Math.max(totalReplace, missingTypes);
    }
  }
}
