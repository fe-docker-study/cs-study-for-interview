import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {

    static int N;

    static int[][] map;
    static int[] dx = {1, -1, 0, 0};
    static int[] dy = {0, 0, 1, -1};

    public static void main(String[] args) throws IOException {

        int S, resX, resY;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        map = new int[N][N];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                map[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        S = Integer.parseInt(st.nextToken());
        resX = Integer.parseInt(st.nextToken()) - 1;
        resY = Integer.parseInt(st.nextToken()) - 1;

        loop:
        for (int i = 0; i < S; i++) {
            for(int j = 0; j < K; j++) {
                BFS(j+1);
                if(map[resX][resY] != 0) break loop;
            }
        }

        System.out.println(map[resX][resY]);
    }

    public static void BFS(int virus) {
        Queue<Point> q = new LinkedList<>();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                if(map[i][j] == virus) q.add(new Point(i, j));
            }
        }

        while(!q.isEmpty()) {
            Point qu = q.poll();

            for (int k = 0; k < 4; k++) {
                int nx = qu.x + dx[k];
                int ny = qu.y + dy[k];

                if(nx < 0 || nx >= N || ny < 0 || ny >= N) continue;
                if(map[nx][ny] != 0) continue;

                map[nx][ny] = virus;
            }
        }

    }
}

class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
