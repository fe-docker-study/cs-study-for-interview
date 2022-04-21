# 양과 늑대
[:link: 양과 늑대](https://programmers.co.kr/learn/courses/30/lessons/92343)  
<br>

```java
import java.util.*;
 
class Solution {
	// 해당 IDX의 자식은 누가 있는지
	static ArrayList<Integer>[] childs;
	static int[] Info;
	static int maxSheepCnt = 0;
 
	public static int solution(int[] info, int[][] edges) {
		Info = info;
		childs = new ArrayList[info.length];
		for (int[] l : edges) {
			int parent = l[0];
			int child = l[1];
			if (childs[parent] == null) {
				childs[parent] = new ArrayList<>();
			}
			childs[parent].add(child);
		}
 
		List<Integer> list = new ArrayList<>();
		list.add(0);

        // 0부터 dfs 탐색 시작
		dfs(0, 0, 0, list);
		return maxSheepCnt;
	}
 
	private static void dfs(int idx, int sheepCnt, int wolfCnt, List<Integer> nextPos) {
		// 현재 위치 Node가 양인지 늑대인지에 따라 대수 갱신
		if (Info[idx] == 0) sheepCnt++; // 양이 있으면 양의 수 +1
		else wolfCnt++; // 늑대면 늑대 수 +1
 
		if (wolfCnt >= sheepCnt) return; // 늑대가 양보다 많으면 양이 먹히기 때문에 return
		maxSheepCnt = Math.max(sheepCnt, maxSheepCnt); // 양의 수 update
   
		// 다음 탐색 위치 갱신
		List<Integer> list = new ArrayList<>();
		list.addAll(nextPos);
		// 다음 탐색 목록중 현재 위치제외
		list.remove(Integer.valueOf(idx));
		if (childs[idx] != null) {
            // child 노드 list에 넣기
			for (int child : childs[idx]) {
				list.add(child);
			}
		}
        
		// 갈수 있는 모든 Node Dfs
		for (int next : list) {
			dfs(next, sheepCnt, wolfCnt, list);
		}
	}
}
```

다시 풀어보기


