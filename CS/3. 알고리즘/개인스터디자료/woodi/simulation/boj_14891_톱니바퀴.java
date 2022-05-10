import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
	
	public static void rotate(int[][] tlist, int num, int dir, boolean[] ischanged) {
		List<Integer> together = new ArrayList<>();
		
		// 같이 돌아가는 톱니바퀴 
		if(num==1) {
			if(!ischanged[2] && tlist[2][6] != tlist[num][2]) {
				together.add(2);
			}
		}else if(num==4) {
			if(!ischanged[3] && tlist[3][2] != tlist[num][6]) {
				together.add(3);
			}
		}else {
			if(!ischanged[num+1] && tlist[num+1][6] != tlist[num][2]) {
				together.add(num+1);
			}
			if(!ischanged[num-1] && tlist[num-1][2] != tlist[num][6]) {
				together.add(num-1);
			}
		}
		
		// num에 해당하는 톱니바퀴 회전
		if(dir == -1) {	// 반시계 방향 회전
			int temp = tlist[num][0];
			for(int i=1; i<8; i++) {
				tlist[num][i-1] = tlist[num][i];
			}
			tlist[num][7] = temp;
		}else {
			int temp = tlist[num][7];
			for(int i=7; i>0; i--) {
				tlist[num][i] = tlist[num][i-1];
			}
			tlist[num][0] = temp;
		}
		
		ischanged[num] = true;
		
		int ndir = dir * -1;
		
		for(int next : together) {
			rotate(tlist, next, ndir, ischanged);
		}
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		
		int[][] tlist = new int[5][8];				// tlist[i] : i번 톱니바퀴 상태 
		
		for(int i=1; i<=4; i++) {
			String s = br.readLine();
			String[] states = s.split("");
			
			for(int j=0; j<8; j++) {
				tlist[i][j] = Integer.parseInt(states[j]);
			}
		}
		
		int k = Integer.parseInt(br.readLine());		// 회전 횟수
		
		int[][] order = new int[k][2];		// order[i] : i번째 턴에서 회전할 톱니바퀴 번호와, 회전 방향
		StringTokenizer st;
		for(int i=0; i<k; i++) {
			st = new StringTokenizer(br.readLine());
			int num = Integer.parseInt(st.nextToken());
			int dir = Integer.parseInt(st.nextToken());
			order[i][0] = num;
			order[i][1] = dir;
		}
		
		boolean[] ischanged;
		for(int i=0; i<k; i++) {
			int num = order[i][0];
			int dir = order[i][1];
			ischanged = new boolean[5];	// 각 톱니바퀴들의 회전 여부. 매 턴마다 초기화
			
			rotate(tlist, num, dir, ischanged);
		}
		
		int score = 0;
		for(int i=1; i<=4; i++) {
			if(tlist[i][0]==1) {
				score += Math.pow(2, (i-1));
			}
		}
		System.out.println(score);
		
	}
}
