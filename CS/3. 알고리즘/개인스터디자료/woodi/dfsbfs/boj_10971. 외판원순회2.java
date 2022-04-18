// https://www.acmicpc.net/problem/10971
/* 외판원 순회 문제는 영어로 Traveling Salesman problem (TSP) 라고 불리는 문제로 
 * computer science 분야에서 가장 중요하게 취급되는 문제 중 하나이다.
	여러 가지 변종 문제가 있으나, 여기서는 가장 일반적인 형태의 문제를 살펴보자.
 * */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

/* 순열 : 순서가 중요함 
 * 어떤 순서로 해야 비용이 최소로 들까 
 * 첫째 줄에 도시의 수 N이 주어진다. (2 ≤ N ≤ 10) 다음 N개의 줄에는 비용 행렬이 주어진다. 
 * W[i][j]는 도시 i에서 j로 가기 위한 비용을 나타낸다.
 * 경우에 따라서 도시 i에서 도시 j로 갈 수 없는 경우도 있으며 이럴 경우 W[i][j]=0이라고 하자.
 * */

public class Main {
	static int N;
	static int[][] W;
	static int MIN = Integer.MAX_VALUE;
	static int[] order;
	
	public static void main(String[] args) throws IOException {
		BufferedReader br =  new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		N = Integer.parseInt(st.nextToken());
		W = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				W[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		// 방문 순서 정렬 : 일단 0, 1, 2, 3, 4, ..., N 순으로 방문한다고 가정
		order = new int[N];
		for(int i=0; i<N; i++) {
			order[i] = i;
		}
		
		// 순열 - 비용 계산 
		// order[0] 여기에 뭐가 들어가는지가 관건 (이미 정해진 거로 치기 때문)
		for(int i=0; i<N; i++) {
			swap(0,i);
			perm(1, 0);
			swap(0,i);
		}
		
		System.out.println(MIN);
		
	}
	
	// k-1 번째까지 순서 정해진 것 
	static void perm (int k, int result) {
		if(k==N && W[order[N-1]][order[0]]!=0) {
			result += W[order[N-1]][order[0]];
			if(MIN > result) {
				MIN = result;
			}
			return;
		}
		
		for(int i=k; i<N; i++) {
			swap(i, k);
			if(W[order[k-1]][order[k]]!=0) {
				perm(k+1, result+ W[order[k-1]][order[k]]);
			}
			swap(i, k);
		}
	}
	
	
	static void swap(int i, int j) {
		int temp = order[i];
		order[i] = order[j];
		order[j] = temp;
	}

	
}
