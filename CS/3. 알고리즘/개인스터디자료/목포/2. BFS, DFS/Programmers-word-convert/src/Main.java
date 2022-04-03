import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        String begin = st.nextToken();
        String target = st.nextToken();
        List<String> words = new ArrayList<>();

        st = new StringTokenizer(br.readLine());

        while (st.hasMoreTokens()) {
            words.add(st.nextToken());
        }

        String[] temp = new String[words.size()];
        words.toArray(temp);

        System.out.println(solution(begin, target, temp));
    }

    public static int solution(String begin, String target, String[] words) {
        boolean[] visited = new boolean[words.length];

        Queue<Bulk> q = new LinkedList<>();
        q.offer(new Bulk(begin, 0));
        while (!q.isEmpty()) {
            Bulk index = q.poll();

            String str = index.str;
            int cnt = index.cnt;

            if (target.equals(str)) {
                return cnt;
            }

            for (int i = 0; i < words.length; i++) {
                if(!visited[i] && isChangable(str, words[i])) {
                    visited[i] = true;
                    q.offer(new Bulk(words[i], cnt + 1));
                }
            }
        }
        return 0;
    }

    public static boolean isChangable(String s1, String s2) {
        int cnt = 0;
        for (int i = 0; i < s1.length(); i++) {
            if(s1.charAt(i) != s2.charAt(i)) cnt++;
        }

        if (cnt == 1) return true;
        return false;
    }
}

class Bulk {
    String str;
    int cnt;

    public Bulk(String str, int cnt) {
        this.str = str;
        this.cnt = cnt;
    }
}



/*

1차 시도.. 아무 생각없이 26가지의 숫자를 다 바꿀 생각을 했다..;

for (int j = 0; j < 26; j++) {
    char change = (char) (97 + j);

    if (str.charAt(i) != change) {
        char[] charArr = str.toCharArray();
        charArr[i] = change;
        String changed = String.valueOf(charArr);

        if (wordList.contains(changed)) {
            q.offer(new Bulk(changed, cnt + 1));
        }
    }
}*/
