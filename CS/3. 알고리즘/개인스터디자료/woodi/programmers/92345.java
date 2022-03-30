package programmers;

import java.util.Arrays;

/**

보드 칸이 크지 않으므로 완전 탐색


 * 칸이 홀수 : 먼저 시작하는 애가 이김
 * 칸이 짝수 : 먼저 시작하는 애가 짐
 * 항상 A가 먼저 시작함
 * 
 * 항상 이기는 애는 최대한 빨리 이길 수 있는 방법으로 
 * 항상 지는 애는 최대한 오래 끌 수 있는 방법으로 게임 진행함
 * 총 이동 횟수를 구하시오
 * 
 * 
 * 참고 : https://tech.kakao.com/2022/01/14/2022-kakao-recruitment-round-1/
 */
public class Programmers_92345 {
	static int[] dx = {-1,1, 0, 0};
	static int[] dy = {0, 0, 1, -1};
	
	public static boolean isOutOfBound(int[][] board, int[] loc) {
		if(loc[0] < 0 || loc[0]>=board.length || loc[1] < 0 || loc[1]>=board[0].length) {
			return true;
		}
		return false;
	}
	
	
	public static int[] moveB(int[][] board, int[] aloc, int[] bloc, int cnt) {
//		System.out.println("========B 차례. 현재 위치  A :"+Arrays.toString(aloc)+" B:"+Arrays.toString(bloc));
		
		int x = bloc[0];
		int y = bloc[1];
		
		if(board[x][y]==0) {
//			System.out.println("B의 자리 0 됨-> 패배");
			// 이기면 1, 지면 0
			return new int[] {0, cnt};
		}
		
		board[x][y] = 0;
		int[] result = new int[2];
		
		int winMin = Integer.MAX_VALUE;	// 최소한으로 이김
		int loseMax = Integer.MIN_VALUE;	// 최대한으로 짐
		boolean flag = false;
		
		for(int i=0; i<4; i++) {
			int nx = bloc[0] + dx[i];
			int ny = bloc[1] + dy[i];
			int[] newBloc = {nx, ny};
			
			if(isOutOfBound(board, newBloc) || board[nx][ny]==0) {
				continue;
			}
			flag = true;
//			System.out.println("B 이동 : ("+x+", "+y+") -> ("+nx+", "+ny+") ");
			result = moveA(board, aloc, newBloc, cnt+1);	// a의 승패 결과 반환
			
			if(result[0] == 0) {
				winMin = Math.min(winMin, result[1]);
			}else {
				loseMax = Math.max(loseMax, result[1]);
			}
			
//			System.out.println("!!!B 이동 : ("+x+", "+y+") -> ("+nx+", "+ny+"), A 승패 결과 : "+result[0]+", cnt:"+result[1]);
		}
		board[x][y] = 1;
		
		if(flag == false) {	// 더 이상 이동 불가능
//			System.out.println("B 더 갈 곳이 없음");
			return new int[] {0, cnt};
		}
		
		if(winMin==Integer.MAX_VALUE) {	// 이길 수 있는 방법 x => 질 수 밖에 없음
//			System.out.println("B가 질 수 밖에 없음. 최대 이동 거리 : "+ loseMax);
			return new int[] {0, loseMax};
		}
		
		// 이길 수 있는 방법이 있음
//		System.out.println("B가 이길 수 있음. 최소 이동 거리 : "+ winMin);
		return new int[] {1, winMin};
		
	}
	
	// A의 승패 여부와 총 이동 횟수를 반환
	public static int[] moveA(int[][] board, int[] aloc, int[] bloc, int cnt) {
//		System.out.println("========A 차례. 현재 위치  A :"+Arrays.toString(aloc)+" B:"+Arrays.toString(bloc));
		
		int x = aloc[0];
		int y = aloc[1];
		
		if(board[x][y]==0) {
//			System.out.println("A의 자리 0 됨 -> 패배");
			// 이기면 1, 지면 0
			return new int[] {0, cnt};
		}
		
		board[x][y] = 0;
		boolean flag = false;
		int winMin = Integer.MAX_VALUE;	// 최소한으로 이김
		int loseMax = Integer.MIN_VALUE;	// 최대한으로 짐
		int[] result = new int[2];
		for(int i=0; i<4; i++) {
			int nx = aloc[0] + dx[i];
			int ny = aloc[1] + dy[i];
			
			int[] newAloc = {nx, ny};
			
			if(isOutOfBound(board, newAloc) || board[nx][ny]==0) {
				continue;
			}
			
			flag = true;
//			System.out.println("A 이동 : ("+x+", "+y+") -> ("+nx+", "+ny+") ");
			result = moveB(board, newAloc, bloc, cnt+1);	// b의 승패 결과 반환'
			if(result[0] == 0) {
				winMin = Math.min(winMin, result[1]);
			}else {
				loseMax = Math.max(loseMax, result[1]);
			}
			
			
//			System.out.println("A 이동 : ("+x+", "+y+") -> ("+nx+", "+ny+"), B 승패 결과 : "+result[0]+", cnt:"+result[1]);
		}
		board[x][y] = 1;
		
		if(flag == false) {	// 더 이상 이동 불가능
//			System.out.println("A 더 갈 곳이 없음. cnt 반환 :"+cnt);
			return new int[] {0, cnt};
		}
		
		
		if(winMin==Integer.MAX_VALUE) {	// 이길 수 있는 방법 x => 질 수 밖에 없음
//			System.out.println("A는 질 수 밖에 없음. 최대 이동 거리 :"+loseMax);
			return new int[] {0, loseMax};
		}
		
		// 이길 수 있는 방법이 있음
//		System.out.println("A는 이길 수있는 방법이 있음. 최소 이동 거리 :"+winMin);
		return new int[] {1, winMin};
		
	}
	
	public static int solution(int[][] board, int[] aloc, int[] bloc) {
        int answer = -1;
        // 칸 갯수 세기 -> 홀수면 a가 이김, 짝수면 b가 이김
        int[] result = moveA(board, aloc, bloc, 0);
        
        answer = result[1];
        return answer;
    }
	
	public static void main(String[] args) {
//		int[][] board = {{1, 1, 1}, {1, 1, 1}, {1, 1, 1}};
//		int[] aloc = {1,0};
//		int[] bloc = {1,2};
		
		int[][] board = {{1, 1, 1}, {1, 0, 1}, {1, 1, 1}};
		int[] aloc = {1,0};
		int[] bloc = {1,2};
		
		int result = solution(board, aloc, bloc);
		System.out.println(result);
	}
}
