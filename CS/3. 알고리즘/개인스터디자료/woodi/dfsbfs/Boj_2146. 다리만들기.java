import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static int[][] map;
	static boolean[][] visited;
	static int landNum = 2;
	static int[] dx = {1, -1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	static int minDist = Integer.MAX_VALUE;
	static class Point{
		int r;
		int c;
		int cnt;
		public Point(int r, int c, int cnt) {
			this.r = r;
			this.c = c;
			this.cnt = cnt;
		}
	}
	
	public static boolean isOutOfBound(int r, int c) {
		if(r<0 || c<0 || r>=n || c>=n) {
			return true;
		}
		return false;
	}
	
	public static void makeLand(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r, c, 0));
		visited[r][c] = true;
		map[r][c] = landNum;
		
		while(!q.isEmpty()) {
			Point now = q.remove();
			for(int i=0; i<4; i++) {
				int nr = now.r+dx[i];
				int nc = now.c+dy[i];
				// 바다가 아닌 & 아직 방문 안한 섬
				if(!isOutOfBound(nr, nc) && !visited[nr][nc] && map[nr][nc] == 1) {
					visited[nr][nc] = true;
					map[nr][nc] = landNum;
					q.add(new Point(nr, nc, 0));
				}
			}
		}
		landNum++;
	}
	
	public static void bfs(int r, int c) {
		Queue<Point> q = new LinkedList<>();
		q.add(new Point(r,c,0));
		
		int currentLandNum = map[r][c];
		visited[r][c] = true;
		while(!q.isEmpty()) {
			Point now = q.remove();
			for(int i=0; i<4; i++) {
				int nr = now.r + dx[i];
				int nc = now.c + dy[i];
				if(!isOutOfBound(nr, nc) && !visited[nr][nc] && map[nr][nc]!=currentLandNum) {
					visited[nr][nc] = true;
					if(map[nr][nc] == 0) {
						q.add(new Point(nr, nc, now.cnt +1));
					}else {	// 다른 섬
						minDist = Math.min(minDist, now.cnt);
					}
				}
			}
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		map = new int[n][n];
		visited = new boolean[n][n];
		
		for(int i=0; i<n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<n; j++) {
				map[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 섬 번호 세팅 : 서로 다른 섬 => 다른 번호
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] == 1) {
					makeLand(i, j);
				}
			}
		}
		
//		printMap();
		
		// 섬 간 거리 구하기
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(map[i][j] >=2) {
					visited = new boolean[n][n];
					bfs(i, j);
				}
			}
		}
		
		System.out.println(minDist);
	}
	
	public static void printMap() {
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		
	}
}
