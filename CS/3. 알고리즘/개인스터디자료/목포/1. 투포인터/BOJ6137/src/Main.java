import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {

        int N = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine());


        char[] S = new char[N];
        List<Character> list = new ArrayList<>();

        for (int i = 0; i < N; i++) {
           S[i] = br.readLine().charAt(0);
        }

        int low = 0, high = N - 1;

        while(low <= high) {
            if(S[low] < S[high]) list.add(S[low++]); // 왼 쪽 글자가 더 빠른 사전순일 때
            else if(S[low] == S[high]) {
                int tempL = low + 1, tempH = high - 1, size = list.size();

                while(tempL <= tempH) {
                    if(tempL < tempH) {
                        list.add(S[low++]);
                        break;
                    }
                    else if(tempL > tempH) {
                        list.add(S[high--]);
                        break;
                    }
                    else tempL++; tempH--;
                }

                if(list.size() == size) list.add(S[low++]);
            }else if(S[low] > S[high]) list.add(S[high--]); // 뒤 쪽 글자가 더 빠른 사전순일 때
        }

        for (int i = 0; i < N; i++) {
            if(i!=0 && i%80==0) System.out.println();
            System.out.print(list.get(i));
        }



    }
}
