# 크레인 인형뽑기 게임

[:link: 크레인 인형뽑기 게임](https://programmers.co.kr/learn/courses/30/lessons/64061)  
<br>

### 풀이 방식

```java
import java.util.ArrayList;
import java.util.List;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;

        List<Integer> basket = new ArrayList<>();

        for(int i = 0; i < moves.length; i++){
            int point = moves[i] - 1;

            for(int j = 0; j < board.length; j++){
                int doll = board[j][point]; // 인형 탐색
                if(doll != 0){ // 인형이 있는 경우
                    basket.add(doll); // 리스트에 인형을 담고
                    board[j][point] = 0; // 기계에 있는 인형은 제거

                    // 범위를 벗어나지 않도록 basket에 인형이 2개 이상일 때부터 확인
                    if(basket.size() >= 2){
                        int size = basket.size() - 1;

                        if(basket.get(size) == basket.get(size - 1)){
                            basket.remove(size);
                            basket.remove(size - 1);
                            answer += 2;
                        }
                    }
                    break;
                }
            }

        }

        return answer;
    }
}
```
