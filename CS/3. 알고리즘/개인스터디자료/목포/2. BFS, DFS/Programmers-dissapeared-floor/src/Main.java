import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {

    static int N, M; // 높이, 가로

    static int[] dy = {1, -1, 0, 0};
    static int[] dx = {0, 0, 1, -1};

    public static class WF {
        boolean win;
        int cnt;

        public WF(boolean win, int cnt) {
            this.win = win;
            this.cnt = cnt;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        int[][] board = new int[N][M];
        int[] aloc = new int[2];
        int[] bloc = new int[2];


        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < M; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
            }
        }

        st = new StringTokenizer(br.readLine());

        aloc[0] = Integer.parseInt(st.nextToken());
        aloc[1] = Integer.parseInt(st.nextToken());
        bloc[0] = Integer.parseInt(st.nextToken());
        bloc[1] = Integer.parseInt(st.nextToken());


        System.out.println(solution(board, aloc, bloc));
    }

    public static int solution(int[][] board, int[] aloc, int[] bloc) {
        WF newWF = dfs(board, aloc, bloc, 1, 0);

        return newWF.cnt;
    }

    public static boolean isRange(int y, int x) {
        if (y >= 0 && y < N && x >= 0 && x < M) return true;
        return false;
    }

    public static WF dfs(int[][] board, int[] aloc, int[] bloc, int turn, int cnt) {
        int ay = aloc[0];
        int ax = aloc[1];

        int by = bloc[0];
        int bx = bloc[1];

        if ((turn > 0 && board[ay][ax] == 0) || (turn < 0 && board[by][bx] == 0)) return new WF(false, cnt);

        int min_cnt = Integer.MAX_VALUE;
        int max_cnt = Integer.MIN_VALUE;

        for (int i = 0; i < 4; i++) {
            // a 차례
            if (turn > 0) {
                int ny = ay + dy[i];
                int nx = ax + dx[i];

                if (isRange(ny, nx) && board[ny][nx] == 1) {
                    board[ay][ax] = 0;

                    WF b = dfs(board, new int[]{ny, nx}, bloc, -turn, cnt+1);
                    // 다음 순서가 졌을 경우 이번 순서에 이긴 것.
                    if (b.win == false) {
                        min_cnt = Math.min(b.cnt, min_cnt);
                    }else {
                        max_cnt = Math.max(b.cnt, max_cnt);
                    }

                    board[ay][ax] = 1;
                }
            }else {
                // b 차례
                int ny = by + dy[i];
                int nx = bx + dx[i];

                if (isRange(ny, nx) && board[ny][nx] == 1) {
                    board[by][bx] = 0;

                    WF a = dfs(board, aloc, new int[]{ny, nx}, -turn, cnt + 1);
                    if (a.win == false) {
                        min_cnt = Math.min(a.cnt, min_cnt);
                    }else {
                        max_cnt = Math.max(a.cnt, max_cnt);
                    }

                    board[by][bx] = 1;
                }
            }

        }

        if (min_cnt == Integer.MAX_VALUE && max_cnt == Integer.MIN_VALUE) return new WF(false, cnt);
        else if (min_cnt != Integer.MAX_VALUE) return new WF(true, min_cnt);
        else return new WF(false, max_cnt);

    }
}

