import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    static int N, M; // N 세로, M, 가로
    static int[][] floor;
    static int cnt = 1;

    static int[] dy = {-1, 0, 1, 0};
    static int[] dx = {0, 1, 0, -1}; // 북동남서 (행렬 기준으로)
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        floor = new int[N][M];

        st = new StringTokenizer(br.readLine());
        int startY, startX, startD;

        startY = Integer.parseInt(st.nextToken());
        startX = Integer.parseInt(st.nextToken());
        startD = Integer.parseInt(st.nextToken());

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                floor[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        dfs(startY, startX, startD);
        System.out.println(cnt);
    }


    public static void dfs(int row, int col, int dir) {
        floor[row][col] = 2;

        for (int i = 0; i < 4; i++) { // 현재 위치에서 4방향 확인(왼쪽으로 돌면서)
            dir = (dir + 3) % 4; // 왼쪽으로 돌기 위한 방향 계산
            int nextY = row + dy[dir];
            int nextX = col + dx[dir];

            if (isRange(nextY, nextX) && floor[nextY][nextX] == 0) {
                cnt++;
                dfs(nextY, nextX, dir);

                return; // 왼쪽 작업에 대한 것만 계산해주고 return 해야 함.
            }
        }

        // 다 벽이거나 청소했을 때 반대편으로 이동 (방향을 유지한 채)
        // 단지 뒤로 이동하는 것은 청소를 했든지 안 했든지 관게가 없음.
        int d = (dir + 2) % 4;
        int nextY = row + dy[d];
        int nextX = col + dx[d];

        if(isRange(nextY, nextX) && floor[nextY][nextX] != 1) {
            dfs(nextY, nextX, dir);
        }
    }


    public static boolean isRange(int y, int x) {
        if (y >= 0 && y < N && x >= 0 && x < M) return true;
        return false;
    }
}
