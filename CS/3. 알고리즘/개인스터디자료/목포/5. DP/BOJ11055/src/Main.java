import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());

        int[] sequence = new int[N + 1];
        int[] dp = new int[N + 1];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for(int i = 1; i <= N; i++) {
            sequence[i] = Integer.parseInt(st.nextToken());
        }

        dp[1] = sequence[1];

        for (int i = 2; i <= N; i++) {
            dp[i] = sequence[i];
            for (int j = 1; j < i; j++) {
                if(sequence[j] < sequence[i]) {
                    dp[i] = Math.max(dp[j] + sequence[i], dp[i]);
                }
            }
        }

        int max = Integer.MIN_VALUE;

        for (int i = 1; i<= N; i++) {
            max = Math.max(max, dp[i]);
        }

        System.out.println(max);
    }
}
