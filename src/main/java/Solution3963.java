//import java.util.Arrays;

public class Solution3963 {
    public String[] createGrid(int m, int n) {
        String[] result = new String[m];

        for (int r = 0; r < m; r++) {
            StringBuilder sb = new StringBuilder();
            for (int c = 0; c < n; c++) {
                if (r == 0 || c == n - 1) {
                    sb.append('.');
                } else {
                    sb.append('#');
                }
            }
            result[r] = sb.toString();
        }

        return result;
    }

//    public String[] createGrid(int m, int n) {
//        String[] result = new String[m];
//
//        result[0] = ".".repeat(n);
//        Arrays.fill(result, 1, m, "#".repeat(n - 1) + ".");
//
//        return result;
//    }
}
