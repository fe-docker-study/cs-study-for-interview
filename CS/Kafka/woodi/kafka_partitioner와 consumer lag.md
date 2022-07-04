# Partitioner
![image](https://user-images.githubusercontent.com/47748246/177092379-1f5d2df6-0823-40a7-bb6c-b2b808d92381.png)

- 프로듀서가 데이터를 보내면 무조건 파티셔너를 통해서 브로커로 전달이 된다.
- 파티셔너는 데이터를 토픽의 어떤 파티션에 넣을지 결정하는 역할을 한다.
- 레코드에 포함된 메시지 키 또는 메시지 값에 따라서 파티션의 위치가 결정된다.

# Consumer Lag

![image](https://user-images.githubusercontent.com/47748246/177092405-f3bdba24-4138-43eb-b020-35ebe0a840b3.png)
- consumer lag = 프로듀서가 마지막으로 넣은 offset - 컨슈머가 마지막으로 읽은 offset
- 이 lag의 숫자를 통해 현재 해당 토픽에 대해 파이프라인으로 연계되어 있는 producer와 consumer의 상태에 대해 유추가 가능하다. (* 주로 consumer의 상태를 볼 때 사용한다.)
- 따라서 topic에 여러 파티션이 존재할 경우 여러 개의 lag이 존재할 수 있다.

# Burrow

- consumer lag을 효과적으로 모니터링 하기 위한 오픈소스 Burrow
- Burrow의 3가지 특징
    1. 멀티 카프카 클러스터 지원
    
    ![image](https://user-images.githubusercontent.com/47748246/177092465-df69df10-c8bf-46b2-a753-825b7f1cfa35.png)
    
    2. Sliding window를 통한 consumer의 status 확인
        - Burrow에는 sliding window를 통해서 consumer의 status를 ‘ERROR’, ‘WARNING’, ‘OK’로 표현할 수 있도록 했다.
        - 만약 데이터 양이 일시적으로 많아지면서 consumer offset이 증가되고 있으면 WARNING으로 정의된다.
    3. HTTP api 제공
        - 위 정보들은 Burrow가 정의한 HTTP api를 통해 조회할 수 있게 했다.
