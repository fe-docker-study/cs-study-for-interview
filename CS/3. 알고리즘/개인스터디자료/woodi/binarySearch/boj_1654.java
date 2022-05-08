import java.util.Arrays;
import java.util.Scanner;

public class Main {
	public static boolean canMakeN(long len, int n, long[] lengths) {
		long sum = 0;
		for(int i=0; i<lengths.length; i++) {
			sum += (lengths[i]/len);
		}
		if(sum>=n) return true;
		return false;
	}
	
	public static long binarySearch(int k, int n, long[] lengths) {
		Arrays.sort(lengths);	// O(nlogn)
		long s = 1;    // 초기값은 자연수이므로 0이 아닌 1
		long e = lengths[k-1];
		
		while(s<=e) {    // 여기서 <로 하면 틀림
			long len = (s+e)/2;
			if(canMakeN(len, n, lengths)) {
				s=len+1;
			}else {	// 랜선 길이를 줄어야 함
				e=len-1;
			}
		}
		return e;
	}
	
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		int k = sc.nextInt();
		int n = sc.nextInt();
		long[] lengths = new long[k];
		for(int i=0; i<k; i++) {
			lengths[i] = sc.nextLong();
		}
//		int result = bruteForce(k,n, lengths);
		long result = binarySearch(k,n, lengths);
		System.out.println(result);
	}
}

