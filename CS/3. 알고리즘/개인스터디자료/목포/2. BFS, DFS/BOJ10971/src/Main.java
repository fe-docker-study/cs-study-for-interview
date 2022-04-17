import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;

    static int[][] roads;
    static boolean[] visited;

    static int minCost = Integer.MAX_VALUE;
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        roads = new int[N + 1][N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 1; j <= N; j++) {
                roads[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 1; i <= N; i++){
            visited = new boolean[N + 1];
            visited[i] = true;
            dfs(i, i, 0);
        }

        System.out.println(minCost);
    }

    public static void dfs(int start, int cur, int cost) {

        if (allVisited()) {
            if (roads[cur][start] != 0) {
                minCost = Math.min(minCost, cost + roads[cur][start]);
            }
            return;
        }

        for (int i = 1; i <= N; i++) {
            if (!visited[i] && roads[cur][i] != 0) {
                visited[i] = true;
                dfs(start, i, cost + roads[cur][i]);
                visited[i] = false;
            }
        }
    }

    public static boolean allVisited() {
        for (int i = 1; i <= N; i++) {
            if (!visited[i]) return false;
        }

        return true;
    }
}
