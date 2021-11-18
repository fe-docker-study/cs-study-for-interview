# Heap

### Heap은

- complete binary tree 이면서
- heap property를 만족해야한다.

### Full binary tree와 Complete binary tree의 비교

![image](https://user-images.githubusercontent.com/47748246/142373384-e17945f7-d073-40e6-9564-fcfdb4622fdf.png)


### heap property

- Max heap : 부모 노드는 항상 자식 노드보다 크다.
- Min heap : 부모 노드는 항상 자식 노드보다 작다.
- heap은 유일하지 않다. 즉, 동일한 데이터에 대해 여러 힙이 존재 할 수 있다.
    
![image](https://user-images.githubusercontent.com/47748246/142373423-a8a6611c-a72b-4094-b0e0-8b4ef97cd0c4.png)
    

### Heap의 표현

- 힙은 일차원 배열로 표현 가능하다.
    - 루트 노드 : A[1]
    - A[i]의 부모 = A[i/2]
    - A[i]의 왼쪽 자식 = A[2*i]
    - A[i]의 오른쪽 자식 = A[2*i +1]

![image](https://user-images.githubusercontent.com/47748246/142373451-2440399e-1b88-4cf5-a99f-e6be890c4b46.png)

### MAX-HEAPIFY 구현

![image](https://user-images.githubusercontent.com/47748246/142373480-5e3e3fc0-8f8c-4108-ba25-0d2c63517856.png)

```java
class MaxHeap{
  private ArrayList<Integer> heap;

  public MaxHeap() {
      heap = new ArrayList<Integer>();
      heap.add(0); //첫번째 인덱스는 사용하지 않음
  }

  //삽입
  public void insert(int val) {
      //맨 마지막 위치에 삽입
      heap.add(val);

      int  p = heap.size()-1; //새로 넣은 노드의 인덱스 위치 정보
      //루트까지 이동 자식이 더 크면 교환
      while(p>1 && heap.get(p)> heap.get(p/2)) {
          int tmp = heap.get(p/2);
          heap.set(p/2, heap.get(p));
          heap.set(p, tmp);

          p /= 2;
      }
  }
  //삭제
  public int delete() {
      //힙 이 비어있으면 0리턴
      if(heap.size()-1 < 1) {
          return 0;
      }

      //삭제할 루트 노드 값 저장
      int deleteitem = heap.get(1);

      //맨 마지막 자식 루트에 넣고 마지막 값 삭제
      heap.set(1,heap.get(heap.size()-1));
      heap.remove(heap.size()-1);

      //루트에 새로 넣은 노드의 인덱스 정보
      int pos = 1;
      while((pos*2)<heap.size()) {

          int max = heap.get(pos*2);
          int maxPos = pos*2;

          //오른쪽 자식이 존재하고 오른쪽 자식이 왼쪽 자식보다 클때 바꿀 자식 오른쪽으로 설정
          if((pos*2 +1)<heap.size() && max < heap.get(pos*2+1)) {
              max = heap.get(pos*2+1);
              maxPos = pos*2+1;
          }

          //부모가 더 크면 끝
          if(heap.get(pos) > max){
              break;
          }

          //자식이 더 크면 교환
          int tmp = heap.get(pos);
          heap.set(pos, max);
          heap.set(maxPos, tmp);
          pos = maxPos;
      }
      return deleteitem;
  }
}
```

# 이진 검색 트리

### Dynamic Set

- 여러 개의 key를 저장
- insert, search, delete와 같은 연산들을 지원한다.

### 검색 트리

- Dynamic Set을 트리의 형태로 구현
- 일반적으로 search, insert, delete 연산이 트리의 높이에 비례하는 시간복잡도를 가짐
- 이진검색트리, 레드블랙트리, B-트리 등

### 이진 검색 트리

- 이진 트리
- 각 노드에 하나의 키를 저장
- 각 노드 v에 대해서 그 노드의 왼쪽 subtree에 있는 키들은 모두 key[v]보다 작거나 같고, 오른쪽 subtree에 있는 값은 크거나 같다.

1) search

```java
// 이진 검색 트리에서 k를 값으로 갖는 노드의 인덱스를 구하고자 한다. 
// x는 현재 노드 , k는 찾으려는 값
Tree-Search(x, k){
	if x=null or k=key[x] 
		then return x

	if k < key[x]
		then return Tree-Search(left[x], k)
		else return Tree-Search(right[x], k)
}
```

- 시간 복잡도 = O(h) (h : 트리의 높이)

- 최소값
    - 최소 값은 항상 가장 왼쪽 노드에 존재
    - 시간 복잡도 = O(h)
    
    ```java
    Tree-minimum(x){
    	while(left[x] != null){
    		x = left[x]
    	}
    	return x
    }
    ```
    

- 최대값
    - 최대값은 항상 가장 오른쪽 노드에 존재
    - 시간 복잡도 = O(h)
    
    ```java
    Tree-maximum(x){
    	while(right[x] != null){
    		x = right[x]
    	}
    	return x
    }
    ```
    

2) insert  

- 검색할때와 같이 루트노드부터 차례로 비교하며, 트리의 가장 리프 노트에 새로운 노드를 추가

![image](https://user-images.githubusercontent.com/47748246/142373523-13736056-a4f0-44a0-8996-ae4a3da7721a.png)

3) delete

i) 자식 노드가 없는 경우 → 그냥 삭제
![image](https://user-images.githubusercontent.com/47748246/142373561-abcfc3a5-c594-44db-b024-c002aa84214e.png)

ii) 자식 노드가 1개인 경우
![image](https://user-images.githubusercontent.com/47748246/142373593-f7fd973e-3ecc-4dd0-b767-56df22487bf9.png)

iii) 자식 노드가 2개인 경우

![image](https://user-images.githubusercontent.com/47748246/142373636-8a88372b-114e-4482-ad86-8246af7ea1ab.png)

<aside>
📖 Successor
- 노드 x의 successor란 key[x]보다 크면서 가장 작은 키를 가진 노드를 말한다. 
예를들어 노드 x의 successor를 구할때
i) 노드 x의 오른쪽 subtree가 존재하는 경우, 오른쪽 subtree의 최소값이 successor가 된다.
ii) 오른쪽 subtree가 없는 경우, 부모를 따라 루트까지 올라가면서 처음으로 누군가의 왼쪽 자식이 되는 노드가 successor가 된다 .
iii) 그런 노드가 없을 경우, successor가 존재하지 않는다. 즉, x가 최대값이 된다.

</aside>

![image](https://user-images.githubusercontent.com/47748246/142373671-ccb47074-c647-471f-829b-c137137a54ea.png)

# 레드블랙 트리

- 이진 탐색 트리의 일종
- 균형 잡힌 트리 : O($log_2n)$
- 레드블랙트리는 자료의 삽입과 삭제, 검색에서 최악의 경우에도 일정한 실행 시간(O($log_2n$))을 보장한다.

### 특성

1. 노드는 레드 혹은 블랙 중의 하나이다.
2. 루트 노드는 블랙이다.
3. 모든 리프 노드들(NIL)은 블랙이다.
4. 레드 노드의 자식노드 양쪽은 언제나 모두 블랙이다. (즉, 레드 노드는 연달아 나타날 수 없으며, 블랙 노드만이 레드 노드의 부모 노드가 될 수 있다)
5. 어떤 노드로부터 시작되어 그에 속한 하위 리프 노드에 도달하는 모든 경로에는 리프 노드를 제외하면 모두 같은 개수의 블랙 노드가 있다.

위 조건들을 만족하게 되면, 레드-블랙 트리는 가장 중요한 특성을 나타내게 된다
