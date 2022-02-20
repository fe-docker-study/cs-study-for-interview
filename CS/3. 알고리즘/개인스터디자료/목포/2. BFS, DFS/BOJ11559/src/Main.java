import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    final static int B_ROW = 12;
    final static int B_COL = 6;

    static boolean[][] visited;
    static char[][] board = new char[B_ROW][B_COL];
    static Queue<int[]> q = new LinkedList<>();

    static int[] dy = {1, -1, 0, 0}; // row
    static int[] dx = {0, 0, 1, -1}; // col

    static boolean isDestroyed = false;
    static int dCnt = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String line;

        for (int i = 0; i < B_ROW; i++) {
            line = br.readLine();
            for (int j = 0; j < B_COL; j++) {
                board[i][j] = line.charAt(j);
            }
        }

        while(true) {
            isDestroyed = false;
            visited = new boolean[B_ROW][B_COL];

            // 덩어리 탐색
            for (int i = 0; i < B_ROW; i++) {
                for (int j = 0; j < B_COL; j++) {
                    if (board[i][j] != '.') {
                        bfs(i, j, board[i][j]);
                    }
                }
            }


            // 중력 처리
            for (int i = B_ROW - 2; i >=0; i--) {
                for(int j = 0; j < B_COL; j++) {
                    if (board[i][j] != '.' && board[i + 1][j] == '.') {
                        puyo_down(i, j);
                    }
                }
            }


            if(isDestroyed)
                dCnt++;
            else
                break;
        }

        System.out.println(dCnt);
    }

    public static void bfs(int s_row, int s_col, char color) {
        List<int[]> checked_puyo = new ArrayList<>();

        q.offer(new int[]{s_row, s_col});

        while(!q.isEmpty()) {
            int[] temp = q.poll();

            for (int i = 0; i < 4; i++) {
                int next_row = temp[0] + dy[i];
                int next_col = temp[1] + dx[i];

                if(check_range(next_row, next_col)
                        && board[next_row][next_col] == color
                        && !visited[next_row][next_col]
                ) {
                    q.offer(new int[]{next_row, next_col});
                    visited[next_row][next_col] = true;
                    checked_puyo.add(new int[]{next_row, next_col});
                }
            }

        }

        // 4개 이상의 뿌요가 모였으면 삭제
        if(checked_puyo.size() >= 4) {
            for (int i = 0; i < checked_puyo.size(); i++) {
                int[] p = checked_puyo.get(i);
                board[p[0]][p[1]] = '.';
                isDestroyed = true;
            }
        }
    }

    public static void puyo_down(int y, int x) {

        boolean completed = true;
        while(completed) {
            if (y + 1 < B_ROW) {
                if (board[y + 1][x] == '.') {
                    board[y + 1][x] = board[y][x];
                    board[y][x] = '.';
                    y++;
                } else {
                    completed = false;
                }
            }else{
                break;
            }

        }
    }

    public static boolean check_range(int y, int x) {
        if (y >= 0 && y < B_ROW && x >= 0 && x < B_COL) return true;
        else return false;
    }

    public static void printBoard() {
        for (int i = 0 ; i < B_ROW; i++) {
            for (int j = 0; j < B_COL; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
}
