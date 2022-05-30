import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        int[][] arrSize = new int[N + 1][2];
        int[][] dp = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            arrSize[i][0] = Integer.parseInt(st.nextToken());
            arrSize[i][1] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i < N; i++) {
            for (int j = 1; j + i<= N; j++) {
                dp[j][j + i] = Integer.MAX_VALUE;

                for (int k = j; k <= j + i; k++) {
                    dp[j][j + i] = Math.min(dp[j][j + i], dp[j][k] + dp[k + 1][i + j] + (arrSize[j][0] * arrSize[k][1] * arrSize[j + i][1]));
                }
            }
        }
    }
}