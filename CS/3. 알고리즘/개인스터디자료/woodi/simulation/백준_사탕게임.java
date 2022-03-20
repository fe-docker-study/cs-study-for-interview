import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	static int n;
	public static boolean isOutOfBound(int x, int y) {
		if(x<0 || x>=n || y<0 || y>=n) {
			return true;
		}
		return false;
	}
	public static int getLongestLength(char[][] map) {
		// 행 또는 열에서 가장 긴 길이 찾기 
		// 행에서 가장 긴 길이
		int rowMax = 0;
		for(int i=0; i<n; i++) {
			int len = 1;
			char candy = map[i][0];
			for(int j=1; j<n; j++) {
				if(map[i][j] == candy) {
					len ++;
					rowMax = Math.max(rowMax, len);
				}else {
					len = 1;
					candy = map[i][j];
				}
			}
		}
		
		int colMax=0;
		for(int j=0; j<n; j++) {
			int len = 1;
			char candy = map[0][j];
			for(int i=1; i<n; i++) {
				if(map[i][j] == candy) {
					len ++;
					colMax = Math.max(colMax, len);
				}else {
					len = 1;
					candy = map[i][j];
				}
			}
		}
		
		return Math.max(rowMax, colMax);
	}
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		n = Integer.parseInt(br.readLine());
		char[][] map = new char[n][n];
		
		for(int i=0; i<n; i++) {
			String row = br.readLine();
			for(int j=0; j<n; j++) {
				map[i][j] = row.charAt(j);
			}
		}
		
		// 색이 다른 인접한 두 칸 바꿨을 때 최대 연속 길이 
		int[] dx = {1, -1, 0, 0};
		int[] dy = {0, 0, 1, -1};
		
		int max = 0;
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				for(int k=0; k<4; k++) {
					int x = i + dx[k];
					int y = j + dy[k];
					
					if(!isOutOfBound(x, y) && map[x][y] != map[i][j]) {
						// 위치 바꾸기
						char temp = map[x][y];
						map[x][y] = map[i][j];
						map[i][j] = temp;
						
						
						// 최대 연속 길이 구하기 
						
						int len = getLongestLength(map);
//						printMap(map);
//						System.out.println("길이 : "+len);
//						System.out.println("변경 : ("+i+", "+j+") <--> ("+x+", "+y+")");
						
						max = Math.max(max, len);
						
						// 위치 돌려놓기
						temp = map[x][y];
						map[x][y] = map[i][j];
						map[i][j] = temp;
						
					}
					
				}
			}
		}
		
		System.out.println(max);
		
	}
	
	public static void printMap(char[][] map) {
		System.out.println("================");
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				System.out.print(map[i][j]);
			}
			System.out.println();
		}
		
	}
}

