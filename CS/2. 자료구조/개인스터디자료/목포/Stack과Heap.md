# Stack과 Heap

## Stack

### 개념

한 쪽 끝에서만 자료를 넣고 뺄 수 있는 LIFO(Last In First Out)형식의 자료구조  
![Image]("https://cheonmro.github.io/images/stack.jpg")

### Stack의 연산

스택은 가장 최근에 스택에 추가한 항목을 먼저 제거한다.

- pop() : 스택에서 가장 위에 있는 항목을 제거한다.
- push(item) : item 하나를 스택의 가장 윗 부분에 추가한다.
- peek() : 스택의 가장 위에 있는 항목을 반환한다.
- isEmpty() : 스택이 비어있을 때 true를 반환한다.

### Stack의 사용사례

- **웹 브라우저 방문기록** : 후입선출(LIFO)를 활용하여 가장 나중에 열린 페이부터 다시 보여준다.
- **역순 문자열 만들기** : 가장 나중에 입력된 문자부터 출력한다.
- **실행취소(undo)** : 가장 나중에 실행된 것부터 실행을 취소한다.
- **후위 표기법 계산**
- **수식의 괄호 검사** (연산자 우선순위 표현을 위한 괄호 검사)

#### Stack을 이용한 괄호검사

> #### condition
>
> - 문자열에 있는 괄호를 차례대로 조사하면서 왼쪽 괄호를 만나면 Stack에 push, 오른쪽 괄호를 만나면 Stack의 top과 괄호의 짝이 맞는지 비교한다.
> - 짝이 맞는 경우 Stack에서 pop을 수행하고 1번 반복, 짝이 맞지 않거나 Stack이 비어있는 경우는 false 반환.
> - 문자열을 전부 검사한 후 Stack이 비어있으면 true. 만약 Stack에 괄호가 남아있다면 false를 반환.

**Bracket.java**

```Java
public class Main {
    static Stack<Character> stack = new Stack<>();
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String statement = br.readLine();
        System.out.println(checkBracket(statement));
    }

    public static boolean checkBracket(String statement) {
        for (int i = 0; i < statement.length(); i++) {
            if(statement.charAt(i) == '{' || statement.charAt(i) == '[' || statement.charAt(i) == '(') {
                stack.push(statement.charAt(i));
            }else{
                switch (statement.charAt(i)) {
                    case '}' :
                        if(stack.peek() =='{') stack.pop();
                        break;
                    case ']' :
                        if(stack.peek() == '[') stack.pop();
                        break;
                    case ')' :
                        if(stack.peek() == '(') stack.pop();
                        break;
                    default :
                        break;
                }
            }
        }
        if(stack.isEmpty()) return true;
        else return false;
    }
}
```

```console
A*[(a+1)/b]
true

if((i==0)&&(j==0)
false
```

## Heap

### 개념

이진트리의 형태를 띤 자료구조. 여러 개의 값들 중 가장 작은 값이나 큰 값을 찾아내기에 용이하다.

### Heap의 종류

- 최대 힙(Max Heap)
  - 부모 노드의 키 값이 자식의 키 값보다 크거나 같은 완전 이진 트리
  - key(부모노드) >= key(자식노드)
- 최소 힙(Min Heap)
  - 부모 모드의 키 값이 자식 노드의 키 값보다 작거나 같은 완전 이진트리
  - key(자식노드)
    <= key(자식노드)

### Heap의 연산

- 삽입(최대 힙)

  1. 힙에 새로운 요소가 들어오면, 일단 새로운 노드를 힙의 마지막 노드에 삽입한다.
  2. 새로운 노드를 부모노드들과 교환하여 힙의 성질을 만족시킨다.  
     **예) 최대 힙에 새로운 요소 8 삽입**
     ![]("https://gmlwjd9405.github.io/images/data-structure-heap/maxheap-insertion.png")

- 삭제(최대 힙)
  1. Root 노드를 삭제한다.
  2. Root 노드의 자리에 맨 마지막 노드를 가져온다.
  3. heap을 재구성한다. (만약 자식노드보다 크면 그대로 두고, 작다면 자식노드와 값을 바꾼다.)
     ![]("https://media.vlpt.us/images/humblechoi/post/412494b4-1e52-42a1-b05c-bb1dcc399372/image.png")

### Priority Queue를 사용하여 Heap 구현하기

Java에서는 Priority Queue를 이용하여 Heap을 구현할 수 있도록 지원하고 있다.

#### Priority Queue

우선순위 큐는 우선순위가 높은 요소가 낮은 요소보다 먼저 제공되는 자료구조이다.

```Java
public class Main {
    public static void main(String[] args) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        System.out.println("최소 힙");

        queue.add(1);
        queue.add(8);
        queue.add(5);
        queue.add(2);
        queue.add(3);

        while(!queue.isEmpty())
            System.out.println(queue.poll());
    }
}
```

```console
최소 힙
1
2
3
5
8
```

```Java
private void siftUpComparable(int k, E x) {
        Comparable<? super E> key = (Comparable<? super E>) x;
        while (k > 0) {
            int parent = (k - 1) >>> 1;
            Object e = queue[parent];
            if (key.compareTo((E) e) >= 0)
                break;
            queue[k] = e;
            k = parent;
        }
        queue[k] = key;
    }
```

### Referenece

https://gmlwjd9405.github.io/2018/05/10/data-structure-heap.html
