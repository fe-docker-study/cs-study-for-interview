import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	static class Matrix{
		int c1;
		int c2;
		int operCnt;
		public Matrix(int c1, int c2, int operCnt) {
			this.c1 = c1;
			this.c2 = c2;
			this.operCnt = operCnt;
		}
		
		public Matrix operation(Matrix o) {
			Matrix m = new Matrix(this.c1, o.c2, this.c1 * this.c2 * o.c2);
			return m;
		}
		
//		public int operCnt(Matrix o) {
//			return this.c1 * this.c2 * o.c2;
//		}
		public String toString() {
			return this.c1+"x"+this.c2+" & operCnt: "+this.operCnt;
			
		}
	}
	static Matrix[][] dp;		// dp[i][j] : i~j까지 연산 결과
	
	static Matrix dfs(int start, int end, List<Matrix> list) {
//		System.out.println("start : "+start+", end:"+end);
		if(start+1 == end) {
			Matrix m1 = list.get(start);
			Matrix m2 = list.get(end);	// end : 바로 앞(start)를 제거했으므로, 여기가 원래 end 지점이 된다. 
			
			Matrix result = m1.operation(m2);
//			System.out.println(m1+", "+m2+", result = "+result);
			dp[start][end] = result;
			
			return dp[start][end];
			
		}
		
		if(dp[start][end] != null) return dp[start][end];
		
		Matrix r1 = null;
		Matrix r2 = null;
		Matrix min = new Matrix(0,0,Integer.MAX_VALUE);
		for(int i=start; i<end; i++) {
			Matrix m1 = dfs(start, i, list);
//			System.out.println(start+"~"+i+" => "+m1);
			Matrix m2 = dfs(i+1, end, list);
//			System.out.println((i+1)+"~"+end+" => "+m1);
			int sum = m1.operCnt + m2.operCnt;
			
			Matrix result = m1.operation(m2);
			result.operCnt += sum;
			
//			System.out.println("m1 : "+m1+", m2 : "+m2+", result = "+result);
			if(min.operCnt > result.operCnt) {
				r1 = m1;
				r2 = m2;
				min = result;
			}
		}
		dp[start][end] = min;
		return min;
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		int n = Integer.parseInt(br.readLine());		// 행렬의 개수
		List<Matrix> list = new ArrayList<>();		// 행렬 담을 list
		dp = new Matrix[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			int c1 = Integer.parseInt(st.nextToken());
			int c2 = Integer.parseInt(st.nextToken());
			
			list.add(new Matrix(c1, c2, 0));
		}
		
		for(int i=0; i<list.size(); i++) {
//			System.out.println(list.get(i));
			dp[i][i] = list.get(i);
		}
		
//		System.out.println("=================");
		Matrix result = dfs(0,n-1,list);
		System.out.println(dp[0][n-1].operCnt);
	}
}
