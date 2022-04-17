import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int N;
    static int[][] country;

    static Queue<int[]> q = new LinkedList<>();

    static int[] dy = {-1, 1, 0, 0};
    static int[] dx = {0, 0, -1, 1};

    static boolean[][] visited;
    static int num = 2;
    static int min = Integer.MAX_VALUE;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        N = Integer.parseInt(br.readLine());
        country = new int[N][N];
        visited = new boolean[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                country[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if (!visited[i][j] && country[i][j] != 0){
                    islandNumbering(i, j);
                    num++;
                }
            }
        }

        visited = new boolean[N][N];
        for (int i = 0; i < N; i++) {
            for (int j =0; j < N; j++) {
                if (!visited[i][j] && country[i][j] != 0) {
                    bfs(i, j, country[i][j]);
                    visited = new boolean[N][N];
                }
            }
        }


        System.out.println(min);
    }


    public static void islandNumbering(int y, int x) {
        Queue<int[]> q = new LinkedList<>();

        visited[y][x] = true;
        q.offer(new int[]{y, x});
        country[y][x] = num;

        while (!q.isEmpty()) {
            int[] temp = q.poll();

            for (int i = 0; i < 4; i++) {
                int nextY = temp[0] + dy[i];
                int nextX = temp[1] + dx[i];

                if (isRange(nextY, nextX) && !visited[nextY][nextX] && country[nextY][nextX] == 1) {
                    visited[nextY][nextX] = true;
                    country[nextY][nextX] = num;
                    q.offer(new int[]{nextY, nextX});
                }
            }
        }
    }

    public static void bfs(int y, int x, int curIslandNum) {
        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{y, x, 0});
        visited[y][x] = true;

        while(!q.isEmpty()) {
            int[] temp = q.poll();

            int curY = temp[0];
            int curX = temp[1];
            int curCnt = temp[2];

            for (int i = 0;i < 4; i++) {
                int nextY = curY + dy[i];
                int nextX = curX + dx[i];

                if (isRange(nextY, nextX) && !visited[nextY][nextX]) {
                    if (country[nextY][nextX] == 0) {
                        q.offer(new int[]{nextY, nextX, curCnt + 1});
                        visited[nextY][nextX] = true;
                    }else if(country[nextY][nextX] != 0 && country[nextY][nextX] != curIslandNum){
                        min = Math.min(min, curCnt);
                        q.clear();
                        return;
                    }
                }
            }
        }
    }

    public static boolean isRange(int y, int x) {
        if (y >= 0 && y < N && x >= 0 && x < N) return true;
        return false;
    }
}
