import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[] t;
    static int[] p;
    static int[] dp;
    static int res = Integer.MIN_VALUE;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());

        t = new int[N + 2];
        p = new int[N + 2];
        dp = new int[N + 2];
        StringTokenizer st;

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());

            t[i] = Integer.parseInt(st.nextToken());
            p[i] = Integer.parseInt(st.nextToken());
        }

        for (int i = 1; i <= N; i++) {
            if(i + t[i] <= N + 1) {
                dp[i + t[i]] = Math.max(dp[i + t[i]], p[i] + dp[i]);
                res = Math.max(dp[i + t[i]], res);
            }

            dp[i + 1] = Math.max(dp[i + 1], dp[i]);
            res = Math.max(dp[i + 1], res);
        }

        System.out.println(res);

    }

}
