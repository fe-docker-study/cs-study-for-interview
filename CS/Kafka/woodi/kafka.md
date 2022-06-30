# 1. Kafka란?

- kafka는 pub-sub 모델의 메시지 큐이다.
- 분산환경에 특화되어있는 특징을 가지고 있다.

# 2. 구성 요소

### 2.1 Event

- `Event`는,  kafka에서 Producer와 Consumer가 데이터를 주고 받는 단위이다. `메시지`라고도 한다.

### 2.2 Producer

- kafka에 이벤트를 게시(post)하는 클라이언트 어플리케이션을 의미한다.

### 2.3 Consumer

- Topic을 구독하고 이로부터 얻어낸 이벤트를 처리하는 클라이언트 어플리케이션이다.

### 2.4 Topic

- 이벤트가 쓰이는 곳이다. Producer는 이 Topic에 이벤트를 게시한다. 그리고 Consumer는 Topic으로부터 이벤트를 가져와 처리한다. Topic은 파일 시스템의 `폴더`와 유사하며, 이벤트는 폴더 안의 `파일`과 유사하다.
- Topic에 저장된 이벤트는 필요한 만큼 다시 읽을 수 있다.

**Partition**

- Topic은 여러 Broker에 분산되어 저장된다. 이렇게 분산된 Topic을 Partition이라고 한다.
- 어떤 이벤트가 Partition에 저장될 지는 이벤트의 key(키)에 의해 정해지며, 같은 키를 가지는 이벤트는 항상 같은 Partition에 저장된다 .
- kafka는 Topic의 Partition에 지정된 Consumer가 항상 정확히 동일한 순서로 Partition의 이벤트를 읽을 것을 보장한다.

# 3. Kafka의 주요 개념

### 3.1 Producer와 Consumer의 분리

- Kafka의 Producer와 Consumer는 완전 별개로 동작한다.  
Producer는 Broker의 Topic에 메시지를 게시하기만 하면 되며, Consumer는 Broker의 특정 Topic에서 메시지를 가져와 처리를 하기만 한다.

- 이 덕분에 Kafka는 높은 확장성을 제공한다. 
즉, Producer 또는 Consumer를 필요에 의해 스케일 인 아웃 하기에 용이한 구조이다. 만약 Producer와 Consumer가 직접적으로 연관을 가지고 있다면, 확장 또는 축소 시 이들을 모두 연결 또는 해제를 하기가 매우 번거롭고 어려웠을 것이다.

 

### 3.2 Push와 Pull 모델

- kafka의 `consumer`는 `Pull` 모델을 기반으로 메시지 처리를 진행한다.
- 즉, Broker가 Consumer에게 메시지를 전달하는 것이 아닌, Consumer가 필요할 때, Broker로부터 메시지를 가져와 처리하는 형태이다.

**Pull 모델의 장점**

1. 여러 다양한 소비자의 처리 형태와 속도를 고려하지 않아도 된다. 
    - 반대의 경우인 Push 모델에서는 Broker가 데이터 전송 속도를 제어하기 때문에 다양한 메시지 스트림의 소비자를 다루기가 어렵지만, Pull 모델은 Consumer가 처리 가능한 때에 메시지를 가져와 처리하기 떄문에 다양한 소비자를 다루기가 쉽다.

1. 불필요한 지연 없이 일관처리를 통해 성능향상 도모
    - Push 모델의 경우에는 요청을 즉시 보내거나, 더 많은 메시지를 한번에 처리하도록 하기 위해 Buffering을 할 수 있다.
    - 하지만 이 경우, Consumer가 현재 메시지를 처리할 수 있음에도 대기를 해야한다. 그렇다고 전송 지연 시간을 최소로 변경하면, 한번에 하나의 메시지만을 보내도록 하는 것과 같으므로 매우 비효율적이다.
    - Pull 모델의 경우, 마지막으로 처리된 메시지 이후의 메시지를 Consumer가 처리가능한 때에 모두 가져오기 때문에, 이 문제를 해결한다. 따라서 불필요한 지연 없이 최적의 일괄처리를 할 수 있다.

### 3.3 소비된 메시지 추적 (Commit과 Offset)

![image](https://user-images.githubusercontent.com/47748246/176575856-e0eaaf72-0a78-468a-8c40-fbffbc6c449b.png)

- 메시지는 지정된 Topic에 전달된다. Topic은 다시 여러 Partition으로 나뉠 수도 있다. 위 그림에서 각 파티션의 한칸 한칸을 로그라고 칭한다.
- 또한 메시지는 로그에 순차적으로 append된다. 그리고 이 메시지의 상대적인 위치를 offset이라고 칭한다.
- 메시징 시스템은 Broker에서 소비된 메시지에 대한 메타데이터를 유지한다. 즉, 메시지가 `Consumer`에게 전달되면 Broker는 이를 로컬에 기록하거나, `소비자의 승인`을 기다린다.

**Commit과 Offset**

- Consumer의 poll()은 이전에 commit한 offset이 존재하면, 해당 offset 이후의 메시지를 읽어오게된다. 그러고 나면 마지막 offset을 commit한다.
- 이어서 poll()이 실행되면 방금 전 commit한 offset 이후의 메시지를 읽어와 처리하게 된다.

**메시지 소비 중 발생할 수 있는 문제들**

1. **소비된 메시지 기록 시점**
    - `Broker`가 메시지를 네트워크를 통해 Consumer에 전달할때 마다 즉시 `소비된 것`으로 기록하면, `Consumer`가 메시지 처리를 실패했을 때 해당 메시지가 손실된다.
    - 이로 인해서, Broker는 메시지가 소비되었음을 기록하기 위해서, Consumer의 승인을 기다린다. 하지만 이런식으로 메시지를 처리하게 되면 아래와 같은 문제점이 또 발생한다.

1. **중복 메시지와 멱등성**
    - 우선 `Consumer`가 메시지를 성공적으로 처리하고, `승인`을 보내기 전에 `Broker`가 실패하였다고 판단하고 다시 메시지를 보내게 되면, Consumer는 같은 메시지를 두번 처리하게 된다.
    - **따라서, Consumer는 `멱등성`을 고려하여야 한다.** 즉, 같은 메시지를 특수한 상황에 의해 여러 번 받아서 여러 번 처리하더라도, 한 번 처리한 것과 같은 결과를 갖도록 설계해야 한다.

### 3.4 Consumer Group

- Consumer Group은 하나의 Topic을 구독하는 여러 Consumer들의 모음이다. Topic을 구독하는 Consumer들을 Group화 하는 이유는 `가용성` 때문이다.
    
    → 하나의 Topic을 처리하는 Consumer가 1개인 것보다 여러 개라면 당연히 가용성은 증가할 것이다. 
    
- Consumer Group의 각 Consumer들은 하나의 Topic의 각기 다른 Partition의 내용만을 처리할 수 있다.

**Rebalance**

- Partition을 담당하던 Consumer가 처리 불가 상태가 되어버리면, Partition과 Consumer를 재조정하여, 남은 Consumer 그룹 내의 Consumer들이 Partition을 적절하게 나누어 처리하게 된다.
- 또한 Consumer Group 내에서 Consumer들 간에 Offset 정보를 공유하고 있기 때문에, 특정 Consumer가 처리 불가 상태가 되었을 때, 해당 Consumer가 처리한 마지막 Offset 이후부터 처리를 이어서 할 수 있다.
- 이렇게 Partition을 나머지 Consumer들이 다시 나누어 처리하도록 하는 것을 Rebalance라고 하며, 이를 위해 Consumer Group이 필요하다.

**Consumer Group과 Partition**

![image](https://user-images.githubusercontent.com/47748246/176575893-375d3e9f-98c0-45a6-ac00-22f58e94663d.png)

- 무조건 Consumer Group 내의 Consumer들은 모두 각기 다른 Partition에 연결되어야 한다. 이렇게 함으로써 Consumer의 메시지 처리 순서를 보장하게 된다. 즉 위와 같은 그림의 형태로는 가능하지만
    
![image](https://user-images.githubusercontent.com/47748246/176575919-30f9c0af-b889-4905-bdd7-1fee3076fced.png)
    
- 이와 같은 형태로 구성은 불가능하다.

**Consumer 확장**

- Consumer의 성능이 부족해 Consumer를 확장한다고 했을 때, Consumer Group 내의 Consumer는 무조건 각기 다른 Partition에만 연결을 할 수 있다고 했다.
- 따라서 Consumer를 확장할 때는 Partition도 같이 늘려주어야 한다.

### 3.5 메시지 (이벤트) 전달 컨셉

kafka는 메시지 전달을 할 때 보장하는 여러가지 방식이 있다. 

- At most once (최대 한번) : 메시지가 `손실`될수 있지만, `재전달`은 하지 않는다.
- At least once (최소 한번) : 메시지가 `손실`되지 않지만, `재전달`이 일어난다.
- Exactly once (정확히 한번) : 메시지는 정확히 한번 전달이 된다.
