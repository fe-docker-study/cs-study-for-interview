import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<Integer> pq = new PriorityQueue<>();

        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            pq.offer(Integer.parseInt(br.readLine()));
        }


        int count = 0;
        while (pq.size() != 1 && !pq.isEmpty()) {
            int c1 = pq.poll();
            int c2 = pq.poll();

            count += c1;
            count += c2;

            pq.offer(c1 + c2);
        }

        System.out.println(count);
    }
}
