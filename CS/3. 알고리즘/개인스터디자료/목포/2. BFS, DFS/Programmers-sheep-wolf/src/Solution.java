import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

class Solution {

    Map<Integer, List<Integer>> graph;
    int MaxCnt = 0;

    public int solution(int[] info, int[][] edges) {
        int answer = 0;
        graph = new HashMap<>();

        for (int[] e : edges) {
            if (!graph.containsKey(e[0])) graph.put(e[0], new ArrayList<>());
            graph.get(e[0]).add(e[1]);
        }

        List<Integer> list = new ArrayList<>();
        list.add(0);
        dfs(0, 0, 0, list, info);

        return MaxCnt;
    }

    public void dfs(int idx, int s, int w, List<Integer> list, int[] info) {
        if (info[idx] == 0) s+=1;
        else w+=1;
        if (s<=w) return;

        MaxCnt = Math.max(MaxCnt, s);

        List<Integer> next = new ArrayList<>();
        next.addAll(list);

        if (graph.containsKey(idx)) next.addAll(graph.get(idx));
        next.remove(Integer.valueOf(idx));

        for (int n : next) {
            dfs(n, s, w, next, info);
        }

        return;
    }
}
