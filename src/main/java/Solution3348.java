public class Solution3348 {

  private static final int[] PRIMES = {2, 3, 5, 7};

  private static final int[][] EXPONENTS = {
      {0, 0, 0, 0}, // 0
      {0, 0, 0, 0}, // 1
      {1, 0, 0, 0}, // 2
      {0, 1, 0, 0}, // 3
      {2, 0, 0, 0}, // 4
      {0, 0, 1, 0}, // 5
      {1, 1, 0, 0}, // 6
      {0, 0, 0, 1}, // 7
      {3, 0, 0, 0}, // 8
      {0, 2, 0, 0}  // 9
  };

  private int[][] cost;

  public String smallestNumber(String num, long t) {
    int[] need = new int[4];
    long rest = t;
    for (int k = 0; k < 4; k++) {
      while (rest % PRIMES[k] == 0) {
        rest /= PRIMES[k];
        need[k]++;
      }
    }
    if (rest > 1) {
      return "-1";
    }
    cost = buildCost(need[0], need[1]);

    int n = num.length();
    int firstZero = num.indexOf('0');
    if (firstZero < 0 && isDivisible(num, need)) {
      return num;
    }

    int start = Math.min(n - 1, firstZero < 0 ? n : firstZero);
    int[] prefix = new int[4];
    for (int i = 0; i < start; i++) {
      addDigit(prefix, num.charAt(i) - '0', 1);
    }

    for (int i = start; i >= 0; i--) {
      int free = n - 1 - i;
      int[] base = remaining(prefix, need);
      for (int d = Math.max(num.charAt(i) - '0' + 1, 1); d <= 9; d++) {
        int[] left = divide(base, d);
        if (digitsNeeded(left) <= free) {
          StringBuilder answer = new StringBuilder(n);
          answer.append(num, 0, i).append((char) ('0' + d));
          appendSmallest(answer, left, free);
          return answer.toString();
        }
      }
      if (i > 0) {
        addDigit(prefix, num.charAt(i - 1) - '0', -1);
      }
    }

    int length = Math.max(n + 1, digitsNeeded(need));
    StringBuilder answer = new StringBuilder(length);
    appendSmallest(answer, need, length);
    return answer.toString();
  }

  private int[][] buildCost(int twos, int threes) {
    int[][] dp = new int[twos + 1][threes + 1];
    for (int a = 0; a <= twos; a++) {
      for (int b = 0; b <= threes; b++) {
        if (a == 0 && b == 0) {
          continue;
        }
        int best = Integer.MAX_VALUE;
        for (int d = 2; d <= 9; d++) {
          int nextA = Math.max(0, a - EXPONENTS[d][0]);
          int nextB = Math.max(0, b - EXPONENTS[d][1]);
          if (nextA == a && nextB == b) {
            continue;
          }
          best = Math.min(best, dp[nextA][nextB]);
        }
        dp[a][b] = best + 1;
      }
    }
    return dp;
  }

  private int digitsNeeded(int[] required) {
    return required[2] + required[3] + cost[required[0]][required[1]];
  }

  private void appendSmallest(StringBuilder sb, int[] required, int slots) {
    int[] left = required;
    sb.repeat("1", Math.max(0, slots - digitsNeeded(left)));
    int free = digitsNeeded(left);
    while (free > 0) {
      for (int d = 2; d <= 9; d++) {
        int[] next = divide(left, d);
        if (digitsNeeded(next) < free) {
          sb.append((char) ('0' + d));
          left = next;
          free = digitsNeeded(next);
          break;
        }
      }
    }
  }

  private int[] remaining(int[] have, int[] need) {
    int[] left = new int[4];
    for (int k = 0; k < 4; k++) {
      left[k] = Math.max(0, need[k] - have[k]);
    }
    return left;
  }

  private int[] divide(int[] required, int digit) {
    int[] left = new int[4];
    for (int k = 0; k < 4; k++) {
      left[k] = Math.max(0, required[k] - EXPONENTS[digit][k]);
    }
    return left;
  }

  private void addDigit(int[] have, int digit, int sign) {
    for (int k = 0; k < 4; k++) {
      have[k] += sign * EXPONENTS[digit][k];
    }
  }

  private boolean isDivisible(String num, int[] need) {
    int[] have = new int[4];
    for (int i = 0; i < num.length(); i++) {
      addDigit(have, num.charAt(i) - '0', 1);
    }
    for (int k = 0; k < 4; k++) {
      if (have[k] < need[k]) {
        return false;
      }
    }
    return true;
  }

}
