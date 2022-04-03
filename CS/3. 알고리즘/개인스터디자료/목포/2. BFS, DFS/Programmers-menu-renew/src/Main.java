import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static Map<String, Integer> map;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        List<String> orders = new ArrayList<>();
        List<Integer> courses = new ArrayList<>();

        while (st.hasMoreTokens()) {
            orders.add(st.nextToken());
        }

        st = new StringTokenizer(br.readLine());

        while (st.hasMoreTokens()) {
            courses.add(Integer.parseInt(st.nextToken()));
        }

        String[] result = solution(orders.toArray(new String[orders.size()]),
                courses.toArray(new Integer[courses.size()]));

        for (int i = 0; i < result.length; i++) {
            System.out.print(result[i] + " ");
        }

        /*
         * List<String> list = new Solution().solution(orders.toArray(new
         * String[orders.size()]), courses.toArray(new Integer[courses.size()]));
         * System.out.println(Arrays.toString(list.toArray(new String[list.size()])));
         */
    }

    public static String[] solution(String[] orders, Integer[] course) {

        List<String> list = new ArrayList<>();

        // 1. 조합 작업을 위해 각 문자열 오름차순 정렬
        for (int i = 0; i < orders.length; i++) {
            char[] charArr = orders[i].toCharArray();

            Arrays.sort(charArr);
            orders[i] = String.valueOf(charArr);
        }

        for (int i = 0; i < course.length; i++) {
            map = new HashMap<>();

            int max = Integer.MIN_VALUE;

            for (int j = 0; j < orders.length; j++) {
                StringBuilder sb = new StringBuilder();
                if (course[i] <= orders[j].length()) {
                    combination(sb, orders[j], course[i], 0, 0);
                }
            }

            for (String s : map.keySet()) {
                max = Math.max(max, map.get(s));
            }

            for (String s : map.keySet()) {
                if (max >= 2 && map.get(s) == max) {
                    list.add(s);
                }
            }
        }

        Collections.sort(list);
        return list.toArray(new String[list.size()]);
    }

    public static void combination(StringBuilder sb, String order, int r, int cnt, int idx) {
        if (cnt == r) {
            map.put(sb.toString(), map.getOrDefault(sb.toString(), 0) + 1); // 해당 문자열이 없으면 0으로 요소 추가
            return;
        }

        for (int i = idx; i < order.length(); i++) {
            sb.append(order.charAt(i));
            combination(sb, order, r, cnt + 1, i + 1);
            sb.delete(cnt, cnt + 1);
        }
    }
}
