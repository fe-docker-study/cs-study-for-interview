import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;


        int res = 0;
        int N  = Integer.parseInt(br.readLine());

        int[] dp = new int[N + 2];
        int[] t = new int[N + 2];
        int[] p = new int[N + 2];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }


        for (int i = 1; i <= N; i++) {
            if (i + t[i] <= N + 1) {
                dp[i + t[i]] = Math.max(dp[i + t[i]], p[i] + dp[i]);
                res = Math.max(res, dp[i + t[i]]);
            }

            dp[i + 1] = Math.max(dp[i], dp[i + 1]);
            res = Math.max(res, dp[i + 1]);
        }

        System.out.println(res);

    }
}
