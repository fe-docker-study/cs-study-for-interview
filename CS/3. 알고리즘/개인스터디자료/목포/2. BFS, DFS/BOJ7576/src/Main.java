import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
<<<<<<< HEAD
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int M,N;
    static int[][] tomatoes;
    static Queue<int []> queue = new LinkedList<>();
=======
import java.util.*;

public class Main {
    static int N, M;
    static int[][] tomatoes;

    static Queue<int[]> queue = new LinkedList<>();
>>>>>>> 196e7a27af562f65da0d6f02b8cf720270fe9fbf

    static int[] drow = {1, -1, 0, 0};
    static int[] dcol = {0, 0, 1, -1};

<<<<<<< HEAD
=======

>>>>>>> 196e7a27af562f65da0d6f02b8cf720270fe9fbf
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

<<<<<<< HEAD
        int result = Integer.MIN_VALUE;

        tomatoes = new int[N][M];
=======
        tomatoes = new int[N][M];
        int res = -1;
>>>>>>> 196e7a27af562f65da0d6f02b8cf720270fe9fbf

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                tomatoes[i][j] = Integer.parseInt(st.nextToken());
<<<<<<< HEAD
                if (tomatoes[i][j] == 1) {
                    queue.add(new int[]{i, j});
                }
=======
                if (tomatoes[i][j] == 1) queue.offer(new int[]{i, j});
>>>>>>> 196e7a27af562f65da0d6f02b8cf720270fe9fbf
            }
        }


        bfs();

        for (int i = 0; i < N; i++) {
            for (int j = 0; j < M; j++) {
<<<<<<< HEAD
                if(tomatoes[i][j] == 0) {
                    System.out.println(-1);
                    return;
                }

                result = Math.max(result, tomatoes[i][j] - 1);
            }
        }

        System.out.println(result);
=======
                if(tomatoes[i][j] == 0) return;
                res = Math.max(res, tomatoes[i][j]);
            }
        }

        System.out.println(res - 1);

>>>>>>> 196e7a27af562f65da0d6f02b8cf720270fe9fbf
    }

    static void bfs() {
        while(!queue.isEmpty()) {
<<<<<<< HEAD
             int[] idxs = queue.poll();
             int nowX = idxs[0];
             int nowY = idxs[1];

             for (int i = 0 ; i < 4; i++) {
                 int nextX = nowX + drow[i];
                 int nextY = nowY + dcol[i];

                 if(nextX >= 0 && nextY >= 0 && nextX < N && nextY < M && tomatoes[nextX][nextY] == 0) {
                     tomatoes[nextX][nextY] = tomatoes[nowX][nowY] + 1;
                     queue.offer(new int[]{nextX, nextY});
                 }
             }
        }
=======

            int[] start = queue.poll();

            int curRow = start[0];
            int curCol = start[1];

            for (int i = 0; i < 4; i++) {
                int nextRow = start[0] + drow[i];
                int nextCol = start[1] + dcol[i];

                if(nextRow >= 0 && nextRow < N && nextCol >= 0 && nextCol < M && tomatoes[nextRow][nextCol] == 0) {
                    tomatoes[nextRow][nextCol] = tomatoes[curRow][curCol] + 1;
                    queue.offer(new int[]{nextRow, nextCol});
                }
            }
        }

>>>>>>> 196e7a27af562f65da0d6f02b8cf720270fe9fbf
    }
}
