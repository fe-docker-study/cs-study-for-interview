import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

    static int V, E;

    public static void main(String[] args) throws IOException {
        int start;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        V = Integer.parseInt(st.nextToken());
        E = Integer.parseInt(st.nextToken());
        start = Integer.parseInt(br.readLine());

        ArrayList<ArrayList<Node>> graph = new ArrayList<>();

        for (int i = 0; i <= V; i++) {
            graph.add(new ArrayList<>());
        }

        boolean[] visited = new boolean[V+1];
        int[] dist = new int[V+1];

        for (int i = 1; i <= V; i++) {
            dist[i] = Integer.MAX_VALUE;
        }

        for (int i = 1; i <= E; i++) {
            st = new StringTokenizer(br.readLine());

            int u = Integer.parseInt(st.nextToken());
            int v = Integer.parseInt(st.nextToken());
            int w = Integer.parseInt(st.nextToken());

            graph.get(u).add(new Node(v, w));
        }

        dist[start] = 0; // 출발 지점의 가중치는 0으로 시작

        for (int i = 1; i <= V; i++) {
            int nodeValue = Integer.MAX_VALUE;
            int nodeIndex = 0;

            for (int j = 1; j <= V; j++) {
                if(!visited[j] && dist[j] < nodeValue) {
                    nodeValue = dist[j];
                    nodeIndex = j;
                }
            }

            visited[nodeIndex] = true;

            for (int j = 0; j < graph.get(nodeIndex).size(); j++) {
                Node nextNode = graph.get(nodeIndex).get(j);

                if(dist[nextNode.vertex] > dist[nodeIndex] + nextNode.weight) {
                    dist[nextNode.vertex] = dist[nodeIndex] + nextNode.weight;
                }
            }
        }

        for (int i = 1; i <= V; i++) {
            if(dist[i] == Integer.MAX_VALUE) {
                System.out.println("INF");
            }else{
                System.out.println(dist[i]);
            }
        }

    }
}

class Node {
    int vertex, weight;

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }

    public int getVertex() {
        return vertex;
    }

    public int getWeight() {
        return weight;
    }
}