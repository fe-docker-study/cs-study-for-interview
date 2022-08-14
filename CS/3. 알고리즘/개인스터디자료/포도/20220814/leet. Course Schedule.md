# Course Schedule

[:link: Course Schedule](https://leetcode.com/problems/course-schedule/)  
<br>

### 풀이 방식
- 내가 들어야 하는 총 과목의 수 : numCourses
- prerequisites 배열 : 수강 순서
- 모든 과목을 들을 수 있는지 확인
<br>

수강을 처음으로 시작하는 과목은 선수 과목이 없어야 한다. 따라서 노드들 중 진입 간선이 없는 노드를 알아내기 위해 진입 차수를 각각의 노드마다 세어 주어야 한다.  
진입 차수가 0인 과목부터 BFS로 순회하여 모든 노드에 방문할 수 있는 경우 모든 과목을 수강할 수 있다는 의미가 된다.  
단, 방문한 노드가 선수 과목을 모두 수강하지 않았다면 수강할 수 없기 때문에 선수과목의 개수로 이를 판단해야 한다.  

```java
class Solution {
	public boolean canFinish(int numCourses, int[][] prerequisites) {
	    int[] indegree = new int[numCourses];
	    ArrayList<ArrayList<Integer>> adjList = new ArrayList<ArrayList<Integer>>();
	    
        for(int i=0; i<numCourses ; ++i)	//ArrayList초기화
	    	adjList.add(new ArrayList<Integer>());
	    
	    for(int i=0; i<prerequisites.length ; ++i) {
	    	int out = prerequisites[i][1], in = prerequisites[i][0];
	    	adjList.get(out).add(in);
	    	indegree[in]++;
	    }

	    boolean[] isVisited = new boolean[numCourses];
	    boolean[] isInQue = new boolean[numCourses];
	    
	    Queue<Integer> que = new LinkedList<>();
	    for(int i=0; i<numCourses ; ++i) {//진입차수가 0인 노드만 큐에 삽입
	    	if(indegree[i] == 0) {
	    		que.add(i);
	    		isInQue[i] = true;
	    	}
	    }
	    
	    while(!que.isEmpty()) { //BFS
	    	int idx = que.poll();
	    	isVisited[idx] = true;
	    	
	    	for(int adjNode: adjList.get(idx)) {
	    		if((--indegree[adjNode]) ==0 &&!isInQue[adjNode]) {
	    			que.add(adjNode);
	    			isInQue[adjNode] = true;
	    		}
	    	}
	    }
	    
	    for(int i=0; i<numCourses ;++i) 
	    	if(!isVisited[i])
	    		return false;
		
		return true;
	}
}
```
