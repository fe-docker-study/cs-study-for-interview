### 문제

N-Queen 문제는 크기가 N × N인 체스판 위에 퀸 N개를 서로 공격할 수 없게 놓는 문제

### 입력

첫째 줄에 N이 주어진다. (1 ≤ N < 15)

### 출력

첫째 줄에 퀸 N개를 서로 공격할 수 없게 놓는 경우의 수를 출력



### 풀이

```java
package solveAlgorithm;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class BOJ9663 {
    static int n;
    static int cnt;

    public static void main(String[] args) throws NumberFormatException, IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        n = Integer.parseInt(br.readLine());

        for (int i = 0; i < n ; i++) {
            int[] col = new int[n+1];

            col[1] = i + 1; // 처음 퀸을 놓는 위치

            dfs(col, 1);
        }

        System.out.print(cnt);

    }

    private static void dfs(int[] col, int row) {
        if (row == n) { //모든 행을 다 채우면 카운트 증가
            cnt++;
            return;
        }

        for (int i = 1; i <= n ; i++) {// 열 탐색 1~n
            col[row+1] = i;

            //해당 열의 i번째 행에 퀸을 놓을 수 있는지 확인, 있으면 다음 행의 dfs 호출
            if (isPossible(col, row+1)) {
                dfs(col, row+1);
            }
        }
    }

    private static boolean isPossible(int[] col, int row) {

        for (int i = 1; i < row ; i++) {

            if (col[i] == col[row]) { // 같은 열이면 퀸을 놓을 수 없음
                return false;
            }

            if (Math.abs(i - row) == Math.abs(col[i] - col[row])) { // 대각선에 위치하면 퀸을 놓을 수 없음(열의 차와 행의 차가 같은 경우 대각선임)
                return false;
            }
        }

        return true;
    }
}
```