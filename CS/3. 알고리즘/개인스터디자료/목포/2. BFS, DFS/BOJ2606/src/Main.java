import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;


/**
 * BOJ 2606 - 바이러스
 * 시작 정점에서
 */

public class Main {

    static int n, m; // n : 컴퓨터의 수, m : 연결된 컴퓨터 쌍의 수
    static int count = 0;
    static ArrayList<Integer>[] a;
    static boolean visited[];

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        n = Integer.parseInt(br.readLine());
        m = Integer.parseInt(br.readLine());

        a = new ArrayList[n + 1];
        visited = new boolean[n + 1];

        for (int i = 1; i <= n; i++) {
            a[i] = new ArrayList<>();
        }

        StringTokenizer st;
        for (int i = 0; i < m; i++) {
            st = new StringTokenizer(br.readLine());

            int front = Integer.parseInt(st.nextToken());
            int back = Integer.parseInt(st.nextToken());

            a[front].add(back);
            a[back].add(front);
        }

        System.out.println(DFS(1));
    }

    public static int DFS(int v) {
        visited[v] = true;
        for(int point : a[v]) {
            if(visited[point] == false) {
                count++;
                DFS(point);
            }

        }

        return count;
    }
}
