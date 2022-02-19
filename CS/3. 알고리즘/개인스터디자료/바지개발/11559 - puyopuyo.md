## 풀이

```java
package bj_Algorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BOJ11559 {

    static int height = 12;
    static int width = 6;
    static int chainCnt = 0;
    static int[] rangeX = {-1, 0, 1, 0}; // 좌, 상, 우, 하
    static int[] rangeY = {0, 1, 0, -1};
    static char[][] map;
    static boolean[][] visited;
    static List<int[]> list = new ArrayList<>();
    static char poyoColor;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        map = new char[height][width];

        for (int i = 0; i < height; i++) {
            String line = br.readLine();
            for (int j = 0; j < width; j++) {
                map[i][j] = line.charAt(j);
            }
        }

        while (true) {
            boolean isChain = findPuyo();
            if (isChain) {
                dropPuyo();

                chainCnt++;
            } else {
                break;
            }
        }
        System.out.println(chainCnt);
    }

    private static boolean findPuyo() {
        visited = new boolean[height][width];
        boolean isChain = false;

        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {

                if (map[i][j] != '.' && !visited[i][j]) {
                    poyoColor = map[i][j];
                    findSameColorPuyo(i, j);
                }

                if (list.size() >= 4) { // 4개 이상이면 같은 색 뿌요를 터뜨림
                    isChain = true;
                    removePuyo();
                }

                list.clear();
            }
        }
        return isChain;
    }

    private static void findSameColorPuyo(int i, int j) {
        Queue<int[]> queue = new LinkedList<int[]>();
        queue.offer(new int[] {i, j});
        visited[i][j] = true;
        list.add(new int[] {i, j});

        while (!queue.isEmpty()) {
            int[] point = queue.poll();

            for (int k = 0; k < 4; k++) {
                int nx = point[0] + rangeX[k];
                int ny = point[1] + rangeY[k];

                if (check(nx, ny)) {
                    list.add(new int[] {nx, ny});
                    queue.offer(new int[] {nx, ny});
                    visited[nx][ny] = true;

                }
            }
        }
    }

    private static boolean check(int nx, int ny) {
        if (nx < 0 || nx >= height) {
            return false;
        }
        if (ny < 0 || ny >= width) {
            return false;
        }
        if (visited[nx][ny]) {
            return false;
        }
        if (map[nx][ny] != poyoColor) {
            return false;
        }
        return true;
    }

    private static void removePuyo() {
        for (int[] point: list) {
            map[point[0]][point[1]] = '.';
        }
    }

    private static void dropPuyo() {
        for (int i = height-1 ; i >= 0 ; i--) {
            for (int j = width-1 ; j >= 0 ; j--) {

                if (map[i][j] == '.') {
                    for (int k = i -1 ; k >= 0 ; k--) {
                        if (map[k][j] != '.') {
                            map[i][j] = map[k][j];
                            map[k][j] = '.';
                            break;
                        }
                    }
                }
            }
        }
    }

}
```