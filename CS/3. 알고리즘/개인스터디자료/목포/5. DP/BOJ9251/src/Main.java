import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = "/".concat(br.readLine());
        String str2 = "/".concat(br.readLine());


        int[][] LCS = new int[str1.length()][str2.length()];

        int res = Integer.MIN_VALUE;

        for (int i = 0; i < str1.length(); i++) {
            for (int j = 0; j < str2.length(); j++) {
                if(i == 0 || j == 0) LCS[i][j] = 0;
                else if(str1.charAt(i) == str2.charAt(j)) LCS[i][j] = LCS[i - 1][j - 1] + 1;
                else LCS[i][j] = Math.max(LCS[i - 1][j], LCS[i][j - 1]);

                res = Math.max(res, LCS[i][j]);
            }
        }

        System.out.println(res);
    }
}
