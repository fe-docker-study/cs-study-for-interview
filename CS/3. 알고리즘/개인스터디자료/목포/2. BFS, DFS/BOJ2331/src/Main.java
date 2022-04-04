import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static List<Integer> list = new ArrayList<>();
    static int res = 0;
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int start = Integer.parseInt(st.nextToken());
        int pow = Integer.parseInt(st.nextToken());

        list.add(start);

        dfs(start, pow);
        System.out.println(res);
    }

    public static void dfs(int val, int pow) {
        String toString = String.valueOf(val);

        int next = 0;
        for(int i = 0; i < toString.length(); i++) {
            next += Math.pow(toString.charAt(i) - '0', pow);
        }

        if (list.contains(next)) {
            res = list.indexOf(next);
            return;
        }else {
            list.add(next);
            dfs(next, pow);
        }
    }
}
