import java.util.PriorityQueue;
import java.util.Scanner;

/**
 * 모든 묶음 중 우선적으로 더할 2개의 묶음 선택 -> 가장 작은거 2개 선택
 *
 */
public class Main {
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int n = sc.nextInt();
		PriorityQueue<Integer> pq = new PriorityQueue<>();
		
		for(int i=0; i<n; i++) {
			pq.add(sc.nextInt());
		}
		
		int cnt = 0;
		
		while(pq.size()>1) {
			int b1 = pq.remove();
			int b2 = pq.remove();
			int total = b1+b2;
			cnt += total;
			pq.add(total);
		}
		
		System.out.println(cnt);
		
	}
}
