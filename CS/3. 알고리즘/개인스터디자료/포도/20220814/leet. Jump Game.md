# Jump Game

[:link: Jump Game](https://leetcode.com/problems/jump-game/)  
<br>

### 풀이 방식
자연수 배열 nums는 각 인덱스에서 이동할 수 있는 최대 거리를 의미  
처음에는 배열의 첫 번째 인덱스에 위치한다고 할 때 인덱스에서 점프하면서 배열의 끝에 도달할 수 있는지 확인하여 결과를 리턴

```java
class Solution {
    public int N;
    
    public boolean canJump(int[] nums) {
        N = nums.length; // 배열의 길이
        
        boolean[] visited = new boolean[nums.length]; // 방문했는지 확인할 배열
        Queue<Integer> queue = new LinkedList<Integer>();
        
        visited[0] = true; // 처음에는 배열의 첫 번째 인덱스에 있음
        queue.add(0);
        
        while(!queue.isEmpty()) {
            int idx = queue.poll();
            
            for(int i = 1; i <= nums[idx]; i++){ // 현재 있는 위치에서 점프할 수 있는 길이에 있는 인덱스 다 방문하기
                int newIdx = idx + i;
                
                if(!isIn(newIdx) || visited[newIdx]) {
                    continue; // 범위 밖이거나 이미 방문한 경우에는 탐색하지 않음
                }
                
                if (newIdx == N - 1) {
                    return true; // 배열의 끝에 도달한 경우 true를 리턴하고 종료
                }
                
                visited[newIdx] = true;
                queue.add(newIdx);
            }
        }
        
        return visited[N-1]; // 배열의 마지막 인덱스의 방문여부를 리턴
        
    }
    
    public boolean isIn(int newIdx) {
        if(newIdx >= 0 && newIdx < N) {
            return true;
        }
        
        return false;
    }
}
```
