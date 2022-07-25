# 구명보트

[:link: 구명보트](https://school.programmers.co.kr/learn/courses/30/lessons/42885)  
<br>

### 풀이 방식
최소한의 횟수로 옮기기 위해서는 가장 몸무게가 큰 사람 + 가장 몸무게가 작은 사람 조합이어야 함
1. 배열 정렬
2. 최소값 위치 역할을 하는 min을 0으로 초기화
3. 반복문을 돌며 최소값의 위치가 최대값의 위치보다 커지면 반복 종료
4. max--
5. 반복문에서 min 위치에 있는 배열의 값 + max 위치에 있는 배열의 값이 한계보다 작으면 min++

```java
public class Solution {

  public int solution(int[] people, int limit) {
    int answer = 0;

    Arrays.sort(people);

    int min = 0;

    for (int max = people.length - 1; min <= max; max--){
      if (people[min] + people[max] <= limit) min++; // 두 명이 타는 경우 최소값 조정
      answer++;
    }

    return answer;
}
```
