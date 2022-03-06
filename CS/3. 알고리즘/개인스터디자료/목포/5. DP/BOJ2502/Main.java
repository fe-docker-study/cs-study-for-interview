import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

/**
 * JONGOL 1997
 */

public class Main {
    public static void main(String[] args) throws Exception {
        int A = 0, B = 0;

        // 할머니가 넘어온 날
        int day = 0;

        // 그 날 호랑이에게 준 떡 개수
        int tteok = 0;

        // 방정식에 넣어서 게산한 떡의 개수
        int sum  = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
        StringTokenizer st = new StringTokenizer(br.readLine());

        day = Integer.parseInt(st.nextToken());
        tteok = Integer.parseInt(st.nextToken());

        //A 항의 계수
        int x = fibo(day -2);
				//B 항의 계수
        int y = fibo(day -1);

        while(true) {
            sum = 0;
            sum += x * A; // A항을 먼저 계산 후 

            while(true) { // B항의 값을 완전 탐색한다.
                sum += y * B;

                if(sum == tteok) {
                    bw.write(A + " " + B);
                    bw.flush();
                    bw.close();
                    return;
                }

                if(sum > tteok) { // 결과가 떡의 개수보다 커지면 B를 초기화하고 A를 증가시킨 후 다시 계산
                    B = 0;
                    break;
                }

								// 이전 B항의 값을 다시 뺀 뒤 B를 증가 후 다시 계산
                sum -= y * B;
                B++;
            }
            A++;
        }
    }

		// 피보나치 수열의 n항 값을 구하는 함수 
    public static int fibo(int n) {
        int next = 1;
        int term1 = 1, term2 = 1;

        if(n == 1 || n == 2) return next;

        for(int i = 1; i < n-1; i++) {
            next = term1  + term2;
            term1 = term2;
            term2 = next;
        }

        return next;
    }
}