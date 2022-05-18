import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int[][] wheel = new int[4][8];
    static int[] isValid;

    public static void main(String[] args) throws IOException {

        int score = 0;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        for (int i = 0; i < 4; i++) {
            String cInfos = br.readLine();

            for (int j = 0; j < 8; j++) {
                wheel[i][j] = cInfos.charAt(j) - '0';
            }
        }

        int round = Integer.parseInt(br.readLine());

        for (int i = 0; i < round; i++) {
            String[] input = br.readLine().split(" ");
            isValid = new int[4];

            int wheelNum = Integer.parseInt(input[0]) - 1;
            int dir = Integer.parseInt(input[1]);

            check(wheelNum, dir);
            rotation();
        }

        System.out.println(calc());
    }

    static int calc() {
        int sum = 0;
        for (int i = 0; i < 4; i++) {
            int num = wheel[i][0];

            if (num == 1) {
                sum += Math.pow(2, i);
            }
        }
        return sum;
    }

    static void check(int wheelNum, int dir) {
        isValid[wheelNum] = dir;

        int prev = wheelNum - 1;
        int next = wheelNum + 1;

        if (prev >= 0 && isValid[prev] == 0) { // 아직 회전정보가 없다면
            if (wheel[prev][2] != wheel[wheelNum][6]) {
                check(prev,dir * -1);
            }
        }

        if (next < 4 && isValid[next] == 0) {
            if (wheel[next][6] != wheel[wheelNum][2]) {
                check(next, dir * -1);
            }
        }
    }

    static void rotation() {
        for (int i = 0; i < 4; i++) {
            if (isValid[i] != 0) {
                int[] temp = new int[8];

                int idx;
                for (int j = 0; j < 8; j++) {
                    idx = j + isValid[i];

                    if (idx == -1) {
                        idx = 7;
                    } else if (idx == 8) {
                        idx = 0;
                    }

                    temp[idx] = wheel[i][j];
                }

                wheel[i] = temp;
            }
        }
    }

    public static boolean isS(int n) {
        if (n == 1) return true;
        return false;
    }


    /**
    public static void rotation() {
        for(int i = 0; i < 4; i++) {
            int[] temp = new int[8];

            if (isValid[i] == 1) { // 시계 방향
                temp[0] = wheel[i][7];

                for (int j = 0; j < 7; j++) {
                    temp[j + 1] = wheel[i][j];
                }
            }else if(isValid[i] == -1) {
                temp[7] = wheel[i][0];

                for (int j = 0; j < 7; j++) {
                    temp[j] = wheel[i][j + 1];
                }
            }

            wheel[i] = temp;
        }
    }
    */

}
