public class Solution1614 {
    //    public int maxDepth(String s) {
//        Stack<Character> stack = new Stack<>();
//        int maxCount = 0;
//
//        for (int i = 0; i < s.length(); i++) {
//            char curr = s.charAt(i);
//
//            if (curr != '(' && curr != ')') continue;
//
//            if (curr == '(') {
//                stack.push(curr);
//            } else {
//                maxCount = Math.max(stack.size(), maxCount);
//                if (!stack.isEmpty()) stack.pop();
//            }
//        }
//
//        return maxCount;
//    }
    public int maxDepth(String s) {
        int maxCount = 0;
        int count = 0;

        for (char c : s.toCharArray()) {

            if (c == '(') {
                count++;
                if (maxCount < count) maxCount = count;
            } else if (c == ')') {
                count--;
            }
        }

        return maxCount;
    }
}
