import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;
/**
 * 플로이드 와샬
 * 
 * shortestPath(i, j, k) = i에서 j로 집합 {1,2,...,k}의 꼭지점들 만을 경유지로 거쳐 가는 최단 경로를 반환
 * 목표 : {1,2,...,N}에 있는 꼭지점만을 이용해서 모든 꼭지점 i에서 모든 꼭지점 j로 가는 최단 경로를 찾는 것
 * 
 * shortestPath(i, j, k)
 * 	- k를 통과하지 않는 경로 : 집합 {1,..., k-1}에 있는 꼭지점만 거쳐간다
 * 	- k를 통과하는 경로 : i->k, k->j 경로 모두 {1,..., k-1}에 있는 꼭지점만 거쳐간다
 * 
 * 즉, shortestPath(i, j, k) = min(shortestPath(i, j, k-1), shortestPath(i, k, k-1)+shortestPath(k, j, k-1))
 * 위 식을 k=1 ~ N에 대해서 반복적으로 수행하면, 모든 (i, j) 쌍에 대해서 최단 경로를 찾게 된다 .
 *
 */
public class Main {
	static int N;
	static int infinite = Integer.MAX_VALUE;
	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st;
		
		N = Integer.parseInt(br.readLine());
		int[][] dist = new int[N][N];
		
		for(int i=0; i<N; i++) {
			st = new StringTokenizer(br.readLine());
			for(int j=0; j<N; j++) {
				int num = Integer.parseInt(st.nextToken());
				dist[i][j] = num;
			}
		}
		
		for(int k=0; k<N; k++) {
			for(int i=0; i<N; i++) {
				for(int j=0; j<N; j++) {
					// 갈 수 있는 경로가 있는지만 체크함.
					if(dist[i][k]==1 && dist[k][j]==1) {
						dist[i][j] = 1;
					}
				}
			}
		}
		
//		printMap(dist);
		StringBuilder sb = new StringBuilder();
		for(int i=0; i<N; i++) {
			for(int j=0; j<N; j++) {
				sb.append(dist[i][j]+" ");
			}
			sb.append("\n");
		}
		
		BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
		bw.write(sb.toString());
		bw.flush();
		bw.close();
		br.close();
	}
	
//	public static void printMap(int[][] map) {
//		for(int i=0; i<N; i++) {
//			for(int j=0; j<N; j++) {
//				if(map[i][j]>0) {
//					System.out.print(1);
//				}else {
//					System.out.print(0);
//				}
//			}
//			System.out.println();
//		}
//	}
	
}

