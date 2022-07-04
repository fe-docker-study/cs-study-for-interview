<aside>
📖 카프카 운영에 있어서 중요한 역할을 하는 broker, replication(복제), ISR에 대해서 알아보자.

특히 replication은 카프카 아키텍처의 핵심이다. 
→ 클러스터에서 서버 장애가 생겼을 때, 카프카의 가용성을 보장하는 가장 좋은 방법이 복제이기 때문

</aside>

### Broker

- 카프카가 설치되어 있는 서버 단위
- 만약 파티션 1개, replication이 1인 topic이 존재하고, broker가 3대라면
    
    → broker 3대 중 1대에 해당 토픽 정보가 저장된다. 
    

### Replication

- replication은 partition의 복제를 뜻한다.
    - 만약 replication이 1이라면, partition은 1개만 존재한다는 것을 뜻함
    
    ![image](https://user-images.githubusercontent.com/47748246/177092142-99ebb980-4019-4ff1-90e1-6b1c721ee49c.png)
    
    - 만약 replication이 2이라면, partition은 원본 1개와 복제본 1개로 총 2개가 존재한다.
    
    ![image](https://user-images.githubusercontent.com/47748246/177092163-83d3a89d-e689-419f-ae6d-f7555f3039b3.png)
    
    - 만약 replication이 3이라면, partition은 원본 1개와 복제본 2개로 총 3개가 존재하게 된다.
    
    ![image](https://user-images.githubusercontent.com/47748246/177092189-a8aefaae-74a6-4aa6-9453-cafc42c4e711.png)
    

- 다만, broker 개수에 따라서 replication 개수가 제한됨
    
    → 브로커 개수가 3이면 replication은 4가 될 수 없다.
    
- 원본 partition을 Leader partition, 복제 partition을 follower partition이라 하며 이 둘을 합쳐서 ISR. 즉, In Sync Replica라고 볼 수 있다.

### Replication을 사용하는 이유

- partition의 고가용성을 위해
    
    ![image](https://user-images.githubusercontent.com/47748246/177092227-03c90d8f-908b-49a7-91a3-86c9bed7f762.png)
    
    - 만약 브로커가 3개인 카프카에서, replication이 1이고 partition이 1인 topic이 존재한다고 가정해보자. → 브로커 하나가 고장나면, 더 이상 그 안에 있는 파티션은 복구할 수 없게 된다.
    - 반면, replication이 2이면, 브로거 하나가 고장나더라도 다른 broker의 해당 파티션(=follower partition)이 존재하므로 복구가 가능하다.
    - 즉, follower partition이 leader partition 역할을 승계하게 된다.
