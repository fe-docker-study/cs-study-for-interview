import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

/**
 * BOJ 2110 공유기 설치
 * https://www.acmicpc.net/problem/2110
 */

public class Main {
    public static void main(String[] args) throws IOException {
        int N, C = 0; // N 집의 개수, C 공유기의 개수

        int arr[]; // 집의 좌표 배열

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        arr = new int[N];

        for (int i = 0; i < N; i++) {
            arr[i] = Integer.parseInt(br.readLine());
        }

        Arrays.sort(arr);

        int low = 1;
        int high = arr[N - 1] - arr[0];
        int result = 0;

        while (low <= high) {
            int mid = (low + high) / 2;

            int cnt = 1;
            int prev_house = arr[0];
            for (int i = 1; i < N; i++) {
                if (arr[i] - prev_house >= mid) {
                    cnt++;
                    prev_house = arr[i];
                }
            }

            if (cnt >= C) {
                result = Math.max(result, mid);
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        System.out.println(result);
    }
}
