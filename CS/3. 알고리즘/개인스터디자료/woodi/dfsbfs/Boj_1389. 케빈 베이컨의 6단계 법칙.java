import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
	static int n;
	static List<Integer>[] graph;
	static class Node implements Comparable<Node>{
		int num;
		int dist;
		public Node(int num, int dist) {
			this.num = num;
			this.dist = dist;
		}
		@Override
		public int compareTo(Node o) {
			if(this.dist == o.dist) {
				return this.num - o.num;
			}
			return this.dist - o.dist;
		}
	}
	
	public static int bfs(int s) {
		// 출발지부터 다른 모든 노드까지의 거리의 합 구하기 
		boolean[] visited = new boolean[n+1];
		visited[s] = true;
		int totalDist = 0;
		
		Queue<Node> q = new LinkedList<>();
		q.add(new Node(s,0));
		
		while(!q.isEmpty()) {
			Node now = q.remove();
			totalDist += now.dist;
			for(int next : graph[now.num]) {
				if(!visited[next]) {
					q.add(new Node(next, now.dist+1));
					visited[next] = true;
				}
			}
		}
		
		return totalDist;
	}
	
	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		n = Integer.parseInt(st.nextToken());
		int m = Integer.parseInt(st.nextToken());
		
		graph = new ArrayList[n+1];
		
		for(int i=1; i<=n; i++) {
			graph[i] = new ArrayList<>();
		}
		
		for(int i=0; i<m; i++) {
			st = new StringTokenizer(br.readLine());
			int u = Integer.parseInt(st.nextToken());
			int v = Integer.parseInt(st.nextToken());
			
			graph[v].add(u);
			graph[u].add(v);
		}
		
		List<Node> result = new ArrayList<>();
		
		// 출발지 : 1~n -> 도착지 : 1~n  
		// 각 출발지에 대해서 도착지까지의 거리의 합이 가장 작은 얘 구하기
		for(int s=1; s<=n; s++) {
			int totalDist = bfs(s);
			result.add(new Node(s, totalDist));
		}
		
		Collections.sort(result);
		
		int answer = result.get(0).num;
		System.out.println(answer);
		
	}
}
