import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        int N, S, R; // 팀의 수, 카약이 손상된 팀의 수, 카약을 하나 더 가져온 팀의
        int cnt = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        S = Integer.parseInt(st.nextToken());
        R = Integer.parseInt(st.nextToken());

        int[] breakTeam = new int[S];
        boolean[] rList = new boolean[N + 1];

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < S; i++){
            breakTeam[i] = Integer.parseInt(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        for (int i = 0; i < R; i++) {
            rList[Integer.parseInt(st.nextToken())] = true;
        }

        Arrays.sort(breakTeam);

        for (int i = 0; i < S; i++) {
            int temp = breakTeam[i];

            if (rList[temp]) { // 자기 팀이 부서졌고 그 팀이 여분의 보트가 있으면 사용처리
                rList[temp] = false;
            }else {
                if (temp > 1 && rList[temp - 1]) {
                    rList[temp - 1] = false;
                }else {
                  if(temp != N && rList[temp + 1]) {
                      rList[temp + 1] = false;
                  }else {
                      cnt++;
                  }
                }
            }
        }
        System.out.println(cnt);

    }
}
