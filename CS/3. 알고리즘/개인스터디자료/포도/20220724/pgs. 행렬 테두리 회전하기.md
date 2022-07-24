# 행렬 테두리 회전하기

[:link: 행렬 테두리 회전하기](https://school.programmers.co.kr/learn/courses/30/lessons/77485)  
<br>

### 풀이 방식
1. rows * columns 행렬 생성 및 초기화 (1부터 채움).
2. query값에 따른 회전 함수 구현: 이때 최소값을 찾아 answer에 삽입

```java
import java.util.*;

class Solution {
    int[][] matrix;
    public int[] solution(int rows, int columns, int[][] queries) {
        this.matrix = new int[rows][columns];  // 행렬 생성
        int[] answer = new int[queries.length]; // 정답 배열
        
        for(int i = 0; i < rows; i++){  // 행렬 초기화
            for(int j = 0; j < columns; j++){
                matrix[i][j] = i*columns + j + 1;
            }
        }
        
        for(int i = 0; i < queries.length; i++){ // 회전하고 최솟값 answer에 저장
            answer[i] = rotate(queries[i]);
        }
        
        return answer;
    }
    
    public int rotate(int[] query){
        int r1 = query[0]-1; 
        int c1 = query[1]-1;
        int r2 = query[2]-1;
        int c2 = query[3]-1;
        
        int temp = this.matrix[r1][c1]; // 시작위치 값 임시저장
        int min = temp;                 // min값 초기화
        for(int i = r1; i < r2; i++){ // 회전의 1번
            this.matrix[i][c1] = this.matrix[i+1][c1];
            if(min > this.matrix[i][c1]) min = this.matrix[i][c1];
        }
        for(int i = c1; i < c2; i++){ // 회전의 2번
            this.matrix[r2][i] = this.matrix[r2][i+1];
            if(min > this.matrix[r2][i]) min = this.matrix[r2][i];
        }
        for(int i = r2; i > r1; i--){ // 회전의 3번
            this.matrix[i][c2] = this.matrix[i-1][c2];
            if(min > this.matrix[i][c2]) min = this.matrix[i][c2];
        }
        for(int i = c2; i > c1; i--){ // 회전의 4번
            this.matrix[r1][i] = this.matrix[r1][i-1];
            if(min > this.matrix[r1][i]) min = this.matrix[r1][i];
        }
        this.matrix[r1][c1+1] = temp; // 임시저장한 값 저장
        
        return min; 최솟값 반환
    }
    
}
```
