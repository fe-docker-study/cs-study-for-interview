import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	// 북, 동 , 남, 서
	static int[] dr = {-1, 0, 1, 0};
	static int[] dc = {0, 1, 0, -1};
	static String[] direct = {"북", "동", "남", "서"};
	static int n;
	static int m;
	
	public static boolean isOutOfMap(int r, int c) {
		if(r>=n || r<0 || c>=m || c<0) {
			return true;
		}
		return false;
	}
	//로봇 청소기가 청소하는 칸의 개수 반환
	public static int cleanRoom(int[][] map, boolean[][] check, int r, int c, int d) {
		int cnt = 0;
		while (true) {
			
			// 현재 위치를 청소한다.
			if(!check[r][c]) {
				check[r][c] = true;
				cnt ++;
			}
			
//			printMap(check, r, c, d);
			// 현재 위치에서 현재 방향을 기준으로 왼쪽 방향부터 차례대로 인접한 칸을 탐색한다. 
			
			int rotate = 1;
			while(rotate<=5) {
				// 회전
				int nd = (d-rotate+8)%4;
				
				// 그 방향으로 전진
				int nr = r + dr[nd];
				int nc = c + dc[nd];
//				System.out.println(direct[nd]);
				
				// 청소할 공간 x
				if(isOutOfMap(nr, nc) || map[nr][nc] == 1 || check[nr][nc]) {
					rotate ++;
					continue;
				}else {
					// 청소할 공간 o
					r = nr;
					c = nc;
					d = nd;
//					check[nr][nc] = true;
					break;
				}
			}
			
			// 4 방향 다 돌아도 청소할 공간 없음 -> 후진
			if(rotate == 6) {
//				System.out.println("4 방향 다 돌아도 청소할 공간 없음 ");
//				printMap(check, r, c, d);
				// 후진
				int nr = r + dr[(d+2)%4];
				int nc = c + dc[(d+2)%4];
				// 후진 가능
				if(!isOutOfMap(nr, nc) && map[nr][nc] == 0) {
//					System.out.println("후진");
					r = nr;
					c = nc;
				}else {
//					System.out.println("후진 못함");
					// 후진도 못하면 작동 멈춤
					break;
				}
			}
		}
		
		return cnt;
		
	}
//	public static void printMap(boolean[][] map, int r, int c, int d) {
//		for(int i=0; i<n; i++) {
//			for(int j=0; j<m; j++) {
//				if(i==r && j==c) {
//					System.out.print(direct[d]+" ");
//				}else {
//					if(map[i][j]) {
//						System.out.print("1 ");
//					}else {
//						System.out.print("0 ");
//					}
//				}
//			}
//			System.out.println();
//		}
//		System.out.println("=============");
//	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		st = new StringTokenizer(br.readLine());
		n = Integer.parseInt(st.nextToken());
		m = Integer.parseInt(st.nextToken());
		
		int[][] map = new int[n][m];
		boolean[][] check = new boolean[n][m];
		
		// 로봇 위치 + 방향
		st = new StringTokenizer(br.readLine());
		int r = Integer.parseInt(st.nextToken());
		int c = Integer.parseInt(st.nextToken());
		int d = Integer.parseInt(st.nextToken());
		
		// 빈 칸 : 0, 벽 : 1
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<m; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
				if(map[i][j] == 1) {
					check[i][j] = true;	// 벽이면 방문 여부도 true로 한다. 한 번 방문한 곳은 다시 방문 안하므로
				}
			}
		}
		
//		System.out.println("시작: ("+r+", "+c +"), 방향 : "+direct[d]);
//		printMap(check, r, c, d);
		//로봇 청소기가 청소하는 칸의 개수 반환
		int ans = cleanRoom(map, check, r, c, d);
		System.out.println(ans);
		
	}
}




