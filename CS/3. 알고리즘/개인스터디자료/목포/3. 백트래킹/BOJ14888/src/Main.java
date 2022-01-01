import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


/**
 * BOJ 14888 - https://www.acmicpc.net/problem/14888
 */
public class Main {
    static int MAX = Integer.MIN_VALUE;
    static int MIN = Integer.MAX_VALUE;
    static int N = 0;
    static int[] num;
    static int[] operations = new int[4];
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        N = Integer.parseInt(br.readLine());
        num = new int[N];

        StringTokenizer st = new StringTokenizer(br.readLine());

        for (int i = 0; i < N; i++) {
            num[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < 4; i++) {
            operations[i] = Integer.parseInt(st.nextToken());
        }


        DFS(num[0], 1);

        System.out.println(MAX);
        System.out.println(MIN);
    }

    public static void DFS(int res, int count) {
        if(count == N) {
            if(res < MIN) MIN = res;
            if(res > MAX) MAX = res;
            return;
        }

        for (int i = 0; i < 4; i++) {

            if(operations[i] > 0) {

                operations[i]--;

                switch (i) {
                    case 0:
                        DFS(res + num[count], count + 1);
                        break;
                    case 1:
                        DFS(res - num[count], count + 1);
                        break;
                    case 2:
                        DFS(res * num[count], count + 1);
                        break;
                    case 3:
                        DFS(res / num[count], count + 1);
                        break;
                }

                operations[i]++;
            }
        }
    }
}
