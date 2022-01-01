```java
import java.util.Scanner;

public class Main {
	static int n;
	static int rst;
	static boolean[][] map;
	public static void dfs(int depth) {
		if(depth == n) {
			rst ++;
			return;
		}
		
		for(int c=0; c<n; c++) {
			if(canPut(depth, c)) {
				// put
				map[depth][c] = true;
				// dfs
				dfs(depth+1);
				// remove
				map[depth][c] = false;
			}
		}
		
	}
	
	// r,c 위치에 퀸을 놓을 수 있나
	public static boolean canPut(int r, int c) {
		for(int i=0; i<r; i++) {
			if(map[i][c]== true) return false;
			int dist = r-i;
			if((c-dist)>=0 && map[i][c-dist]== true) return false;
			if((c+dist)<n && map[i][c+dist]== true) return false;
		}
		return true;
	}
	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		n = sc.nextInt();
		map = new boolean[n][n];
		
		rst = 0;
		// 퀸 n개를 서로 공격할 수 없게 놓기 => 퀸을 놓는 방법의 수 
		dfs(0);
		System.out.println(rst);
	}
}






```
