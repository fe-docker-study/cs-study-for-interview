package backtracking;

import java.io.*;
import java.util.*;
// 외판원의 순회에 필요한 최소 비용을 출력
public class Boj_10971 {
	static int n;
	static int[][] W;		// W[i][j] : i->j로 가기 위한 비용
	static int minCost = Integer.MAX_VALUE;
	
	public static void goTo(int start, int now, int totalCost, boolean[] visited) {
//		System.out.println("start :"+start +", now : "+now +", totalCost : "+totalCost);
		if(start == now) {
			// n개의 도시를 모두 거쳤는지 확인
			for(int i=1; i<=n; i++) {
				if(!visited[i]) {
					return;
				}
			}
			
			minCost = Math.min(minCost, totalCost);
			return;
		}
		
		for(int i=1; i<=n; i++) {
			if(i == now || W[now][i] == 0) continue;
			if(visited[i]) {
//				System.out.println(i+"번째 노드는 이미 방문함");
				continue;
			} 
			
			totalCost += W[now][i];
			visited[i] = true;
			
			goTo(start, i, totalCost, visited);
			
			totalCost -= W[now][i];
			visited[i] = false;
		}
	}
	
	public static void start(int start, boolean[] visited) {
		int totalCost = 0;
		
		for(int i=1; i<=n; i++) {
			if(i == start || W[start][i]==0) continue;
			totalCost = W[start][i];
			visited[i] = true;
			
			goTo(start, i, totalCost, visited);
			
			visited[i] = false;
		}
	}
	
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		n = Integer.parseInt(br.readLine());
		W = new int[n+1][n+1];
		
		StringTokenizer st;
		for(int i=1; i<=n; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=1; j<=n; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		boolean[] visited = new boolean[n+1];
		for(int i=1; i<=n; i++) {
			// 출발지
			start(i, visited);
		}
		
		System.out.println(minCost);
	}
}
