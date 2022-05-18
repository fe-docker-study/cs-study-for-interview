import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N, K;

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        int[] W = new int[N + 1];
        int[] V = new int[N + 1];

        int[][] dp = new int[N + 1][K + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            W[i] = Integer.parseInt(st.nextToken());
            V[i] = Integer.parseInt(st.nextToken());
        }


        for(int i = 1; i <= N; i++) {
            for(int j = 1; j <= K; j++) {
                if (W[i] > j) // 현재 물건의 무게가 배낭의 크기보다 크다면 --> 현재 물건은 포함되어있지 않다는 것이기 때문에 n-1의 최적해를 대입
                    dp[i][j] = dp[i-1][j];
                else
                    dp[i][j] = Math.max(dp[i-1][j], V[i] + dp[i - 1][j - W[i]]);
            }
        }


        System.out.println(dp[N][K]);
    }
}
