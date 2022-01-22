import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Main {

    static int N, M, X;
    static int[] dist;
    static boolean[] visited;
    static ArrayList<ArrayList<Node>> graph;
    public static void main(String[] args) throws IOException {

        int result = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        X = Integer.parseInt(st.nextToken());

        graph = new ArrayList<>();

        visited = new boolean[N + 1];
        dist = new int[N + 1];
        int[] distXtoHome;

        for (int i = 0; i <= N; i++) {
            graph.add(new ArrayList<>());
        }



        for (int i = 1; i <= M; i++) {
            st = new StringTokenizer(br.readLine());

            int sv = Integer.parseInt(st.nextToken());
            int ev = Integer.parseInt(st.nextToken());
            int t = Integer.parseInt(st.nextToken());

            graph.get(sv).add(new Node(ev, t));
        }

        dijsktra(X);

        distXtoHome = Arrays.copyOf(dist, N + 1);


        for (int i = 1; i <= N; i++) {
            result = Math.max(result, distXtoHome[i] + dijsktra(i));
        }


        System.out.println(result);
    }

    public static int dijsktra(int start) {
        for (int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
            visited[i] = false;
        }

        dist[start] = 0;

        for (int i = 1; i <= N; i++) {
            int nodeValue = Integer.MAX_VALUE;
            int nodeIndex = 0;

            for (int j = 1; j <= N; j++) {
                if(!visited[j] && dist[j] < nodeValue) {
                    nodeValue = dist[j];
                    nodeIndex = j;
                }
            }

            visited[nodeIndex] = true;

            for (int j = 0; j < graph.get(nodeIndex).size(); j++) {
                Node nextNode = graph.get(nodeIndex).get(j);

                if(dist[nextNode.vertex] > nextNode.weight + dist[nodeIndex]) {
                    dist[nextNode.vertex] = nextNode.weight + dist[nodeIndex];
                }
            }
        }

        return dist[X];
    }
}

class Node {
    int vertex, weight;

    public Node(int vertex, int weight) {
        this.vertex = vertex;
        this.weight = weight;
    }
}