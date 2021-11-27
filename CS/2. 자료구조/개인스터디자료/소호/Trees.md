# Trees

### ○ Tree

- 계층적, 비선형 구조
- Linked List 처럼 노드를 가지게 된다.
- 하나의 Root노드로부터 Parent-Child 관계를 이루는 가지 노드들이 뻗어나가는 양상 
- Child 노드는 하나의 Parent 노드만 가진다.
- Parent → Child의 방향을 가진다. (Child가 Parent를 참조하지는 않음)
- 예시) DOM Object, 댓글/대댓글, Abstract Syntax Tree (추상구문트리-컴파일러에 사용되는 자료구조. syntax 분석 결과물)



![자료구조 - Tree](https://media.vlpt.us/images/qksud14/post/3d20cd05-5909-4370-91f3-b48e85c4786f/tree-data-struct.png)





### ○ Binary Trees

- 각 노드가 0~2개의 Child 노드만 가지는 트리
- Full Binary Tree (정이진 트리) :
  - 모든 노드가 0개 혹은 2개의 자식 노드를 가짐
- Complete Binary Tree (완전 이진 트리) :
  - 가장 말단 레벨을 제외한 모든 레벨이 포화 상태
  - 데이터가 왼쪽부터 채워져야 함
- Perfect Binary Tree (포화 이진 트리) : 
  - 모든 노드들이 포화 상태 (가장 말단 레벨의 노드 개수가 최대치)
  - 특징1 : 1레벨씩 내려갈 수록 노드의 개수가 2배됨
  - 특징2 : n레벨의 노드의 개수 =  0~n-1레벨의 노드 개수 총합 + 1



![Binary Tree](https://t1.daumcdn.net/cfile/tistory/216DF84957B87E6125)



### ○ Binary Tree 시간복잡도

- lookup - O(log N)
- insert - O(log N)
- delete - O(log N)

![Perfect Binary Tree](https://cdn.programiz.com/sites/tutorial2program/files/perfect-binary-tree_0.png)

N (h높이 트리의 노드 수) = 2^h -1

log N = h



## 1. Binary Search Tree

https://visualgo.net/en/bst

- 왼쪽 Child 노드는 Parent보다 작으며, 오른쪽 Child 노드는 Parent보다 크다.
- 각 노드는 최대 2개의 Child 노드를 가질 수 있다.

→ Linear 자료구조에 비하면 검색이 빠르다. (모든 노드를 방문할 필요 없음)

→ Linear 자료구조에 비하면 비교적 Insert, Delete가 느리다. (삽입할 자리/삭제할 데이터를 찾아야 함)



![Binary Search Tree | Example | Construction | Gate Vidyalay](https://www.gatevidyalay.com/wp-content/uploads/2018/07/Binary-Search-Tree-Example.png)



### ○ Balanced VS Unbalanced

불균형 이진 탐색 트리 - 최악의 경우 Linked List와 같은 모양과 성능을 가지게 된다.

- lookup - O(N)
- insert - O(N)
- delete - O(N)



→ Question) 불균형 이진탐색트리는 왜 안 좋은 걸까요?



![Balancing a binary search tree · Applied Go](https://appliedgo.net/media/balancedtree/BinTreeShapes.png)

→ Question) 불균형 BST를 어떻게 밸런싱 할 것인가?



### ○ 장점 / 단점

- 장점
  - O(N) 보다 좋은 성능을 가진다 (균형 BST인 경우)
  - 데이터가 정렬됨.
  - 데이터간에 관계가 형성됨. (Parent - Child)
  - 메모리 위치의 제약이 없기 때문에 flexible 하다.
- 단점
  - O(1) 연산이 없다. 모든 경우에 항상 traverse 해야 함.



### ○ 구현

 ```javascript
 class Node {
   constructor(value) {
     this.left = null;
     this.right = null;
     this.value = value;
   }
 }
 
 class BinarySearchTree {
   constructor() {
     this.root = null;
   }
   insert(value) {
     const newNode = new Node(value);
     if (this.root === null) {
       this.root = newNode;
     } else {
       let currentNode = this.root;
       while (true) {
         if (value < currentNode.value) {
           // left
           if (!currentNode.left) { // in the case of empty
             currentNode.left = newNode;
             return this;
           }
           currentNode = currentNode.left;
         } else if (value > currentNode.value) {
           // right
           if (!currentNode.right) { // in the case of empty
             currentNode.right = newNode;
             return this;
           }
           currentNode = currentNode.right;
         } else { 
           // equal
           return this;
         }
       }
       
     }
   }
   lookup(value) {
     if (!this.root) {
       return false;
     }
     let currentNode = this.root;
     while(currentNode){
       if(value < currentNode.value){
         currentNode = currentNode.left;
       } else if(value > currentNode.value){
         currentNode = currentNode.right;
       } else if (currentNode.value === value) {
         return currentNode;
       }
     }
     return null;
   }
 }
 
 ```



## 2. AVL Tree & Red Black Tree

''불균형 BST를 어떻게 밸런싱 할 것인가?'에 대한 해답 → AVL Tree / Red Black Tree



### ○ AVL Tree

- BST의 속성을 가진다. (이진탐색트리라는 뜻)
- 왼쪽, 오른쪽 서브 트리의 높이 차이가 최대 1이다.
- 이 높이 차이(|BF|)가 1보다 커지면 회전(rotation)을 통해 Balancing하여 높이 차이를 줄인다.
- Balanced 상태를 유지하기 때문에 (높이를 log N으로 유지), 삽입, 검색,삭제의 시간복잡도가 O(log N)

#### Balance Factor (BF)

- BF = 왼쪽 서브트리의 높이 - 오른쪽 서브트리의 높이
- 각 노드가 BF 값을 가짐. AVL Tree의 모든 BF는 -1~+1의 값을 유지

![img](https://blog.kakaocdn.net/dn/blxsRD/btq21CW9Fw3/WOk8F74J254K1pczckskEK/img.png)

#### Rotation

- |BF|가 1보다 커지면 Rotation을 통해 Balancing을 진행
- 4가지 불균형이 발생할 수 있으며, 각 상황마다 Rotation의 방향을 달리 한다.



##### LL(Left Left) case

Right rotation을 수행햐여 밸런싱한다.

- y노드의 오른쪽 자식 노드를 z노드로 변경
- z노드 왼쪽 자식 노드를 y노드 오른쪽 서브트리(T2)로 변경
- y는 새로운 루트 노드가 된다.

![img](https://blog.kakaocdn.net/dn/xLIeV/btq2Xb7eZdF/0tfPz6aL4PEFaIJC6CvTs1/img.png)

##### RR(Right Right) case

Left rotation을 수행햐여 밸런싱한다.

- y노드의 왼쪽 자식 노드를 z노드로 변경
- z노드 오른쪽 자식 노드를 y노드 왼쪽 서브트리(T2)로 변경
- y는 새로운 루트 노드가 된다.

![img](https://blog.kakaocdn.net/dn/MgydF/btq2ZpcT9dF/WNzhK8Ka9KmiuX6iqj5Ws0/img.png)



##### LR(Left Right) case

Left, Right 순으로 두 번의 Rotation을 수행하여 밸런싱한다.

![img](https://blog.kakaocdn.net/dn/tMu3I/btq21Mk69Ei/TToajHJiFvy3FmNYlbagj0/img.png)



##### RL(Right Left) case

Right, Left 순으로 두 번의 Rotation을 수행하여 밸런싱한다.

![img](https://blog.kakaocdn.net/dn/brTQV1/btq2TcMbXA3/mhrY8bPspDrRT90kkGDIR1/img.png)



### ○ Red Black Tree

![No Image](https://nesoy.github.io/assets/posts/20180831/2.png)



- BST의 속성을 가진다.
- 모든 노드는 Red/Black의 색깔을 가짐
- Root 노드는 항상 Black
- 모든 Leaf 노드(NIL)는 Black
  - RBT에서는 자식이 없는 쪽의 노드에 NIL이라는 Black 리프노드를 붙인다.
  - 실제 구현 시에 모든 리프에 NIL을 할당하기보다, 아래 그림과 같이 노드 하나를 할당하여 모든 NIL 리프에 대한 포인터가 이 노드를 가리키도록 하면, 메모리를 절약할 수 있다.
    ![img](https://t1.daumcdn.net/cfile/tistory/273C113D534D2FC70C)
- 노드가 Red이면 그의 Child는 모두 Black. (Red가 연속적으로 나올 수 없다)
- 각 노드로부터 그 노드의 Leaf로 가는 경로들은 모두 같은 수의 Black 노드를 포함한다. (Black Height)
- Balanced 상태를 유지하기 때문에 (높이를 log N으로 유지), 삽입, 검색,삭제의 시간복잡도가 O(log N)



#### Insert 예시

- 삽입되는 모든 노드의 색깔은 Red로 시작
- Insert 8 : 루트는 Black 규칙에 따라 Black으로 변경
  ![No Image](https://nesoy.github.io/assets/posts/20180831/4.png)
- Insert 18 :
  ![No Image](https://nesoy.github.io/assets/posts/20180831/5.png)

- Insert 5 : 
  ![No Image](https://nesoy.github.io/assets/posts/20180831/6.png)

-  Insert 15 : Red 노드가 연속으로 나올 수 없는 규칙 위반

  → 연속된 Red가 나오게 되면 아래 두 가지 방법을 사용한다

  - 삽입된 노드의 부모의 형제의 색깔이 Red인 경우 : Recoloring
  - 삽입된 노드의 부모의 형제의 색깔이 Black인 경우, NULL인 경우 : Restructuring

  → 현재의 경우 Recoloring으로 진행

  - 삽입된 노드의 부모와 부모의 형제노드를 Black으로, 부모의 부모노드를 Red로 Coloring
    (이 때 부모의 부모노드가 Root인 경우 변경하지 않음.)

  ![No Image](https://nesoy.github.io/assets/posts/20180831/7.png)

- Insert 17 : 삽입된 노드의 부모의 형제가 Black (NULL)이기 때문에 Restructuring 수행
  → 삽입된 노드, 부모, 부모의 부모를 오름차순으로 정렬
  → 중앙 값을 부모 노드로, 나머지 노드를 자식으로 변환
  → 부모가 된 노드를 Black, 나머지 노드를 Red로 Coloring

  ![No Image](https://nesoy.github.io/assets/posts/20180831/8.png)

- 이후 생략



위와 같은 규칙으로 트리를 유지할 경우, 가장 최대의 Depth와 최소의 Depth 차이는 최대 2배까지만 차이가 난다.



### ○ AVL Tree와 Red Black Tree의 사용

- AVL Tree가 Red Black Tree보다 빠른 Search를 제공한다.
  - AVL Tree가 더 엄격한 Balanced를 유지하고 있기 때문
- Red Black Tree은 AVL Tree보다 빠른 삽입과 제거를 제공한다.
  - AVL Tree보다 Balanced를 느슨하게 유지하고 있기 때문
- AVL Tree는 각 노드에 BF와 heights 저장하기 떄문에 integer 자료형 만큼의 메모리를 필요로 하는 반면, RBT는 색깔 표현을 위해 각 노드 당 1비트만 필요하다.
- Red Black Trees는 대부분의 언어의 map, multimap, multiset에서 사용한다. (insert, delete가 많이 쓰이므로)
- AVL tree는 조회의 속도가 중요한 Database에서 사용한다.



## 3. Binary Heap

- 완전 이진 트리 충족 (가장 말단 레벨을 제외한 모든 레벨이 포화 상태, 가장 말단은 왼쪽부터 채워짐)
- (최대 힙의 경우) 부모 노드는 반드시 Child 노드보다 크다. 
- 최상위 노드는 최대값을 가지고, 레벨의 순서대로 큰 값을 가지게 됨 → 우선순위 큐 등 순서가 중요한 알고리즘을 구현하는 데 적합한 자료구조

![최대 힙 최소 힙](https://t1.daumcdn.net/cfile/tistory/17084F504DA9895214)



### ○ 시간 복잡도

- lookup - O(N)
- insert - O(log N)
- delete - O(log N)

Lookup - BST처럼 Ordered 되어있지 않으므로(left와 right에 관계가 없음) 모든 노드를 탐색해야 한다.
값을 비교할 때는 효율적이다. (ex. 33보다 큰 값 찾기)

Insert - Best Case: O(1) / Worst Case: O(log N)

https://visualgo.net/en/heap



### ○ Binary Heap 특징

- 왼쪽부터 오른쪽의 방향으로 삽입하므로 공간이 절약되며, Unbalanced tree가 될 일이 없다.
- 왼쪽부터 차례대로 삽입하므로 배열로 구현할 수 있다.
- 우선순위 큐에 매우 효율적이다.
- Priority Queue : 우선순위가 높은 순서대로 처리되는 큐



### ○ 장점 / 단점

- 장점
  - O(N) 보다 좋은 성능을 가진다
  - 우선순위가 정해진다.
  - 메모리 위치의 제약이 없기 때문에 flexible 하다.
  - Insert가 빠르다. (O (log N))
- 단점
  - 검색이 느리다.



## 4. Trie

- 문자열을 저장하고 효율적으로 탐색하기 위한 트리 형태의 자료구조 (문자열 검색에 강력한 자료구조)
- Empty root node를 가진다.
- Binary Tree가 아니다. (multiple children을 가짐)
- alphabet을 생각하면 한 노드당 최대 26개의 Children 노드를 가질 것임을 알 수 있다.
- 사용 예시) 자동 완성 기능(검색 엔진), IP routing
- 시간복잡도(검색) - O(length of the word)
- 공간복잡도의 면에서도 이점을 가진다. - 같은 Prefix에 대하여 여러 번 저장하지 않기 떄문.



![Trie](https://media.vlpt.us/images/roo333/post/d29f3566-596d-40ae-868e-9322bba44ed6/Trie.png)





---

[참고]

전반적인 내용 : Udemy https://www.udemy.com/course/master-the-coding-interview-data-structures-algorithms/

AVL Tree : https://yoongrammer.tistory.com/72

Red Black Tree : https://nesoy.github.io/articles/2018-08/Algorithm-RedblackTree

https://itstory.tk/entry/%EB%A0%88%EB%93%9C%EB%B8%94%EB%9E%99-%ED%8A%B8%EB%A6%ACRed-black-tree

https://www.geeksforgeeks.org/red-black-tree-vs-avl-tree/amp/