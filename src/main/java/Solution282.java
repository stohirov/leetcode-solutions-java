import java.util.ArrayList;
import java.util.List;

public class Solution282 {
  public List<String> addOperators(String num, int target) {
    List<String> result = new ArrayList<>();
    backtrack(num, target, 0, 0, 0, new StringBuilder(), result);
    return result;
  }

  private void backtrack(String num, int target, int start, long evaluated,
                         long prevOperand, StringBuilder expression,
                         List<String> result) {
    if (start == num.length()) {
      if (evaluated == target) {
        result.add(expression.toString());
      }
      return;
    }

    int lengthBefore = expression.length();
    for (int end = start + 1; end <= num.length(); end++) {
      if (end - start > 1 && num.charAt(start) == '0') {
        break;
      }

      long operand = Long.parseLong(num.substring(start, end));

      if (start == 0) {
        expression.append(operand);
        backtrack(num, target, end, operand, operand, expression, result);
        expression.setLength(lengthBefore);
        continue;
      }

      expression.append('+').append(operand);
      backtrack(num, target, end, evaluated + operand, operand, expression, result);
      expression.setLength(lengthBefore);

      expression.append('-').append(operand);
      backtrack(num, target, end, evaluated - operand, -operand, expression, result);
      expression.setLength(lengthBefore);

      expression.append('*').append(operand);
      backtrack(num, target, end, evaluated - prevOperand + prevOperand * operand,
                prevOperand * operand, expression, result);
      expression.setLength(lengthBefore);
    }
  }
}
